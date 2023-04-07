package com.example.lab1.presenter;

public interface IRabbitPresenter {
    void setParams(
            String defaultRabbitInterval,
            String albinoRabbitInterval,
            double defaultRabbitProbability,
            double albinoRabbitPercentage,
            String defaultRabbitLifeTime,
            String albinoRabbitLifeTime);

    void showStatsDialog();

    void update(double elapsed);

    void resetAll();

    int getSimulationTime();

    boolean checkInput(String input);

    void showCurrentObjects();

    void setDefaultRabbitAIEnable(Boolean isEnable);

    void setAlbinoRabbitAIEnable(Boolean isEnable);

    void stopSimulation();

    void startSimulation();
}
