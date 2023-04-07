package com.example.lab1.view;

import com.example.lab1.entity.Rabbit;

import java.util.Vector;

public interface IRabbitController {
    void updateRabbits(Vector<Rabbit> rabbits);

    void updateStats(int simulationTime);

    void showStatsDialog(int simulationTime, int defaultRabbitsCount, int albinoRabbitsCount);

    void showErrorDialog(String errorMessage);

    void update(double elapsed, double frameTime);

    void setDefaultRabbitTextField(String s);

    void setAlbinoRabbitTextField(String s);

    void setDefaultRabbitLifeTimeTextField(String s);
    void setAlbinoRabbitLifeTimeTextField(String s);

    void showCurrentObjectsDialog(Vector<Rabbit> rabbits);
}
