package com.ch_02_mapping.ex_03_to_many_bidirectional;

import javax.persistence.*;

@Entity
public class Car3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    private CarType3 type;

    public Car3() {
    }

    public Car3(String name) {
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

    public CarType3 getType() {
        return type;
    }

    public void setType(CarType3 type) {
        this.type = type;
    }
}
