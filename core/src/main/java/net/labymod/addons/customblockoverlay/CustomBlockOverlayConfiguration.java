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

package net.labymod.addons.customblockoverlay;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.util.Color;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public final class CustomBlockOverlayConfiguration extends AddonConfig {

  @SwitchSetting
  private ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> outlineEnabled = new ConfigProperty<>(true);

  @SettingRequires("outlineEnabled")
  @ColorPickerSetting(alpha = true, chroma = true)
  private ConfigProperty<Color> outlineColor = new ConfigProperty<>(Color.ofRGB(117, 0, 10, 170));

  @SwitchSetting
  private ConfigProperty<Boolean> overlayEnabled = new ConfigProperty<>(true);

  @SettingRequires("overlayEnabled")
  @ColorPickerSetting(alpha = true, chroma = true)
  private ConfigProperty<Color> overlayColor = new ConfigProperty<>(Color.ofRGB(9, 58, 142, 70));

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> outlineEnabled() {
    return this.outlineEnabled;
  }

  public ConfigProperty<Color> outlineColor() {
    return this.outlineColor;
  }

  public ConfigProperty<Boolean> overlayEnabled() {
    return this.overlayEnabled;
  }

  public ConfigProperty<Color> overlayColor() {
    return this.overlayColor;
  }
}

