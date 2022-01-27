package net.labymod.addons.customblockoverlay.colorpicker;

import java.awt.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Consumer;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.BoundsType;
import net.labymod.api.client.gui.screen.widget.widgets.activity.Document;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.ScreenRendererWidget;
import net.labymod.api.configuration.settings.accessor.SettingAccessor;
import net.labymod.api.configuration.settings.annotation.SettingElement;
import net.labymod.api.configuration.settings.annotation.SettingFactory;
import net.labymod.api.configuration.settings.widget.WidgetFactory;

public class NewColorPickerWidget extends HorizontalListWidget {

  private final boolean displayAlpha;
  private final ColorPickerColor color;
  private final Consumer<Color> callback;

  public NewColorPickerWidget(boolean displayAlpha, ColorPickerColor color,
      Consumer<Color> callback) {
    this.displayAlpha = displayAlpha;
    this.color = color;
    this.callback = callback;
    this.setPressable(this::openOverlay);
  }

  private void openOverlay() {
    Parent parent = this.getRoot();
    Document document = ((Activity) parent).getDocument();
    ScreenRendererWidget screenRendererWidget = new ScreenRendererWidget();
    screenRendererWidget.getBounds().setHeight(document.getBounds().getHeight());
    screenRendererWidget.getBounds().setWidth(document.getBounds().getWidth());
    screenRendererWidget.displayScreen(
        new ColorPickerOverlayActivity(this.color, this.displayAlpha));
    document.addChild(screenRendererWidget);
  }

  @Override
  public float getContentWidth(BoundsType type) {
    return Math.max(20.0F, super.getContentWidth(type));
  }

  @Override
  public float getContentHeight(BoundsType type) {
    return Math.max(20.0F, super.getContentHeight(type));
  }

  @SettingElement
  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface ColorPicker {

    boolean alpha() default true;
  }

  @SettingFactory
  public static class Factory implements WidgetFactory<ColorPicker, NewColorPickerWidget> {

    public Factory() {
    }

    public NewColorPickerWidget create(ColorPicker annotation, SettingAccessor accessor) {
      NewColorPickerWidget widget = new NewColorPickerWidget(annotation.alpha(),
          ColorPickerColor.of(((Color) accessor.get()).getRGB()), accessor::set);
      widget.backgroundColor = widget.color.getRgb() | -16777216;
      return widget;
    }
  }
}
