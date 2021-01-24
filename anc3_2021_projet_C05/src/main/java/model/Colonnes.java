package model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Colonnes implements Comparable<Colonnes>{
    private final String name;
    private  Set<Cartes> CartePerColumn;

    public Colonnes(String name){
        this.name = name;
        CartePerColumn = new TreeSet<>();
    }

    public final String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colonnes colonnes = (Colonnes) o;
        return name.equals(colonnes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Colonnes o) {
        return this.name.compareToIgnoreCase(o.name);
    }

    @Override
    public String toString() {

        return name;
    }

    boolean addCarte(Cartes cartes) {

        return CartePerColumn.add(cartes);
    }

    int nbCartes() {
        return CartePerColumn.size();
    }

    boolean removeCartes(Cartes carte) {
        return CartePerColumn.remove(carte);
    }

    boolean existsCartes(Cartes carte) {
        return CartePerColumn.contains(carte);
    }

    public final Set<Cartes> getCartes() {
        return Collections.unmodifiableSet(CartePerColumn);
    }

}
