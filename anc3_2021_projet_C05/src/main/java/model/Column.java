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
    private  Board board;


    public Column(String name, int position,Board board) {
        this.name = name;
        this.position = position;
        this.board = board;
        lsCards = FXCollections.observableList(new ArrayList<>());
    }

    /**************************************************  configure column **********************************************************/

    public final String getName() {
        return name;
    }

    public void setNameColumn(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }
   /* public int getposition(){
        return  this.getBoard ().getColonne ().indexOf (this);
    }*/

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column column = (Column) o;
        return getPosition () == column.getPosition () &&
                Objects.equals (getName (), column.getName ());
    }


    @Override
    public int hashCode() {
        return Objects.hash (getName (), getPosition ());
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

    /**************************************************  configure swapCard  **********************************************************/

    public void swapCardDown(int index) {
        Card card = getCard(index);
        Card that = this.getCard(index + 1);
        if(that.getPosition () < lsCards.size()) {
            card.setPosition(index + 1);
            that.setPosition(index);
            System.out.println (lsCards);
        }
        Collections.swap (lsCards,index,(index+1));
    }

    public void swapCardUp(int index) {
        Card card = getCard(index);
        Card that = this.getCard(index - 1);
        if(that.getPosition() >= this.getCard(0).getPosition()) {
            card.setPosition(index - 1);
            that.setPosition(index);
            System.out.println (lsCards+"up");
        }
        Collections.swap (lsCards,(index-1),index);
    }
    public void swapCardRight(int indexCard, int index) {
        Column that = this.board.getColumn(index + 1);
        Column column = this.board.getColumn(index);
        Card card = column.getCard(indexCard);
        card.setPosition(that.getCards().size());
        that.addCard(card);
        column.removeCard(card);
    }

    public void swapCardLeft(int indexcol, int indexCard ) {
        Column that = this.board.getColumn (indexcol - 1);
        System.out.println (that);
        Column column = this.board.getColumn(indexcol);
        System.out.println (column);
        Card card = this.lsCards.get (indexCard);
        System.out.println (card);
        card.setPosition(that.getCards().size());
        that.addCard(card);
        column.removeCard(card);
    }

}