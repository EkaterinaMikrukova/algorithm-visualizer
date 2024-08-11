package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class BubbleSort implements SortAlgorithm {

    private int operationCount = 0;
    private int currentIndex = 0;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        if (currentIndex >= bars.length - 1) {
            return true;
        }

        boolean swapped = false;

        for (int j = 0; j < bars.length - 1 - currentIndex; j++) {
            if (bars[j].getHeight() > bars[j + 1].getHeight()) {
                Utility.highlightBars(bars, j, j + 1);
                double tempHeight = bars[j].getHeight();
                bars[j].setHeight(bars[j + 1].getHeight());
                bars[j + 1].setHeight(tempHeight);

                swapped = true;
            }

            operationCount++;

            if (stepByStep) {
                return false;
            }
        }

        currentIndex++;
        return !swapped;
    }

    @Override
    public void reset() {
        currentIndex = 0;
        resetOperationCount();
    }

    @Override
    public int getOperationCount() {
        return operationCount;
    }

    @Override
    public void resetOperationCount() {
        operationCount = 0;
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}
