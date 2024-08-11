package com.edu;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Utility {

    public static void highlightBars(Rectangle[] bars, int firstIndex, int secondIndex) {
        clearBarEffects(bars);

        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.RED);
        borderGlow.setWidth(10);
        borderGlow.setHeight(10);

        bars[firstIndex].setEffect(borderGlow);
        bars[secondIndex].setEffect(borderGlow);

        bars[firstIndex].setFill(Color.web("#ff5555"));
        bars[secondIndex].setFill(Color.web("#ff5555"));
    }

    public static void clearBarEffects(Rectangle[] bars) {
        for (Rectangle bar : bars) {
            bar.setFill(Color.web("#6272A4"));
            bar.setEffect(null);
        }
    }
}
