package net.labymod.addons.customblockoverlay.listener;

import java.awt.*;
import java.util.Objects;
import javax.inject.Inject;
import net.labymod.addons.customblockoverlay.CustomBlockOverlay;
import net.labymod.addons.customblockoverlay.CustomBlockOverlayConfiguration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.world.RenderBlockSelectionBoxEvent;
import net.labymod.api.util.ColorUtil;

public class RenderBlockSelectionBoxListener {

  private final CustomBlockOverlay addon;
  private Color lineColor;
  private Color overlayColor;

  @Inject
  public RenderBlockSelectionBoxListener(CustomBlockOverlay addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onRenderBlockSelectionBox(RenderBlockSelectionBoxEvent event) {
    CustomBlockOverlayConfiguration configuration = this.addon.configuration();
    if (!configuration.isEnabled()) {
      return;
    }

    if (configuration.isLineEnabled()) {
      this.lineColor = this.getCachedOrNewColor(this.lineColor, configuration.getLineColor());
      event.setLineColor(this.lineColor);
    } else {
      event.setLineColor(null);
    }

    if (configuration.isOverlayEnabled()) {
      this.overlayColor = this.getCachedOrNewColor(this.overlayColor,
          configuration.getOverlayColor());
      event.setOverlayColor(this.overlayColor);
    }
  }

  private Color getCachedOrNewColor(Color color, int rgb) {
    int[] rgba = this.rgbToIntArray(rgb);
    if (Objects.nonNull(color)) {
      boolean matches =
          rgba[0] == color.getRed() && rgba[1] == color.getGreen() && rgba[2] == color.getBlue()
              && rgba[3] == color.getAlpha();
      if (matches) {
        return color;
      }
    }

    return new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
  }

  private int[] rgbToIntArray(int rgb) {
    float[] color = ColorUtil.toRGBA(rgb);
    int[] rgba = new int[4];
    rgba[0] = (int) (color[0] * 255);
    rgba[1] = (int) (color[1] * 255);
    rgba[2] = (int) (color[2] * 255);
    rgba[3] = (int) (color[3] * 255);

    return rgba;
  }
}
