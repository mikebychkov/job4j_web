package com.ch_02_mapping.ex_05_cars_and_owners_xml;

import java.util.Objects;

public class EngineXML {
    private int id;
    private String name;

    public EngineXML() {
    }

    public EngineXML(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineXML engine = (EngineXML) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
