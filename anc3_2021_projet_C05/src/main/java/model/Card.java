package model;

import java.util.Comparator;

class SortByPosition implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o1.getPosition() - o2.getPosition();
    }
}

public class Card {

    private String name;
    private int position ;

    public Card( String name, int position) {
        this.name=name;
        this.position = position;
    }

    public final String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
