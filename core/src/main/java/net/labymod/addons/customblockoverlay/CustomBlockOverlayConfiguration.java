package net.labymod.addons.customblockoverlay;

import java.awt.*;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ConfigName;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public final class CustomBlockOverlayConfiguration extends Config {

  @SwitchSetting
  private boolean enabled = true;

  @SwitchSetting
  private boolean lineEnabled = true;

  @ColorPickerSetting(alpha = true)
  private int lineColor = new Color(117, 0, 10, 170).getRGB();

  @SwitchSetting
  private boolean overlayEnabled = true;

  @ColorPickerSetting(alpha = true)
  private int overlayColor = new Color(9, 58, 142, 170).getRGB();

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

