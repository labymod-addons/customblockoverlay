package net.labymod.addons.customblockoverlay.colorpicker.selector;

import java.awt.*;
import net.labymod.addons.customblockoverlay.colorpicker.ColorPickerColor;
import net.labymod.api.Laby;
import net.labymod.api.client.render.batch.RectangleRenderContext;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.math.MathHelper;

public class ShadeSelectorWidget extends SelectorWidget {

  private static final RectangleRenderContext RENDER_CONTEXT = Laby.getLabyAPI().getRenderContexts()
      .getRectangleRenderContext();
  private final Color WHITE_LEFT = new Color(255, 255, 255, 255);
  private final Color WHITE_RIGHT = new Color(255, 255, 255, 0);
  private final Color BLACK_TOP = new Color(0, 0, 0, 0);
  private final Color BLACK_BOTTOM = new Color(0, 0, 0, 255);

  public ShadeSelectorWidget(ColorPickerColor color) {
    super(color);
  }

  @Override
  public void render(Stack stack, int mouseX, int mouseY, float partialTicks) {
    RENDER_CONTEXT.begin();
    RENDER_CONTEXT.render(stack, this.getBounds(),
        this.getColor().hsbToRgb(this.getColor().getHue(), 100, 100));
    RENDER_CONTEXT.renderGradientHorizontally(stack, this.getBounds(), this.WHITE_LEFT.getRGB(),
        this.WHITE_RIGHT.getRGB());
    RENDER_CONTEXT.renderGradientVertically(stack, this.getBounds(), this.BLACK_TOP.getRGB(),
        this.BLACK_BOTTOM.getRGB());
    RENDER_CONTEXT.uploadToBuffer();

    super.render(stack, mouseX, mouseY, partialTicks);
  }

  @Override
  public void update(float mouseX, float mouseY) {
    this.updateMarkerPosition(mouseX, mouseY);
    float saturation = MathHelper.clamp(mouseX * 100 / this.getBounds().getWidth(), 0, 100);
    float brightness = MathHelper.clamp(mouseY * 100 / this.getBounds().getHeight(), 0, 100);
    this.getColor().setSaturation((int) saturation);
    this.getColor().setBrightness((int) brightness);
    this.getColor().setRgb();
  }

  @Override
  public void setMarkerPosition() {
    float posX = this.getColor().getSaturation() * this.getBounds().getWidth() / 100;
    float posY = this.getColor().getBrightness() * this.getBounds().getHeight() / 100;
    this.updateMarkerPosition(posX, posY);
  }
}
