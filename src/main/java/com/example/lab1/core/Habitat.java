package com.example.lab1.core;

import com.example.lab1.entity.AlbinoRabbit;
import com.example.lab1.entity.DefaultRabbit;
import com.example.lab1.entity.Rabbit;
import com.example.lab1.core.ai.AlbinoRabbitAI;
import com.example.lab1.core.ai.DefaultRabbitAI;
import com.example.lab1.repository.RabbitRepository;

import java.util.Random;
import java.util.Vector;

//Singleton
public class Habitat {
    private static Habitat instance;

    public static synchronized Habitat getInstance() {
        if (instance == null) {
            instance = new Habitat();
        }
        return instance;
    }

    private final AlbinoRabbitAI albinoRabbitAI = new AlbinoRabbitAI();
    private final DefaultRabbitAI defaultRabbitAI = new DefaultRabbitAI();
    private static final int width = 600;
    private static final int height = 500;
    private final Random random = new Random();
    private int simulationTime = 0;
    private int defaultRabbitInterval;
    private double defaultRabbitProbability;
    private int albinoRabbitInterval;
    private double albinoRabbitPercentage;
    private int defaultRabbitLifeTime;
    private int albinoRabbitLifeTime;

    private Habitat() {
        defaultRabbitAI.start();
        albinoRabbitAI.start();
    }

    public void setParams(int defaultRabbitInterval, int albinoRabbitInterval, int defaultRabbitProbability, int albinoRabbitPercentage, int defaultRabbitLifeTime, int albinoRabbitLifeTime) {
        this.defaultRabbitInterval = defaultRabbitInterval == 0 ? 3 : defaultRabbitInterval;
        this.albinoRabbitInterval = albinoRabbitInterval == 0 ? 5 : albinoRabbitInterval;
        this.defaultRabbitProbability = defaultRabbitProbability / 100.0;
        this.albinoRabbitPercentage = albinoRabbitPercentage / 100.0;
        this.defaultRabbitLifeTime = defaultRabbitLifeTime == 0 ? 30 : defaultRabbitLifeTime;
        this.albinoRabbitLifeTime = albinoRabbitLifeTime == 0 ? 20 : albinoRabbitLifeTime;
    }

    private void spawnRabbits() {
        if (simulationTime % defaultRabbitInterval == 0 && random.nextDouble(1) < defaultRabbitProbability) {
            spawnDefaultRabbit();
        }

        if (simulationTime % albinoRabbitInterval == 0 && (double) AlbinoRabbit.count / RabbitRepository.getInstance().size() < albinoRabbitPercentage) {
            spawnAlbinoRabbit();
        }
    }

    private void checkRabbits() {
        for (int i = 0; i < RabbitRepository.getInstance().size(); i++) {
            Rabbit rabbit = RabbitRepository.getInstance().get(i);
            if (rabbit.getBirthTime() + rabbit.getLifeTime() < simulationTime) {
                // rabbit die :(
                RabbitRepository.getInstance().remove(rabbit);
                rabbit.die();
            }

            rabbit.updateDirection();
        }
    }

    public void update(double elapsed) {
        simulationTime = (int) elapsed;
        checkRabbits();
        spawnRabbits();
    }

    private void spawnDefaultRabbit() {
        int id = generateId();
        RabbitRepository.getInstance().add(new DefaultRabbit(simulationTime, defaultRabbitLifeTime, id));
    }

    private void spawnAlbinoRabbit() {
        int id = generateId();
        RabbitRepository.getInstance().add(new AlbinoRabbit(simulationTime, albinoRabbitLifeTime, id));
    }

    public void setDefaultRabbitAIEnable(Boolean isEnable) {
        if (isEnable && !defaultRabbitAI.isActive()) {
            defaultRabbitAI.startAI();
        } else {
            if (!isEnable && defaultRabbitAI.isActive()) {
                defaultRabbitAI.stopAI();
            }
        }
    }

    public void setAlbinoRabbitAIEnable(Boolean isEnable) {
        if (isEnable && !albinoRabbitAI.isActive()) {
            albinoRabbitAI.startAI();
        } else {
            if (!isEnable && albinoRabbitAI.isActive()) {
                albinoRabbitAI.stopAI();
            }
        }
    }

    public void resetAll() {
        simulationTime = 0;
        DefaultRabbit.count = 0;
        AlbinoRabbit.count = 0;
        RabbitRepository.getInstance().clear();
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public Vector<Rabbit> getRabbits() {
        return RabbitRepository.getInstance().getVector();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private int generateId() {
        return random.nextInt(1000000);
    }

}
