package com.example.lab1.view;

import com.example.lab1.core.Updater;
import com.example.lab1.entity.Rabbit;
import com.example.lab1.presenter.IRabbitPresenter;
import com.example.lab1.presenter.RabbitPresenter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;
import java.util.Timer;
import java.util.Vector;

public class RabbitController implements IRabbitController {

    private final int FPS = 120;
    private final IRabbitPresenter presenter = new RabbitPresenter(this);
    private Timer timer;

    @FXML
    private Pane pane;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private CheckBox showInfoCheckBox;
    @FXML
    private RadioButton showTimeRadio;
    @FXML
    private RadioButton hideTimeRadio;
    @FXML
    private TextField defaultRabbitIntervalTextField;
    @FXML
    private TextField albinoRabbitIntervalTextField;
    @FXML
    private TextField defaultLifeTimeTextField;
    @FXML
    private TextField albinoLifeTimeTextField;
    @FXML
    private Slider defaultRabbitProbabilitySlider;
    @FXML
    private Slider albinoRabbitPercentageSlider;
    @FXML
    private Button currentObjectsButton;
    @FXML
    private MenuItem startMenuItem;
    @FXML
    private MenuItem stopMenuItem;
    @FXML
    private CheckBox defaultRabbitAICheckBox;
    @FXML
    private CheckBox albinoRabbitAICheckBox;

    private final ToggleGroup radioGroup = new ToggleGroup();
    private final Text simulationTimeText = new Text();

    @FXML
    private void initialize() {
        System.out.println("initialize");
        setupViews();
        setListeners();
    }

    private void setupViews() {
        simulationTimeText.setFont(Font.font("comic sans ms", 20));
        simulationTimeText.setFill(Color.RED);
        simulationTimeText.setX(50);
        simulationTimeText.setY(50);

        stopButton.setDisable(true);

        showTimeRadio.setToggleGroup(radioGroup);
        hideTimeRadio.setToggleGroup(radioGroup);
        showTimeRadio.fire();

        defaultRabbitAICheckBox.setDisable(true);
        albinoRabbitAICheckBox.setDisable(true);
    }


