package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.*;

class SortColumnByPosition implements Comparator<Column>, Iterable<Column> {

    @Override
    public int compare(Column o1, Column o2) {
        return o1.getPosition() - o2.getPosition();
    }

    @Override
    public Iterator<Column> iterator() {
        return null;
    }
}

public class Column {

    private String name;
    private  ObservableList<Card> lsCards;
    private int position ;

    public Column(String name, int position) {
        this.name = name;
        this.position = position;
        lsCards = FXCollections.observableList(new ArrayList<>());
    }

    /**************************************************  configure column **********************************************************/

    public final String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }

    /**************************************************  configure card **********************************************************/

    public boolean addCard(Card card) {
        return  lsCards.add(card);
    }

    public boolean removeCard(Card card) {
        return lsCards.remove(card);
    }

    public Card getCardByPosition(int position) {
        int i = 0; boolean q = true;
        while (i < lsCards.size() && lsCards.get(i).getPosition() != position) {
            i++;
        }
        return position == -1 ? null : this.lsCards.get(i);
    }

    public Card getCard(int index) {
        return position == -1 ? null : this.lsCards.get(index);
    }

    public ObservableList<Card> getCards() {
        Collections.sort(lsCards, new SortByPosition());
        return lsCards;
    }

    /**************************************************  configure swapCard up down **********************************************************/
    public void swapCardDown(int index) {
        Card card = getCard(index);
        Card that = this.getCard(index + 1);
        if(this.getCard(index + 1).getPosition() < lsCards.size()) {
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
    }

    /**************************************************  configure swapCard letf right **********************************************************/

    /*public void swapCardRight(int index) {
        Card pos = this.getCard(index);
        Column that = this;
        int c = this.getPosition() + 1;
        that.removeCard(pos);
        that.addCard(pos);


        //if(this.lsCards.get(card.getPosition() + 1).getPosition() < lsCards.size()) {
        //card.setPosition(pos + 1);
        //that.setPosition(pos);
        //}
    }*/

    /*public void swapCardLeft(int index) {
        Card pos = this.getCard(index);
        Column that = this;
        that.removeCard(pos);
        that.addCard(pos);


        Card that = board.getColumn(index - 1) .getCard(card.getPosition());
        // if(this.lsCards.get(card.getPosition() - 1).getPosition() >= this.getCard(0).getPosition()) {
        card.setPosition(pos - 1);
        that.setPosition(pos);
        // }
    }*/
}