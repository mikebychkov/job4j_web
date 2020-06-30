package com.ch_05_non_blocking_algorithm.ex_02_non_blocking_cache;

public class Base {
    private int id;
    private String name;
    private int version = 0;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Base(int id, String name, int version) {
        this.id = id;
        this.name = name;
    }

    public static Base of(Base model) {
        return new Base(model.getId(), model.getName(), model.getVersion());
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", version=" + version
                + '}';
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.version++;
    }
}
