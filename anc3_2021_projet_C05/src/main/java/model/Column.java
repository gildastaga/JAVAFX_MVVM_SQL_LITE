package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Column implements Comparable<Column>{

    private final String name;
    private ObservableSet<Card> CartePerColumn;//= FXCollections.observableSet(new TreeSet<> ());;
    private final IntegerProperty size = new SimpleIntegerProperty ();

    public Column(String name){
        this.name = name;
        CartePerColumn = FXCollections.observableSet(new TreeSet<> ());

    }

    public final String getName() {
        return name;
    }

    public Set<Card> getCartePerColumn() {
        return CartePerColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column col = (Column) o;
        return name != null ? name.equals(col.name): col.name==null ;
    }

    @Override
    public int hashCode() {
        return name != null? name.hashCode() : 0 ;
    }

    @Override
    public int compareTo(Column o) {
        return this.name.compareToIgnoreCase(o.name);
    }
    @Override
    public String toString() {
        return name;
    }

    public boolean addCard(Card card) {
        return  CartePerColumn.add(card);
    }

    int nbCard() {
        return CartePerColumn.size();
    }

    boolean removeCard(Card card) {
        return CartePerColumn.remove(card);
    }

    boolean existsCard(Card card) {
        return CartePerColumn.contains(card);
    }

    public final Set<Card> getCard() {
        return Collections.unmodifiableSet(CartePerColumn);
    }

    public IntegerProperty sizeProperty() {
        return size;
    }
}
