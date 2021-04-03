package model;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Set;
import java.util.TreeSet;

public class Column {
    private String nameC;
    private Set<Carte> listcat;

    public Column(String nameC) {
        this.nameC = nameC;
        this.listcat = new TreeSet<>();
    }

    public String getNameC() {
        return nameC;
    }

    public Set<Carte> getListcat() {
        return listcat;
    }


    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public void setListcat(Set<Carte> listcat) {
        this.listcat = listcat;
    }
}
=======
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

=======
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

>>>>>>> recap
public class Column {

    private String name;
    private  ObservableList<Card> lsCards;
<<<<<<< HEAD
    private int position ;

    public Column(String name, int position) {
        this.name = name;
        this.position = position;
=======
    private  Board board;


    public Column(String name,Board board) {
        this.name = name;
        this.board = board;
>>>>>>> recap
        lsCards = FXCollections.observableList(new ArrayList<>());
    }

    /**************************************************  configure column **********************************************************/

    public final String getName() {
        return name;
    }

<<<<<<< HEAD
    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
=======
    public Board getBoard() {
        return board;
    }

    public int getposition(){
        return  this.getBoard ().getColumns ().indexOf (this);
    }

    public void setName(String name) {
        this.name = name;
>>>>>>> recap
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

<<<<<<< HEAD
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

}
>>>>>>> StaticView
=======
    public ObservableList<Card> getCards() {
        return lsCards;
    }

    /**************************************************  configure swapCard  **********************************************************/

    public void swapCardDown(int index) {
        Collections.swap (lsCards,index,(index+1));
    }

    public void swapCardUp(int index) {
        Collections.swap (lsCards,(index-1),index);
    }

    public void swapCardRight(Column column , Card card) {
        Column that = this.board.getColumn( (column.getposition ()+ 1));
        that.addCard(card);
        column.removeCard(card);
        card.setColumn (that);
    }

    public void swapCardLeft(Column column, Card card ) {
        Column that = this.board.getColumn ((column.getposition () - 1));
        that.addCard(card);
        column.removeCard(card);
        card.setColumn (that);
    }

}
>>>>>>> recap
