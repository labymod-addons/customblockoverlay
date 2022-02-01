package net.labymod.addons.customblockoverlay.colorpicker;

import java.awt.*;

public class ColorPickerColor {

  private int rgb;
  private int alpha;
  private int hue;
  private int saturation;
  private int brightness;

  private ColorPickerColor(int rgb) {
    this.setColor(rgb);
  }

  protected static ColorPickerColor of(int rgb) {
    return new ColorPickerColor(rgb);
  }

  protected static ColorPickerColor of(short red, short green, short blue, short alpha) {
    return new ColorPickerColor(
        (alpha & 255) << 24 | (red & 255) << 16 | (green & 255) << 8 | (blue & 255));
  }

  public int getRed() {
    return (this.rgb & 0xff0000) >> 16;
  }

  public int getGreen() {
    return (this.rgb & 0x00ff00) >> 8;
  }

  public int getBlue() {
    return this.rgb & 0x0000ff;
  }

  public int getRgb() {
    return this.rgb;
  }

  public void setRgb(int rgb) {
    this.rgb = rgb;
    this.setHsb();
  }

  public int getAlpha() {
    return this.alpha;
  }

  public void setAlpha(int alpha) {
    this.alpha = alpha;
  }

  public int getHue() {
    return this.hue;
  }

  public void setHue(int hue) {
    this.hue = hue;
  }

  public int getSaturation() {
    return this.saturation;
  }

  public void setSaturation(int saturation) {
    this.saturation = saturation;
  }

  public int getBrightness() {
    return this.brightness;
  }

  public void setBrightness(int brightness) {
    this.brightness = brightness;
  }

  public int getColor() {
    return (this.alpha << 24) | this.rgb;
  }

  public void setColor(int color) {
    this.rgb = color & 0x00ffffff;
    this.alpha = (short) ((color & 0xff000000) >>> 24);
    this.setHsb();
  }

  public void setHsb() {
    int[] hsb = this.rgbToHsb(this.rgb);
    this.hue = hsb[0];
    this.saturation = hsb[1];
    this.brightness = hsb[2];
  }

  public void setRgb() {
    this.rgb = this.hsbToRgb(this.hue, this.saturation, this.brightness);
  }

  public int hsbToRgb(int hue, int saturation, int brightness) {
    return Color.HSBtoRGB(hue, saturation, brightness);
  }

  public int[] rgbToHsb(int rgb) {
    float red = ((rgb & 0xff0000) >> 16) / 255F;
    float green = ((rgb & 0x00ff00) >> 8) / 255F;
    float blue = (rgb & 0x0000ff) / 255F;

    float min = Math.min(Math.min(red, green), blue);
    float max = Math.max(Math.max(red, green), blue);
    float delta = min - max;
    float saturation = delta / min;

    float hue;
    if (min == red) {
      hue = ((green - blue) / delta);
      while (hue < 0) {
        hue += 6;
      }

      hue %= 6;
    } else if (min == green) {
      hue = ((blue - red) / delta) + 2;
    } else {
      hue = ((red - green) / delta) + 4;
    }

    hue *= 60;
    return new int[]{(int) hue, (int) (saturation * 100), (int) (min * 100)};
  }
}
