package net.labymod.addons.customblockoverlay;

import com.google.inject.Singleton;
import javax.inject.Inject;
import net.labymod.addons.customblockoverlay.colorpicker.NewColorPickerWidget;
import net.labymod.addons.customblockoverlay.listener.RenderBlockSelectionBoxListener;
import net.labymod.api.LabyAPI;
import net.labymod.api.configuration.loader.ConfigurationLoader;
import net.labymod.api.configuration.settings.SettingsRegistry;
import net.labymod.api.configuration.settings.gui.SettingCategoryRegistry;
import net.labymod.api.event.Priority;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameInitializeEvent;
import net.labymod.api.event.client.lifecycle.GameInitializeEvent.Lifecycle;
import net.labymod.api.event.labymod.config.ConfigurationSaveEvent;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
@Singleton
public class CustomBlockOverlay {

  private final LabyAPI labyAPI;
  private final SettingCategoryRegistry categoryRegistry;

  private CustomBlockOverlayConfiguration configuration;

  @Inject
  private CustomBlockOverlay(LabyAPI labyAPI, SettingCategoryRegistry categoryRegistry) {
    this.labyAPI = labyAPI;
    this.categoryRegistry = categoryRegistry;

    labyAPI.getEventBus().registerListener(this, RenderBlockSelectionBoxListener.class);
  }

  /**
   * On game initialize.
   *
   * @param event the event
   */
  @Subscribe(Priority.LATEST)
  public void onGameInitialize(GameInitializeEvent event) {
    if (event.getLifecycle() != Lifecycle.POST_STARTUP) {
      return;
    }

    this.labyAPI.getWidgetRegistry().register(NewColorPickerWidget.class);

    ConfigurationLoader configurationLoader = this.labyAPI.getConfigurationLoader();
    try {
      this.configuration = configurationLoader.load(CustomBlockOverlayConfiguration.class);
      SettingsRegistry registry = this.configuration.initializeRegistry();
      this.categoryRegistry.register("customblockoverlay.settings", registry);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

/*  @Subscribe
  public void onSettingWidgetInitialize(SettingWidgetInitializeEvent event) {
    if (!event.getLayer().getId().equals("customblockoverlay.settings")) {
      return;
    }

    ColorPickerWidget colorPickerWidget = new ColorPickerWidget(true, value -> {
      System.out.println(value.getRGB());
    });
    event.getSettings().add(colorPickerWidget);
  } */


  /**
   * On configuration save.
   *
   * @param event the event
   */
  @Subscribe
  public void onConfigurationSave(ConfigurationSaveEvent event) {
    try {
      event.getLoader().save(this.configuration);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public CustomBlockOverlayConfiguration getConfiguration() {
    return this.configuration;
  }
}
