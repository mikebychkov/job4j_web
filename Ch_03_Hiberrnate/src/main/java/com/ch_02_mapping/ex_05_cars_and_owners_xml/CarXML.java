package com.ch_02_mapping.ex_05_cars_and_owners_xml;

import java.util.*;

public class CarXML {
    private int id;
    private String name;
    private EngineXML engine;
    private Collection<OwnerHistoryXML> oh = new HashSet<>();

    public void addOwnerHistory(OwnerHistoryXML oh) {
        this.oh.add(oh);
    }

    public CarXML() {
    }

    public CarXML(String name) {
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

    public EngineXML getEngine() {
        return engine;
    }

    public void setEngine(EngineXML engine) {
        this.engine = engine;
    }

    public Collection<OwnerHistoryXML> getOwnerHistory() {
        return oh;
    }

    public Collection<OwnerHistoryXML> getOh() {
        return oh;
    }

    public void setOh(Collection<OwnerHistoryXML> oh) {
        this.oh = oh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarXML car = (CarXML) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
