package model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;

public class Cartes implements Comparable<Cartes>{
    private final String name;

    public Cartes(String name){
        this.name = name;
    }

    public final String getName() {

        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartes cartes = (Cartes) o;
        return name.equals(cartes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Cartes c) {
        return this.name.compareToIgnoreCase(c.name);
    }

}
