package com.example.lab1.core.ai;

import com.example.lab1.core.Habitat;
import com.example.lab1.entity.AlbinoRabbit;
import com.example.lab1.entity.Direction;
import com.example.lab1.entity.Rabbit;
import com.example.lab1.repository.RabbitRepository;

public class AlbinoRabbitAI extends BaseAI{

    @Override
    public synchronized void calculateMovement() {
        System.out.println(getName());
        for (Rabbit rabbit : RabbitRepository.getInstance().getVectorWhere(AlbinoRabbit.class)) {
            double x = rabbit.getX();
            double speed = rabbit.getSpeed();
            double fieldWidth = Habitat.getWidth() - rabbit.getSize();

            //check direction
            if (x >= fieldWidth) {
                rabbit.setDirection(Direction.Left);
            } else if (x <= 0) {
                rabbit.setDirection(Direction.Right);
            }

            Direction direction = rabbit.getDirection();
            rabbit.setX(x + (speed * direction.getXBias()));
        }
    }
}
