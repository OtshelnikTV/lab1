package com.example.lab1.core.ai;

import com.example.lab1.core.Habitat;
import com.example.lab1.entity.DefaultRabbit;
import com.example.lab1.entity.Direction;
import com.example.lab1.entity.Rabbit;
import com.example.lab1.repository.RabbitRepository;

public class DefaultRabbitAI extends BaseAI {
    @Override
    public synchronized void calculateMovement() {
        System.out.println(getName());
        for (Rabbit rabbit : RabbitRepository.getInstance().getVectorWhere(DefaultRabbit.class)) {
            double x = rabbit.getX();
            double y = rabbit.getY();
            double speed = rabbit.getSpeed();
            int fieldWidth = Habitat.getWidth() - rabbit.getSize();
            int fieldHeight = Habitat.getHeight() - rabbit.getSize();

            //check direction
            if (x >= fieldWidth) {
                rabbit.setDirection(Direction.Left);
            } else if (x <= 0) {
                rabbit.setDirection(Direction.Right);
            }

            if (y >= fieldHeight) {
                rabbit.setDirection(Direction.Up);
            } else if (y <= 0) {
                rabbit.setDirection(Direction.Down);
            }

            Direction direction = rabbit.getDirection();
            rabbit.setX(x + (speed * direction.getXBias()));
            rabbit.setY(y + (speed * direction.getYBias()));
        }
    }
}
