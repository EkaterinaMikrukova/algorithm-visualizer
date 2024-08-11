package com.edu.algorithms;

import javafx.scene.shape.Rectangle;

public interface SortAlgorithm {
    boolean sortStep(Rectangle[] bars, boolean stepByStep);
    void reset();
    int getOperationCount();
    void resetOperationCount();
    String getName();
}