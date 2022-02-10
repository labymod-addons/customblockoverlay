package net.labymod.addons.customblockoverlay.listener;

import java.awt.*;
import javax.inject.Inject;
import net.labymod.addons.customblockoverlay.CustomBlockOverlayConfiguration;
import net.labymod.api.event.Priority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.world.RenderBlockSelectionBoxEvent;

public class RenderBlockSelectionBoxListener {

  private final CustomBlockOverlayConfiguration configuration;

  @Inject
  public RenderBlockSelectionBoxListener(CustomBlockOverlayConfiguration configuration) {
    this.configuration = configuration;
  }

  @Subscribe(Priority.LATEST)
  public void onRenderBlockSelectionBox(RenderBlockSelectionBoxEvent event) {
    if (!this.configuration.isEnabled()) {
      return;
    }

    if (this.configuration.isLineEnabled()) {
      Color color = new Color(this.configuration.getLineColor());
      event.setLineColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 104));
    } else {
      event.setLineColor(null);
    }

    if (this.configuration.isOverlayEnabled()) {
      Color color = new Color(this.configuration.getOverlayColor());
      event.setOverlayColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 104));
    }
  }
}
