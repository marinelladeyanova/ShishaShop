package com.example.model.pojos;

public class Characteristic {
    private int id;
    private String name;
    private String value;


    public Characteristic(String name) {
        this.name = name;
    }

    public Characteristic(String name, String value) {
        this(name);
        this.value = value;
    }

    public Characteristic(int id, String name, String value) {
        this(name, value);
        this.id = id;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
