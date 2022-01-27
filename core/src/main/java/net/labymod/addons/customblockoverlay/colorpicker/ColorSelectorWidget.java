package net.labymod.addons.customblockoverlay.colorpicker;

import java.awt.*;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.AbstractWidget;
import net.labymod.api.client.gui.screen.widget.Widget;
import net.labymod.api.client.gui.screen.widget.attributes.WidgetAlignment;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.VerticalListWidget;
import net.labymod.api.client.render.batch.RectangleRenderContext;
import net.labymod.api.client.render.matrix.Stack;

public abstract class ColorSelectorWidget extends VerticalListWidget<Widget> {

  protected static final RectangleRenderContext RENDER_CONTEXT = Laby.getLabyAPI()
      .getRenderContexts()
      .getRectangleRenderContext();

  private final String key;
  private final ColorPickerColor color;
  private final int defaultValue;

  private GradientWidget gradient;
  private TextFieldWidget textField;

  //set by lss
  private WidgetAlignment fieldPosition;
  private float gradientWidth;

  protected ColorSelectorWidget(String key, ColorPickerColor color, int defaultValue) {
    this.key = key;
    this.color = color;
    this.defaultValue = defaultValue;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    this.addId("color-selector");
    this.addId(this.key + "-selector");

    ComponentWidget title = ComponentWidget.i18n(
        "customblockoverlay.settings.overlay." + this.key + ".name");
    title.addId("color-picker-title");
    this.addChild(title);

    HorizontalListWidget selector = new HorizontalListWidget();
    selector.addId("selector-list");

    this.gradient = new GradientWidget();
    this.gradient.getBounds().setWidth(this.gradientWidth);
    selector.addEntry(this.gradient);

    System.out.println("TEXTFIELDPOS " + this.fieldPosition);

    this.textField = new TextFieldWidget();
    this.textField.getBounds().setWidth(this.getBounds().getWidth() - this.gradientWidth);
    this.textField.setText(String.valueOf(this.defaultValue));
    selector.addEntry(/*this.fieldPosition.equalsIgnoreCase("left") ? 0 : */ 1, this.textField);
    this.addChild(selector);
  }

  @Override
  public boolean mouseClicked(int mouseX, int mouseY, MouseButton mouseButton) {
    return super.mouseClicked(mouseX, mouseY, mouseButton);
  }

  protected ColorPickerColor getColor() {
    return this.color;
  }

  protected void updateTextField(int value) {
    this.textField.setText(String.valueOf(value));
  }

  protected abstract void renderGradient(Stack stack, Bounds bounds);

  protected abstract float getMarkerPosition(Bounds bounds);

  protected abstract void updateValue(Bounds bounds, int value);

  private class GradientWidget extends AbstractWidget<GradientWidget> {

    @Override
    public void render(Stack stack, int mouseX, int mouseY, float partialTicks) {
      super.render(stack, mouseX, mouseY, partialTicks);
      ColorSelectorWidget.this.renderGradient(stack, this.getBounds());

      float position = ColorSelectorWidget.this.getMarkerPosition(this.getBounds());
      float x = this.getBounds().getLeft() - 1.5F + position;
      float y = this.getBounds().getTop() - 0.5F;
      float maxY = this.getBounds().getBottom() + 0.5F;

      RENDER_CONTEXT.begin();
      RENDER_CONTEXT.render(stack, x, y, x + 0.5F, maxY, Color.BLACK);
      RENDER_CONTEXT.render(stack, x + 2.5F, y, x + 3F, maxY, Color.BLACK);
      RENDER_CONTEXT.render(stack, x, y, x + 3F, y + 0.5F, Color.BLACK);
      RENDER_CONTEXT.render(stack, x, maxY - 0.5F, x + 3F, maxY, Color.BLACK);

      x += 0.5F;
      y += 0.5F;
      maxY -= 0.5F;
      RENDER_CONTEXT.render(stack, x, y, x + 0.5F, maxY, Color.WHITE);
      RENDER_CONTEXT.render(stack, x + 1.5F, y, x + 2F, maxY, Color.WHITE);
      RENDER_CONTEXT.render(stack, x, y, x + 2F, y - 0.5F, Color.WHITE);
      RENDER_CONTEXT.render(stack, x, maxY + 0.5F, x + 2F, maxY, Color.WHITE);
      RENDER_CONTEXT.uploadToBuffer();
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, MouseButton mouseButton) {
      ColorSelectorWidget.this.updateValue(this.getBounds(),
          (int) (mouseX - this.getBounds().getLeft()));
      return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseDragged(int mouseX, int mouseY, int button, double deltaX, double deltaY) {
      ColorSelectorWidget.this.updateValue(this.getBounds(),
          (int) (mouseX - this.getBounds().getLeft()));
      return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
  }
}
