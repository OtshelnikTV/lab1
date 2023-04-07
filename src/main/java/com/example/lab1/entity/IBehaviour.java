package com.example.lab1.entity;

public interface IBehaviour {
    double getX();
    double getY();
    double getSpeed();
    void setX(double x);
    void setY(double y);
    Direction getDirection();
    void setDirection(Direction direction);
    void updateDirection();
}
