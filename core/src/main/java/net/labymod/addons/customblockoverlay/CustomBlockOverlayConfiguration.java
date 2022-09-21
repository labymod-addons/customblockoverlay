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

import java.awt.*;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public final class CustomBlockOverlayConfiguration extends AddonConfig {

  @SwitchSetting
  private ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> outlineEnabled = new ConfigProperty<>(true);

  @ColorPickerSetting(alpha = true)
  private ConfigProperty<Integer> outlineColor = new ConfigProperty<>(
      new Color(117, 0, 10, 170).getRGB());

  @SwitchSetting
  private ConfigProperty<Boolean> overlayEnabled = new ConfigProperty<>(true);

  @ColorPickerSetting(alpha = true)
  private ConfigProperty<Integer> overlayColor = new ConfigProperty<>(
      new Color(9, 58, 142, 70).getRGB());

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> outlineEnabled() {
    return this.outlineEnabled;
  }

  public ConfigProperty<Integer> outlineColor() {
    return this.outlineColor;
  }

  public ConfigProperty<Boolean> overlayEnabled() {
    return this.overlayEnabled;
  }

  public ConfigProperty<Integer> overlayColor() {
    return this.overlayColor;
  }
}

