package ru.job4j.caraccident.model;

import java.util.Objects;

public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType accidentType;

    public Accident() {
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(int id, String name, String text, String address, AccidentType accidentType) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.accidentType = accidentType;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Accident{" + "id=" + id + ", name='" + name + '\'' + ", text='" + text + '\''
                + ", address='" + address + '\'' + ", accidentType=" + accidentType + '}';
    }
}
