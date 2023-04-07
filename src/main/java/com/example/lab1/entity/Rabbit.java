package com.example.lab1.entity;

import javafx.scene.image.Image;

public abstract class Rabbit implements IBehaviour{
    public abstract int getId();
    public abstract int getBirthTime();
    public abstract int getLifeTime();
    public abstract Image getImage();
    public abstract int getSize();
    public abstract void die();
}
