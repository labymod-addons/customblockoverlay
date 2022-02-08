package net.labymod.addons.customblockoverlay.colorpicker.selector;

import java.awt.*;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.theme.ThemeRenderer;
import net.labymod.api.client.render.batch.CircleRenderContext;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.client.render.vertex.phase.RenderPhases;

public class VanillaMarkerRenderer extends ThemeRenderer<MarkerWidget> {

  private static final CircleRenderContext RENDER_CONTEXT = Laby.getLabyAPI().getRenderContexts()
      .getCircleRenderContext();

  public VanillaMarkerRenderer() {
    super("SelectorMarker");
  }

  @Override
  public void renderPre(MarkerWidget widget, Stack stack, int mouseX, int mouseY,
      float partialTicks) {
    super.renderPre(widget, stack, mouseX, mouseY, partialTicks);
    float width = widget.getBounds().getWidth() / 2;
    System.out.println("render");
    RENDER_CONTEXT.getBuilder().begin(RenderPhases.triangle(false, 2F));
    RENDER_CONTEXT.render(stack, widget.getBounds().getLeft() + width,
        widget.getBounds().getTop() + width, width,
        Color.WHITE.getRGB());
    RENDER_CONTEXT.uploadToBuffer();
  }
}
