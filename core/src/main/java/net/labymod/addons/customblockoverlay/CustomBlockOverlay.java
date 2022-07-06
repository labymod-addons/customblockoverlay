package net.labymod.addons.customblockoverlay;

import com.google.inject.Singleton;
import net.labymod.addons.customblockoverlay.listener.RenderBlockSelectionBoxListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonListener;

@AddonListener
@Singleton
public class CustomBlockOverlay extends LabyAddon<CustomBlockOverlayConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(RenderBlockSelectionBoxListener.class);
  }

  @Override
  protected Class<CustomBlockOverlayConfiguration> configurationClass() {
    return CustomBlockOverlayConfiguration.class;
  }
}
