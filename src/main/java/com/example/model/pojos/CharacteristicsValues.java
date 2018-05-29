package com.example.model.pojos;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class CharacteristicsValues {
    private String name;
    private Set<Characteristic> values;

    public CharacteristicsValues(String name) {
        this.name = name;
        this.values = new TreeSet<>((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) return -1;
            return o1.getValue().compareToIgnoreCase(o2.getValue());
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Characteristic> getValues() {
        return Collections.unmodifiableSet(values);
    }

    public void addValue(Characteristic value) {
        this.values.add(value);
    }

    public void removeValue(Characteristic characteristicToRemove) {
        Characteristic characteristic = null;
        for (Characteristic character : values) {
            if (character.getName().equalsIgnoreCase(characteristicToRemove.getName()))
                characteristic = character;
        }
        this.values.remove(characteristic);
    }

    public void setValues(Set<Characteristic> values) {
        this.values = values;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CharacteristicsValues: name : ");
        sb.append(name);
        for (Characteristic value : values) {
            sb.append("\n\t");
            sb.append(value.getValue());
        }
        return sb.toString();
    }
}
