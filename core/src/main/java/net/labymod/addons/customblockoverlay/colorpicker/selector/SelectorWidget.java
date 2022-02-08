package net.labymod.addons.customblockoverlay.colorpicker.selector;

import net.labymod.addons.customblockoverlay.colorpicker.ColorPickerColor;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.SimpleWidget;

public abstract class SelectorWidget extends SimpleWidget {

  private final ColorPickerColor color;
  private final MarkerWidget markerWidget;

  protected SelectorWidget(ColorPickerColor color) {
    this.color = color;
    this.markerWidget = new MarkerWidget(this);
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    this.addChild(this.markerWidget);
  }

  @Override
  public boolean mouseClicked(int mouseX, int mouseY, MouseButton mouseButton) {
    this.update(mouseX - this.getBounds().getLeft(), -(mouseY - this.getBounds().getBottom()));
    return super.mouseClicked(mouseX, mouseY, mouseButton);
  }

  @Override
  public boolean mouseDragged(int mouseX, int mouseY, int button, double deltaX, double deltaY) {
    this.update(mouseX - this.getBounds().getLeft(), -(mouseY - this.getBounds().getBottom()));
    return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
  }

  public abstract void update(float posX, float posY);

  public abstract void setMarkerPosition();

  public void updateMarkerPosition(float posX, float posY) {
    if (posX != -1) {
      this.getMarkerWidget().setX(posX);
    }

    if (posY != -1) {
      this.getMarkerWidget().setY(posY);
    }

    this.getMarkerWidget().getBounds().checkForChanges();
  }

  public ColorPickerColor getColor() {
    return this.color;
  }

  public MarkerWidget getMarkerWidget() {
    return this.markerWidget;
  }
}
