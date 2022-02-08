package net.labymod.addons.customblockoverlay.colorpicker;

import net.labymod.addons.customblockoverlay.colorpicker.selector.HueSelectorWidget;
import net.labymod.addons.customblockoverlay.colorpicker.selector.ShadeSelectorWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.render.matrix.Stack;

public class ColorPickerOverlayActivity extends SimpleActivity {

  private final ColorPickerColor color;
  private final boolean displayAlpha;
  private final boolean displayChroma;

  public ColorPickerOverlayActivity(ColorPickerColor color, boolean displayAlpha,
                                    boolean displayChroma) {
    this.color = color;
    this.displayAlpha = displayAlpha;
    this.displayChroma = displayChroma;
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

    ShadeSelectorWidget shadeSelector = new ShadeSelectorWidget(this.color);
    colorPicker.addChild(shadeSelector);
    HueSelectorWidget hueSelector = new HueSelectorWidget(this.color);
    colorPicker.addChild(hueSelector);

    this.getDocument().addChild(colorPicker);
  }

  @Override
  public void render(Stack stack, int mouseX, int mouseY, float partialTicks) {
    super.render(stack, mouseX, mouseY, partialTicks);
  }

  @Override
  public boolean mouseClicked(int mouseX, int mouseY, MouseButton mouseButton) {
    return super.mouseClicked(mouseX, mouseY, mouseButton);
  }
}
