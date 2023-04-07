package com.example.lab1.entity;

import com.example.lab1.core.Habitat;
import com.example.lab1.view.RabbitController;
import javafx.scene.image.Image;

import java.util.Random;

public class AlbinoRabbit extends Rabbit{
    private static final Image image = new Image(System.getProperty("user.dir") + "\\src\\main\\resources\\assets\\albino_rabbit.png");
    public static int count = 0;
    private double x;
    private double y;
    private Direction direction = Direction.Left;
    private double speed = 300.0 / RabbitController.FPS;
    private final int birthTime;
    private final int lifeTime;
    private final int id;
    private final int size = 50;
    public AlbinoRabbit(int birthTime, int lifeTime, int id) {
        count++;
        Random random = new Random();
        x = random.nextInt(Habitat.getWidth() - size);
        y = random.nextInt(Habitat.getHeight() - size);
        this.birthTime = birthTime;
        this.lifeTime = lifeTime;
        this.id = id;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void updateDirection() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getBirthTime() {
        return birthTime;
    }

    @Override
    public int getLifeTime() {
        return lifeTime;
    }

    public Image getImage() {
        return image;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void die() {
        count--;
    }

    @Override
    public String toString() {
        return birthTime + " - Albino Rabbit";
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
}
