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

    event.setLineColor(new Color(this.configuration.getLineColor()));
  }
}
