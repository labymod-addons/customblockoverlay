package net.labymod.addons.customblockoverlay.colorpicker.selector;

import net.labymod.addons.customblockoverlay.colorpicker.ColorPickerColor;
import net.labymod.addons.customblockoverlay.colorpicker.ColorSelectorWidget;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.math.MathHelper;

public class RedSelectorWidget extends ColorSelectorWidget {

  public RedSelectorWidget(ColorPickerColor color) {
    super("red", color, color.getRed());
  }

  @Override
  protected void renderGradient(Stack stack, Bounds bounds) {
    int rgb = this.getColor().getRgb() | 0xff000000;

    RENDER_CONTEXT.begin();
    RENDER_CONTEXT.renderGradientHorizontally(stack, bounds.getLeft(), bounds.getTop(),
        bounds.getRight(), bounds.getBottom(), rgb & 0xff00ffff, rgb | 0x00ff0000);
    RENDER_CONTEXT.uploadToBuffer();
  }

  @Override
  protected float getMarkerPosition(Bounds bounds) {
    return ((this.getColor().getRgb() & 0xff0000) >> 16) * bounds.getWidth() / 255F;
  }

  @Override
  protected void updateValue(Bounds bounds, int value) {
    value = MathHelper.clamp(value, 0, (int) bounds.getWidth());
    int rgb = this.getColor().getRgb();
    rgb &= 0x00ffff;
    rgb |= (value * 255 / 64) << 16;
    this.getColor().setRgb(rgb);

    this.updateTextField(this.getColor().getRed());
  }
}
