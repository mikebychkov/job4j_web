package com.ch_02_mapping.ex_01_to_one;

import javax.persistence.*;

@Entity
public class Car1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CarType1 type;

    public Car1() {
    }

    public Car1(String name) {
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

    public CarType1 getType() {
        return type;
    }

    public void setType(CarType1 type) {
        this.type = type;
    }
}
