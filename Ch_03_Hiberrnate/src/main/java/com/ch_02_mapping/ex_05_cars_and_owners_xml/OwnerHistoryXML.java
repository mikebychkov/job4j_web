package com.ch_02_mapping.ex_05_cars_and_owners_xml;

import java.util.Objects;

public class OwnerHistoryXML {
    private int id;
    private DriverXML driver;
    private CarXML car;

    public OwnerHistoryXML() {
    }

    public OwnerHistoryXML(DriverXML driver, CarXML car) {
        this.driver = driver;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DriverXML getDriver() {
        return driver;
    }

    public void setDriver(DriverXML driver) {
        this.driver = driver;
    }

    public CarXML getCar() {
        return car;
    }

    public void setCar(CarXML car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OwnerHistoryXML that = (OwnerHistoryXML) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
