package net.labymod.addons.customblockoverlay.colorpicker.selector;

import java.awt.*;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.SimpleWidget;
import net.labymod.api.client.render.batch.CircleRenderContext;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.client.render.vertex.phase.RenderPhases;

public class MarkerWidget extends SimpleWidget {

  private final SelectorWidget selectorWidget;
  private float x;
  private float y;

  protected MarkerWidget(SelectorWidget selectorWidget) {
    this.selectorWidget = selectorWidget;
  }

/*  @Override
  public String getDefaultRendererName() {
    return "SelectorMarker";
  } */

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    this.selectorWidget.setMarkerPosition();
  }

  @Override
  public void render(Stack stack, int mouseX, int mouseY, float partialTicks) {
    super.render(stack, mouseX, mouseY, partialTicks);
    CircleRenderContext renderContext = Laby.getLabyAPI().getRenderContexts()
        .getCircleRenderContext();
    float width = this.getBounds().getWidth() / 2;
    renderContext.getBuilder().begin(RenderPhases.triangle(false, 2F));
    renderContext.render(stack, this.getBounds().getLeft() + width,
        this.getBounds().getTop() + width, width, Color.WHITE.getRGB());
    renderContext.uploadToBuffer();
  }

  public void setX(float x) {
    this.x = x;
    float width = this.getBounds().getWidth();
    this.getBounds().setLeft(this.selectorWidget.getBounds().getLeft() + x - width / 2);
    this.getBounds().setWidth(width);
  }

  public void setY(float y) {
    this.y = y;
    float height = this.getBounds().getHeight();
    this.getBounds().setTop(this.selectorWidget.getBounds().getBottom() - y - height / 2);
    this.getBounds().setHeight(height);
  }
}
