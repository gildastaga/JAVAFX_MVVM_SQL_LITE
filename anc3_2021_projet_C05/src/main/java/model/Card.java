package model;

import java.util.Collections;
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
    private final Column column;

    public Card( String name, int position,Column column) {
        this.column= column;
        this.name=name;
        this.position = position;
    }


    public Column getColumn() {
        return column;
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
        return name+" "+position;
    }

    /*public void swapCardDown(int index) {
        Card card = this.column.getCard(index);
        Card that = this.column.getCard(index + 1);
        if(that.getPosition() < this.column.getCards ().size()) {
            card.setPosition(index + 1);
            that.setPosition(index);
        }

    }

    public void swapCardUp(int index) {
        Card card = getCard(index);
        Card that = this.getCard(index - 1);
        if(this.getCard(index - 1).getPosition() >= this.getCard(0).getPosition()) {
            card.setPosition(index - 1);
            that.setPosition(index);
        }
    }*/
}
