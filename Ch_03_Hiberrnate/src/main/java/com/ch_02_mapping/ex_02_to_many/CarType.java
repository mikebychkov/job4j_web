package com.ch_02_mapping.ex_02_to_many;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarType {
    @Id
    @GeneratedValue
    private int id;

    public CarType() {
    }
}
