package com.example.lab1.entity;

public interface IBehaviour {
    int getX();
    int getY();
    int getSpeed();
    void setX(int x);
    void setY(int y);
    Direction getDirection();
    void setDirection(Direction direction);
    void updateDirection();
}
