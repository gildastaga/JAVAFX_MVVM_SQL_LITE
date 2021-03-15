package model;




public class Card {

    private String name;
    private  Column column;

    public Card( String name,Column column) {
        this.column= column;
        this.name=name;
    }


    public Column getColumn() {
        return column;
    }

    public final String getName() {
        return name;
    }

    public void setNameCard(String name) {
        this.name = name;
    }

    public int getPosition() {
        return this.getColumn ().getCards ().indexOf (this);
    }
    public void setColumn(Column column){
        this.column =column;
    }


    @Override
    public String toString() {
        return name+" "+getPosition();
    }


}
