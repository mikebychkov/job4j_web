package com.ch_02_mapping.ex_03_to_many_bidirectional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private int id;

    public Car() {
    }
}
