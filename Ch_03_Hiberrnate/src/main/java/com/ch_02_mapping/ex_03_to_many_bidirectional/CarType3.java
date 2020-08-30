package com.ch_02_mapping.ex_03_to_many_bidirectional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarType3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car3> cars = new ArrayList<>();

    public void addCar(Car3 car) {
        cars.add(car);
    }

    public CarType3() {
    }

    public CarType3(String name) {
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

    public List<Car3> getCars() {
        return cars;
    }
}
