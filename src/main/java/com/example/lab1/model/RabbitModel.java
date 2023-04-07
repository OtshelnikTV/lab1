package com.example.lab1.model;

import com.example.lab1.core.Habitat;
import com.example.lab1.entity.Rabbit;

import java.util.Vector;

public class RabbitModel implements IRabbitModel {

    private final Habitat habitat = Habitat.getInstance();
    @Override
    public int getSimulationTime() {
        return habitat.getSimulationTime();
    }

    @Override
    public Vector<Rabbit> getRabbits() {
        return habitat.getRabbits();
    }

    @Override
    public void setParams(
            int defaultRabbitInterval,
            int albinoRabbitInterval,
            int defaultRabbitProbability,
            int albinoRabbitPercentage,
            int defaultRabbitLifeTime,
            int albinoRabbitLifeTime) {

        habitat.setParams(
                defaultRabbitInterval,
                albinoRabbitInterval,
                defaultRabbitProbability,
                albinoRabbitPercentage,
                defaultRabbitLifeTime,
                albinoRabbitLifeTime);
    }

    @Override
    public void update(double elapsed) {
        if ((int) elapsed != Habitat.getInstance().getSimulationTime()) {
            habitat.update(elapsed);
        }
    }

    @Override
    public void resetAll() {
        habitat.resetAll();
    }

    @Override
    public void setDefaultRabbitAIEnable(Boolean isEnable) {
        habitat.setDefaultRabbitAIEnable(isEnable);
    }

    @Override
    public void setAlbinoRabbitAIEnable(Boolean isEnable) {
        habitat.setAlbinoRabbitAIEnable(isEnable);
    }

    @Override
    public void stopSimulation() {
        habitat.setAlbinoRabbitAIEnable(false);
        habitat.setDefaultRabbitAIEnable(false);
    }

    @Override
    public void startSimulation() {
        habitat.setAlbinoRabbitAIEnable(true);
        habitat.setDefaultRabbitAIEnable(true);
    }
}