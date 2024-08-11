package com.edu.algorithms;

import com.edu.Utility;
import javafx.scene.shape.Rectangle;

public class QuickSort implements SortAlgorithm {

    private int operationCount = 0;
    private int low = 0;
    private int high = 0;
    private boolean initialized = false;
    private int[] stack;
    private int top;

    @Override
    public boolean sortStep(Rectangle[] bars, boolean stepByStep) {
        if (!initialized) {
            low = 0;
            high = bars.length - 1;
            stack = new int[high - low + 1];
            top = -1;
            stack[++top] = low;
            stack[++top] = high;
            initialized = true;
        }

        if (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int pivotIndex = partition(bars, low, high);

            if (pivotIndex - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
            }

            if (pivotIndex + 1 < high) {
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }

            if (stepByStep) {
                return false;
            }
        } else {
            return true;
        }

        return false;
    }

    private int partition(Rectangle[] bars, int low, int high) {
        double pivot = bars[high].getHeight();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            operationCount++;
            if (bars[j].getHeight() < pivot) {
                i++;
                Utility.highlightBars(bars, i, j);
                swap(bars, i, j);
            }
        }
        swap(bars, i + 1, high);
        return i + 1;
    }

    private void swap(Rectangle[] bars, int i, int j) {
        double tempHeight = bars[i].getHeight();
        bars[i].setHeight(bars[j].getHeight());
        bars[j].setHeight(tempHeight);
    }

    @Override
    public void reset() {
        initialized = false;
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
        return "Quick Sort";
    }

    @Override
    public String toString() {
        return getName();
    }
}

