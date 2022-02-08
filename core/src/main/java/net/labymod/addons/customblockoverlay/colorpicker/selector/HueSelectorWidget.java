package net.labymod.addons.customblockoverlay.colorpicker.selector;

import net.labymod.addons.customblockoverlay.colorpicker.ColorPickerColor;
import net.labymod.api.Laby;
import net.labymod.api.client.render.batch.RectangleRenderContext;
import net.labymod.api.client.render.matrix.Stack;

public class HueSelectorWidget extends SelectorWidget {

  private static final RectangleRenderContext RENDER_CONTEXT = Laby.getLabyAPI().getRenderContexts()
      .getRectangleRenderContext();

  public HueSelectorWidget(ColorPickerColor color) {
    super(color);
  }

  @Override
  public void render(Stack stack, int mouseX, int mouseY, float partialTicks) {
    RENDER_CONTEXT.begin();

    float width = this.getBounds().getWidth();
    int lowHue;
    int highHue;
    for (float i = 0; i < width; i += width / 8) {
      lowHue = this.getColor().hsbToRgb((int) (i * 360 / width), 100, 100);
      highHue = this.getColor().hsbToRgb((int) ((i + 8) * 360 / width), 100, 100);

      System.out.println(i + ";" + width + ";" + lowHue + ";" + highHue);

      RENDER_CONTEXT.renderGradientHorizontally(stack, this.getBounds().getLeft() + i,
          this.getBounds().getTop(), this.getBounds().getLeft() + i + width / 8,
          this.getBounds().getBottom(), lowHue, highHue);
    }

    RENDER_CONTEXT.uploadToBuffer();

    super.render(stack, mouseX, mouseY, partialTicks);
  }

  @Override
  public void update(float posX, float posY) {

  }

  @Override
  public void setMarkerPosition() {

  }
}