    private void setListeners() {
        startButton.setOnMouseClicked(mouseEvent -> startSimulation());

        stopButton.setOnMouseClicked(mouseEvent -> stopSimulation());

        radioGroup.selectedToggleProperty().addListener((changed, oldValue, newValue) -> {
            RadioButton selectedRadio = (RadioButton) newValue;
            simulationTimeText.setVisible(selectedRadio.getId().equals("showTimeRadio"));
        });

        startMenuItem.setOnAction(actionEvent -> startSimulation());

        stopMenuItem.setOnAction(actionEvent -> stopSimulation());

        currentObjectsButton.setOnAction(actionEvent -> showCurrentObjects());

        defaultRabbitIntervalTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!presenter.checkInput(newValue)) {
                defaultRabbitIntervalTextField.setText(oldValue);
            }
        });

        albinoRabbitIntervalTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!presenter.checkInput(newValue)) {
                albinoRabbitIntervalTextField.setText(oldValue);
            }
        });

        defaultLifeTimeTextField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!presenter.checkInput(newValue)) {
                defaultLifeTimeTextField.setText(oldValue);
            }
        }));

        albinoLifeTimeTextField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!presenter.checkInput(newValue)) {
                albinoLifeTimeTextField.setText(oldValue);
            }
        }));

        defaultRabbitAICheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            presenter.setDefaultRabbitAIEnable(newValue);
        });

        albinoRabbitAICheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            presenter.setAlbinoRabbitAIEnable(newValue);
        });
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case B -> startSimulation();
            case E -> stopSimulation();
            case T -> {
                if (simulationTimeText.isVisible()) {
                    hideTimeRadio.fire();
                } else {
                    showTimeRadio.fire();
                }
            }
        }
    }

    private void showCurrentObjects() {
        if (!canStopTimer()) return;
        stopTimer();
        presenter.stopSimulation();
        presenter.showCurrentObjects();
    }

    private void startSimulation() {
        if (!canStartTimer()) return;
        presenter.resetAll();
        presenter.setParams(
                defaultRabbitIntervalTextField.getText(),
                albinoRabbitIntervalTextField.getText(),
                defaultRabbitProbabilitySlider.getValue(),
                albinoRabbitPercentageSlider.getValue(),
                defaultLifeTimeTextField.getText(),
                albinoLifeTimeTextField.getText());

        startButton.setDisable(true);
        stopButton.setDisable(false);

        defaultRabbitAICheckBox.setDisable(false);
        albinoRabbitAICheckBox.setDisable(false);

        defaultRabbitAICheckBox.setSelected(true);
        albinoRabbitAICheckBox.setSelected(true);

        presenter.startSimulation();

        startTimer();
    }

    private void stopSimulation() {
        if (!canStopTimer()) return;
        stopTimer();
        presenter.showStatsDialog();
    }

    private void onStopSimulation() {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        showTimeRadio.fire();

        defaultRabbitAICheckBox.setDisable(true);
        albinoRabbitAICheckBox.setDisable(true);

        presenter.stopSimulation();
    }

    @Override
    public void updateRabbits(Vector<Rabbit> rabbits) {
        pane.getChildren().clear();
        for (Rabbit r : rabbits) {
            ImageView image = new ImageView(r.getImage());
            image.setX(r.getX());
            image.setY(r.getY());
            image.setFitWidth(r.getSize());
            image.setFitHeight(r.getSize());
            pane.getChildren().add(image);
        }
    }

    @Override
    public void updateStats(int simulationTime) {
        simulationTimeText.setText("Simulation time: " + simulationTime);
        pane.getChildren().add(simulationTimeText);
    }

    @Override
    public void showStatsDialog(int simulationTime, int defaultRabbitsCount, int albinoRabbitsCount) {
        if (showInfoCheckBox.isSelected()) {
            presenter.stopSimulation();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Rabbit!");
            dialog.setHeaderText("Statistics");
            dialog.setContentText("Simulation time: " + simulationTime + "\n" +
                    "Default rabbits count: " + defaultRabbitsCount + "\n" +
                    "Albino rabbits count: " + albinoRabbitsCount);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                System.out.println("OK (ENDING)");
                onStopSimulation();
            } else {
                presenter.startSimulation();
                continueTimer();
            }
        } else {
            onStopSimulation();
        }
    }

    @Override
    public void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Rabbit!");
        alert.setHeaderText("Warning!");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void showCurrentObjectsDialog(Vector<Rabbit> rabbits) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Rabbit!");
        dialog.setHeaderText("Current Objects");
        ListView<Rabbit> listView = new ListView<>(FXCollections.observableArrayList(rabbits));
        dialog.getDialogPane().setContent(listView);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton);

        dialog.showAndWait();

        presenter.startSimulation();
        continueTimer();
    }

    @Override
    public void update(double elapsed, double frameTime) {
        presenter.update(elapsed);
    }

    @Override
    public void setDefaultRabbitTextField(String s) {
        defaultRabbitIntervalTextField.setText(s);
    }

    @Override
    public void setAlbinoRabbitTextField(String s) {
        albinoRabbitIntervalTextField.setText(s);
    }

    @Override
    public void setDefaultRabbitLifeTimeTextField(String s) {
        defaultLifeTimeTextField.setText(s);
    }

    @Override
    public void setAlbinoRabbitLifeTimeTextField(String s) {
        albinoLifeTimeTextField.setText(s);
    }

    private void startTimer() {
        if (timer != null) return;
        timer = new Timer();
        timer.schedule(new Updater(this, 0), 0, 1000/FPS);
    }

    private void continueTimer() {
        if (timer != null) return;
        timer = new Timer();
        timer.schedule(new Updater(this, presenter.getSimulationTime()), 0, 1000/FPS);
    }

    private void stopTimer() {
        if (timer == null) return;
        timer.cancel();
        timer = null;
    }

    private boolean canStartTimer() {
        return timer == null;
    }

    private boolean canStopTimer() {
        return timer != null;
    }
}