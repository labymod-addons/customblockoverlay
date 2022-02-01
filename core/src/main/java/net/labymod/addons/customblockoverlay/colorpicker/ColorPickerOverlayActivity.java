package net.labymod.addons.customblockoverlay.colorpicker;

import net.labymod.addons.customblockoverlay.colorpicker.selector.BlueSelectorWidget;
import net.labymod.addons.customblockoverlay.colorpicker.selector.GreenSelectorWidget;
import net.labymod.addons.customblockoverlay.colorpicker.selector.RedSelectorWidget;
import net.labymod.addons.customblockoverlay.colorpicker.selector.WholeSelectorWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.render.matrix.Stack;

public class ColorPickerOverlayActivity extends SimpleActivity {

  private final ColorPickerColor color;
  private final boolean displayAlpha;

  public ColorPickerOverlayActivity(ColorPickerColor color, boolean displayAlpha) {
    this.color = color;
    this.displayAlpha = displayAlpha;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    this.renderBackground = false;
    DivWidget colorPicker = new DivWidget();
    colorPicker.addId("color-picker");

    ComponentWidget title = ComponentWidget.i18n("customblockoverlay.settings.overlay.title");
    title.addId("color-picker-title");
    colorPicker.addChild(title);

    ColorSelectorWidget redWidget = new RedSelectorWidget(this.color);
    colorPicker.addChild(redWidget);
    ColorSelectorWidget greenWidget = new GreenSelectorWidget(this.color);
    colorPicker.addChild(greenWidget);
    ColorSelectorWidget blueWidget = new BlueSelectorWidget(this.color);
    colorPicker.addChild(blueWidget);
    ColorSelectorWidget wholeWidget = new WholeSelectorWidget(this.color);
    colorPicker.addChild(wholeWidget);

    this.getDocument().addChild(colorPicker);
  }

  @Override
  public void render(Stack stack, int mouseX, int mouseY, float partialTicks) {
    super.render(stack, mouseX, mouseY, partialTicks);
  }

  @Override
  public boolean mouseClicked(int mouseX, int mouseY, MouseButton mouseButton) {
    return false;
  }
}
