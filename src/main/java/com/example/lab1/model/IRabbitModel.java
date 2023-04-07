package com.example.lab1.model;

import com.example.lab1.entity.Rabbit;

import java.util.ArrayList;
import java.util.Vector;

public interface IRabbitModel {
    Vector<Rabbit> getRabbits();

    int getSimulationTime();

    void setParams(
            int defaultRabbitInterval,
            int albinoRabbitInterval,
            int defaultRabbitProbability,
            int albinoRabbitPercentage,
            int defaultRabbitLifeTime,
            int albinoRabbitLifeTime);


    void update(double elapsed);

    void resetAll();

    void setDefaultRabbitAIEnable(Boolean isEnable);

    void setAlbinoRabbitAIEnable(Boolean isEnable);

    void stopSimulation();

    void startSimulation();
}
