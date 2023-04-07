package com.example.lab1.presenter;

import com.example.lab1.entity.AlbinoRabbit;
import com.example.lab1.entity.DefaultRabbit;
import com.example.lab1.entity.Rabbit;
import com.example.lab1.model.IRabbitModel;
import com.example.lab1.model.RabbitModel;
import com.example.lab1.view.IRabbitController;

import java.util.Vector;

public class RabbitPresenter implements IRabbitPresenter {
    private final IRabbitController controller;
    private final IRabbitModel model = new RabbitModel();

    public RabbitPresenter(IRabbitController controller) {
        this.controller = controller;
    }

    @Override
    public void setParams(
            String defaultRabbitInterval,
            String albinoRabbitInterval,
            double defaultRabbitProbability,
            double albinoRabbitPercentage,
            String defaultRabbitLifeTime,
            String albinoRabbitLifeTime) {

        int defaultRabbitIntervalInt = 0;
        int albinoRabbitIntervalInt = 0;
        int defaultRabbitProbabilityInt;
        int albinoRabbitPercentageInt;
        int defaultRabbitLifeTimeInt = 0;
        int albinoRabbitLifeTimeInt = 0;

        if (defaultRabbitInterval.isEmpty()) {
            controller.showErrorDialog("Default rabbit interval is empty");
            controller.setDefaultRabbitTextField("3");
        } else {
            defaultRabbitIntervalInt = Integer.parseInt(defaultRabbitInterval);
        }

        if (albinoRabbitInterval.isEmpty()) {
            controller.showErrorDialog("Albino rabbit interval is empty");
            controller.setAlbinoRabbitTextField("5");
        } else {
            albinoRabbitIntervalInt = Integer.parseInt(albinoRabbitInterval);
        }

        if (defaultRabbitLifeTime.isEmpty()){
            controller.showErrorDialog("Default rabbit life time is empty");
            controller.setDefaultRabbitLifeTimeTextField("30");
        } else {
            defaultRabbitLifeTimeInt = Integer.parseInt(defaultRabbitLifeTime);
        }

        if (albinoRabbitLifeTime.isEmpty()) {
            controller.showErrorDialog("Albino rabbit life time is empty");
            controller.setAlbinoRabbitLifeTimeTextField("20");
        } else {
            albinoRabbitLifeTimeInt = Integer.parseInt(albinoRabbitLifeTime);
        }

        defaultRabbitProbabilityInt = (int) defaultRabbitProbability;
        albinoRabbitPercentageInt = (int) albinoRabbitPercentage;

        model.setParams(
                defaultRabbitIntervalInt,
                albinoRabbitIntervalInt,
                defaultRabbitProbabilityInt,
                albinoRabbitPercentageInt,
                defaultRabbitLifeTimeInt,
                albinoRabbitLifeTimeInt);
    }

    @Override
    public void showStatsDialog() {
        int simulationTime = model.getSimulationTime();
        int defaultRabbitsCount = DefaultRabbit.count;
        int albinoRabbitsCount = AlbinoRabbit.count;

        controller.showStatsDialog(simulationTime, defaultRabbitsCount, albinoRabbitsCount);
    }

    @Override
    public void update(double elapsed) {
        model.update(elapsed);

        Vector<Rabbit> rabbits = model.getRabbits();
        int simulationTime = model.getSimulationTime();

        controller.updateRabbits(rabbits);
        controller.updateStats(simulationTime);
    }

    @Override
    public void showCurrentObjects() {
        Vector<Rabbit> rabbits = model.getRabbits();
        controller.showCurrentObjectsDialog(rabbits);
    }

    @Override
    public void setDefaultRabbitAIEnable(Boolean isEnable) {
        model.setDefaultRabbitAIEnable(isEnable);
    }

    @Override
    public void setAlbinoRabbitAIEnable(Boolean isEnable) {
        model.setAlbinoRabbitAIEnable(isEnable);
    }

    @Override
    public void stopSimulation() {
        model.stopSimulation();
    }

    @Override
    public void startSimulation() {
        model.startSimulation();
    }

    @Override
    public void resetAll() {
        model.resetAll();
    }

    @Override
    public int getSimulationTime() {
        return model.getSimulationTime();
    }

    @Override
    public boolean checkInput(String input) {
        return isNumeric(input);
    }

    private static boolean isNumeric(String str) {
        return str.matches("^[1-9][0-9]*") || str.isEmpty();
    }


}
