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

  @SwitchSetting
  private boolean lineEnabled;

  @ColorPickerSetting
  private int lineColor;

  @SwitchSetting
  private boolean overlayEnabled;

  @ColorPickerSetting
  private int overlayColor;

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  public boolean isLineEnabled() {
    return this.lineEnabled;
  }

  public int getLineColor() {
    return this.lineColor;
  }

  public boolean isOverlayEnabled() {
    return this.overlayEnabled;
  }

  public int getOverlayColor() {
    return this.overlayColor;
  }
}

