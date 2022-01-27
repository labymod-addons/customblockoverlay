package net.labymod.addons.customblockoverlay.colorpicker.selector;

import net.labymod.addons.customblockoverlay.colorpicker.ColorPickerColor;
import net.labymod.addons.customblockoverlay.colorpicker.ColorSelectorWidget;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.math.MathHelper;

public class GreenSelectorWidget extends ColorSelectorWidget {


  public GreenSelectorWidget(ColorPickerColor color) {
    super("green", color, color.getGreen());
  }

  @Override
  protected void renderGradient(Stack stack, Bounds bounds) {
    int rgb = this.getColor().getRgb() | 0xff000000;

    RENDER_CONTEXT.begin();
    RENDER_CONTEXT.renderGradientHorizontally(stack, bounds.getLeft(), bounds.getTop(),
        bounds.getRight(), bounds.getBottom(), rgb & 0xffff00ff,
        rgb | 0x0000ff00);
    RENDER_CONTEXT.uploadToBuffer();
  }

  @Override
  protected float getMarkerPosition(Bounds bounds) {
    return ((this.getColor().getRgb() & 0x00ff00) >> 8) * bounds.getWidth() / 255F;
  }

  @Override
  protected void updateValue(Bounds bounds, int value) {
    value = MathHelper.clamp(value, 0, (int) bounds.getWidth());
    int rgb = this.getColor().getRgb();
    rgb &= 0xff00ff;
    rgb |= (value * 255 / 64) << 8;
    this.getColor().setRgb(rgb);

    this.updateTextField(this.getColor().getGreen());
  }
}
