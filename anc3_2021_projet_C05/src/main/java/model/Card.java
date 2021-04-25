package model;




public class Card {
    private int id;
    private String name;
    private  Column column;
    private int columnId;

    public Card( String name,Column column) {
        this.column= column;
        this.name=name;
    }

    public Card(int id, String name, int columnId) {
        this.id = id;
        this.name = name;
        this.columnId = columnId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
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
