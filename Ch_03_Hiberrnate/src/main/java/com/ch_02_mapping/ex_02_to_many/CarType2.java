package com.ch_02_mapping.ex_02_to_many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarType2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car2> cars = new ArrayList<>();

    public void addCar(Car2 car) {
        cars.add(car);
    }

    public CarType2() {
    }

    public CarType2(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car2> getCars() {
        return cars;
    }
}
