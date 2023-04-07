package com.example.lab1.repository;

import com.example.lab1.entity.AlbinoRabbit;
import com.example.lab1.entity.Rabbit;

import java.util.*;

public class RabbitRepository {

    private static RabbitRepository instance;
    private final Vector<Rabbit> rabbits = new Vector<>();
    private final TreeSet<Integer> rabbitIds = new TreeSet<>();
    private final HashMap<Integer, Integer> rabbitsMap = new HashMap<>();// <id, birthTime>

    public static synchronized RabbitRepository getInstance() {
        if (instance == null) {
            instance = new RabbitRepository();
        }
        return instance;
    }

    private RabbitRepository() {
    }

    public void add(Rabbit rabbit) {
        rabbits.add(rabbit);
        rabbitIds.add(rabbit.getId());
        rabbitsMap.put(rabbit.getId(), rabbit.getBirthTime());
    }

    public void remove(Rabbit rabbit) {
        rabbits.remove(rabbit);
        rabbitIds.remove(rabbit.getId());
        rabbitsMap.remove(rabbit.getId());
    }

    public void clear() {
        rabbits.clear();
        rabbitIds.clear();
        rabbitsMap.clear();
    }

    public int size() {
        return rabbits.size();
    }

    public Rabbit get(int index) {
        return rabbits.get(index);
    }

    public Vector<Rabbit> getVector() {
        return rabbits;
    }

    public <T extends Rabbit> Vector <T> getVectorWhere(Class<T> clazz) {
        Vector<T> filteredRabbits = new Vector<>();
        for (Rabbit rabbit : rabbits) {
            if (clazz.isAssignableFrom(rabbit.getClass())) {
                filteredRabbits.add(clazz.cast(rabbit));
            }
        }
        return filteredRabbits;
    }
}
