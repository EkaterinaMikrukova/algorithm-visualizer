package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class InsertionSort implements SortAlgorithm {

    private int currentIndex = 1;
    private int innerIndex = 1;
    private int operationCount = 0;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean isStepByStep) {
        if (currentIndex < bars.length) {
            if (innerIndex > 0 && bars[innerIndex].getHeight() < bars[innerIndex - 1].getHeight()) {
                Utility.highlightBars(bars, innerIndex, innerIndex - 1);
                swap(bars, innerIndex, innerIndex - 1);
                innerIndex--;
                operationCount++;
                if (isStepByStep) return false;
            } else {
                Utility.highlightBars(bars, innerIndex, innerIndex);
                currentIndex++;
                innerIndex = currentIndex;
            }
            return false;
        }
        return true;
    }

    @Override
    public void reset() {
        currentIndex = 1;
        innerIndex = 1;
        resetOperationCount();
    }

    private void swap(Rectangle[] bars, int i, int j) {
        double tempHeight = bars[i].getHeight();
        bars[i].setHeight(bars[j].getHeight());
        bars[j].setHeight(tempHeight);
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
        return "Insertion Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}
