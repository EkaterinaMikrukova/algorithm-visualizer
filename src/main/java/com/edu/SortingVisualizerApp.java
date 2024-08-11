package com.edu;

import com.edu.algorithms.BubbleSort;
import com.edu.algorithms.InsertionSort;
import com.edu.algorithms.QuickSort;
import com.edu.algorithms.SortAlgorithm;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SortingVisualizerApp extends Application {

    private static final int NUM_BARS = 50;
    private static final int MAX_HEIGHT = 200;
    private static final int BAR_WIDTH = 10;
    private static final int BAR_SPACING = 2;
    private Rectangle[] bars;
    private SortAlgorithm selectedAlgorithm;
    private boolean stepByStep = true;
    private Timeline timeline;
    private Label operationCountLabel;
    private Button startButton;
    private Button stepButton;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        createBars(root);
        VBox controlsWrapper = createControls();
        root.setBottom(controlsWrapper);

        Scene scene = new Scene(root, NUM_BARS * (BAR_WIDTH + BAR_SPACING), MAX_HEIGHT + 100);
        scene.getStylesheets().add(getClass().getResource("/darcula.css").toExternalForm());
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBars(BorderPane root) {
        HBox barBox = new HBox(BAR_SPACING);
        barBox.setStyle("-fx-alignment: bottom-left; -fx-padding: 0 0 10 0;");
        bars = new Rectangle[NUM_BARS];
        populateAndShuffleBars();

        for (Rectangle bar : bars) {
            DropShadow shadow = new DropShadow(5, Color.web("#282A36"));
            bar.setEffect(shadow);
            barBox.getChildren().add(bar);
        }
        root.setCenter(barBox);
    }

    private VBox createControls() {
        ComboBox<SortAlgorithm> algorithmSelector = createAlgorithmSelector();
        startButton = createStartButton();
        Button stopButton = createStopButton();
        Button shuffleButton = createShuffleButton();
        stepButton = createStepButton();

        HBox modeControls = createModeControls();

        operationCountLabel = new Label("Операции: 0");
        operationCountLabel.setId("operation-count-label");

        HBox controls = new HBox(10, algorithmSelector, startButton, stopButton, shuffleButton, stepButton);
        return new VBox(10, controls, modeControls, operationCountLabel);
    }

    private ComboBox<SortAlgorithm> createAlgorithmSelector() {
        ComboBox<SortAlgorithm> algorithmSelector = new ComboBox<>();
        algorithmSelector.getItems().addAll(new BubbleSort(), new InsertionSort(), new QuickSort());
        algorithmSelector.getSelectionModel().selectFirst();
        algorithmSelector.setStyle("-fx-background-color: #fff;");
        selectedAlgorithm = algorithmSelector.getValue();
        algorithmSelector.setOnAction(e -> selectedAlgorithm = algorithmSelector.getValue());
        return algorithmSelector;
    }

    private Button createStartButton() {
        Button startButton = new Button("Старт");
        startButton.setOnAction(e -> {
            if (selectedAlgorithm != null) {
                handleStartButton();
            }
        });
        return startButton;
    }

    private Button createStopButton() {
        Button stopButton = new Button("Стоп");
        stopButton.setOnAction(e -> stopSort());
        return stopButton;
    }

    private Button createShuffleButton() {
        Button shuffleButton = new Button("Перемешать");
        shuffleButton.setOnAction(e -> {
            resetBars();
            clearBarEffects();
        });
        return shuffleButton;
    }

    private Button createStepButton() {
        Button stepButton = new Button("Шаг");
        stepButton.setOnAction(e -> stepAlgorithm());
        return stepButton;
    }

    private HBox createModeControls() {
        RadioButton stepRadio = new RadioButton("Пошаговый");
        RadioButton continuousRadio = new RadioButton("Непрерывный");

        ToggleGroup modeToggleGroup = new ToggleGroup();
        stepRadio.setToggleGroup(modeToggleGroup);
        continuousRadio.setToggleGroup(modeToggleGroup);
        stepRadio.setSelected(true);

        modeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            stepByStep = stepRadio.isSelected();
            if (timeline != null) {
                timeline.stop();
                timeline = createTimeline();
                if (!stepByStep && startButton.isDisabled()) {
                    timeline.play();
                }
            }
            stepButton.setDisable(!stepByStep);
            startButton.setDisable(stepByStep);
        });

        return new HBox(10, stepRadio, continuousRadio);
    }

    private void handleStartButton() {
        startButton.setDisable(true);
        selectedAlgorithm.resetOperationCount();

        if (timeline == null || timeline.getStatus() == Timeline.Status.STOPPED) {
            timeline = createTimeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        } else if (timeline.getStatus() == Timeline.Status.PAUSED && stepByStep) {
            timeline.play();
        }
    }

    private void stopSort() {
        if (timeline != null) {
            timeline.stop();
            startButton.setDisable(false);
        }
    }

    private void stepAlgorithm() {
        if (selectedAlgorithm != null && stepByStep) {
            boolean sorted = selectedAlgorithm.sortStep(bars, stepByStep);
            updateOperationCount();

            if (sorted) {
                stopSort();
            }
        }
    }

    private Timeline createTimeline() {
        if (timeline != null) {
            timeline.stop();
        }

        return new Timeline(new KeyFrame(Duration.millis(stepByStep ? 500 : 100), event -> {
            boolean sorted = selectedAlgorithm.sortStep(bars, false);
            updateOperationCount();

            if (sorted) {
                stopSort();
            } else if (stepByStep) {
                timeline.pause();
            }
        }));
    }

    private void updateOperationCount() {
        operationCountLabel.setText("Операции: " + selectedAlgorithm.getOperationCount());
    }

    private void populateAndShuffleBars() {
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < NUM_BARS; i++) {
            heights.add((int) (Math.random() * MAX_HEIGHT));
        }
        Collections.shuffle(heights);
        for (int i = 0; i < NUM_BARS; i++) {
            bars[i] = new Rectangle(BAR_WIDTH, heights.get(i), Color.web("#6272A4"));
        }
    }

    private void resetBars() {
        if (timeline != null) {
            timeline.stop();
        }
        List<Integer> heights = new ArrayList<>();
        for (Rectangle bar : bars) {
            heights.add((int) bar.getHeight());
        }
        Collections.shuffle(heights);
        for (int i = 0; i < NUM_BARS; i++) {
            bars[i].setHeight(heights.get(i));
        }
        if (selectedAlgorithm != null) {
            selectedAlgorithm.reset();
            operationCountLabel.setText("Операции: 0");
        }
    }

    private void clearBarEffects() {
        for (Rectangle bar : bars) {
            bar.setFill(Color.web("#6272A4"));
            bar.setEffect(null);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
