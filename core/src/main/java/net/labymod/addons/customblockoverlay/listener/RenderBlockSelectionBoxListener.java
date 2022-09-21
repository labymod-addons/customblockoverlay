/*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

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
    if (!configuration.enabled().get()) {
      return;
    }

    if (configuration.outlineEnabled().get()) {
      this.lineColor = this.getCachedOrNewColor(this.lineColor, configuration.outlineColor().get());
      event.setLineColor(this.lineColor);
    } else {
      event.setLineColor(null);
    }

    if (configuration.overlayEnabled().get()) {
      this.overlayColor = this.getCachedOrNewColor(this.overlayColor,
          configuration.overlayColor().get());
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
