package com.yotwei.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Random;

/**
 * Created by YotWei on 2018/4/1.
 */
public class Util {
    public static BufferedImage compressImage(BufferedImage src, int dstWidth, int dstHeight) {
        BufferedImage compress = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
        compress.getGraphics().drawImage(
                src.getScaledInstance(dstWidth, dstHeight, Image.SCALE_SMOOTH),
                0, 0, dstWidth, dstHeight, null);
        return compress;
    }

    public static class RGB {

        public static int red(int color) {
            return (color & 0xff0000) >> 16;
        }

        public static int green(int color) {
            return (color & 0x00ff00) >> 8;
        }

        public static int blue(int color) {
            return color & 0x0000ff;
        }

        public static int color(int r, int g, int b) {
            return (r << 16) | (g << 8) | b;
        }

        public static int getAverageRGB(BufferedImage img) {
            int r = 0, g = 0, b = 0;
            int size = img.getWidth() * img.getHeight();
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int rgb = img.getRGB(x, y);
                    r += ((rgb & 0xff0000) >> 16);
                    g += ((rgb & 0x00ff00) >> 8);
                    b += (rgb & 0x0000ff);
                }
            }
            r /= size;
            g /= size;
            b /= size;
            return color(r, g, b);
        }

        public static int aligningValue(int c) {
            int r = Math.round(red(c) * 1f / 0x80) * 0x7f;
            int g = Math.round(green(c) * 1f / 0x80) * 0x7f;
            int b = Math.round(blue(c) * 1f / 0x80) * 0x7f;
            return color(r, g, b);
        }
    }

}
