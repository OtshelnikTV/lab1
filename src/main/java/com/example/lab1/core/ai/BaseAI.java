package com.example.lab1.core.ai;

import com.example.lab1.view.RabbitController;

abstract class BaseAI extends Thread {
    private volatile boolean isActive = false;

    public synchronized void startAI() {
        isActive = true;
        notify();
    }

    public synchronized void stopAI() {
        isActive = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this){
                    if (!isActive) {
                        wait();
                    }
                }
                calculateMovement();
                Thread.sleep(1000/ RabbitController.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setAIPriority(int priority) {
        setPriority(priority);
    }

    public int getAIPriority() {
        return getPriority();
    }
    abstract void calculateMovement();

}
