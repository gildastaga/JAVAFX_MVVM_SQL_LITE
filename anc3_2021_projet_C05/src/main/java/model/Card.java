package model;

<<<<<<< HEAD
import java.util.Comparator;

class SortByPosition implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o1.getPosition() - o2.getPosition();
    }
}
=======


>>>>>>> recap

public class Card {

    private String name;
<<<<<<< HEAD
    private int position ;

    public Card( String name, int position) {
        this.name=name;
        this.position = position;
=======
    private  Column column;

    public Card( String name,Column column) {
        this.column= column;
        this.name=name;
    }


    public Column getColumn() {
        return column;
>>>>>>> recap
    }

    public final String getName() {
        return name;
    }

<<<<<<< HEAD
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setName(String name){
        this.name = name;
=======
    public void setNameCard(String name) {
        this.name = name;
    }

    public int getPosition() {
        return this.getColumn ().getCards ().indexOf (this);
    }

    public void setColumn(Column column){
        this.column =column;
>>>>>>> recap
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return name+" "+position;
    }
=======
        return name+" "+getPosition();
    }


>>>>>>> recap
}
