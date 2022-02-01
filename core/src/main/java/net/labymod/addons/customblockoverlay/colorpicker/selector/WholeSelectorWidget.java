package net.labymod.addons.customblockoverlay.colorpicker.selector;

import java.awt.*;
import net.labymod.addons.customblockoverlay.colorpicker.ColorPickerColor;
import net.labymod.addons.customblockoverlay.colorpicker.ColorSelectorWidget;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.client.render.vertex.VertexBuilder;

public class WholeSelectorWidget extends ColorSelectorWidget {

  public WholeSelectorWidget(ColorPickerColor color) {
    super("whole", color, color.getHue());
  }

  @Override
  protected void renderGradient(Stack stack, Bounds bounds) {
    float left = bounds.getLeft();
    float top = bounds.getTop();
    float right = bounds.getRight();
    float bottom = bounds.getBottom();

    int rgbLowHue = this.getColor().hsbToRgb(1 * 360 / 64, 100, 0) | 0xff000000;
    int rgbHighHue = this.getColor().hsbToRgb((1 + 8) * 360 / 64, 100, 100) | 0xff000000;
    Color low = new Color(rgbLowHue);
    Color high = new Color(rgbHighHue);

    RENDER_CONTEXT.begin();
    VertexBuilder builder = RENDER_CONTEXT.getBuilder();
    builder.vertex(stack, left, bottom, 0.0F).color(0, 0, 0, 1F).next(); //left bottom
    builder.vertex(stack, right, bottom, 0.0F)
        .color(low.getRed(), low.getGreen(), low.getBlue(), 1F).next(); //right botom
    builder.vertex(stack, right, top, 0.0F)
        .color(high.getRed(), high.getGreen(), high.getBlue(), 1F).next(); //right top
    builder.vertex(stack, left, top, 0.0F).color(1, 1, 1, 1F).next(); //left top
    RENDER_CONTEXT.uploadToBuffer();
  }

  @Override
  protected float getMarkerPosition(Bounds bounds) {
    return 0;
  }

  @Override
  protected void updateValue(Bounds bounds, int value) {

  }
}
