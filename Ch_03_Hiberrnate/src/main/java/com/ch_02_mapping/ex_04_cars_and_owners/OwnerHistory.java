package com.ch_02_mapping.ex_04_cars_and_owners;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "owner_history")
public class OwnerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Car car;

    public OwnerHistory() {
    }

    public OwnerHistory(Driver driver, Car car) {
        this.driver = driver;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerHistory that = (OwnerHistory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
