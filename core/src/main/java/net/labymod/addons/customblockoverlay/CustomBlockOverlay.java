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

import net.labymod.addons.customblockoverlay.listener.RenderBlockSelectionBoxListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class CustomBlockOverlay extends LabyAddon<CustomBlockOverlayConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new RenderBlockSelectionBoxListener(this));
  }

  @Override
  protected Class<CustomBlockOverlayConfiguration> configurationClass() {
    return CustomBlockOverlayConfiguration.class;
  }
}
