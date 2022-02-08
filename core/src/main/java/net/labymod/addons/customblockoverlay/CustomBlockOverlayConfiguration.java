package net.labymod.addons.customblockoverlay;

import com.google.inject.Singleton;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.impl.AddonConfig;

@SuppressWarnings("FieldMayBeFinal")
@Singleton
@ConfigName("settings")
public final class CustomBlockOverlayConfiguration extends AddonConfig {

  @SwitchSetting
  private boolean enabled;

  @ColorPickerSetting
  private int lineColor;

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  public int getLineColor() {
    return this.lineColor;
  }
}

