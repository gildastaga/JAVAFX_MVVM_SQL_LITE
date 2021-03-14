package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Board {

    private String name ;
    private final IntegerProperty size = new SimpleIntegerProperty();
    private final ObservableList<Column> columns = FXCollections.observableList( new ArrayList<>());
    private int position = 0;

    public Board(String name) {
        this.name = name;
        initData();
    }

    /**************************************************  configure board **********************************************************/

    public final String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Column> getColumns() {
        //Collections.sort(columns, new SortColumnByPosition());
        return columns;
    }

    /**************************************************  configure column **********************************************************/

    public boolean addColumn (Column col) {
        return columns.add(col);
    }

    public boolean removeColumn (Column col) {
        return columns.remove(col);
    }

    public Column getColumn(int index) {
      return   index == -1 ? null :this.getColumns().get(index);
    }

    public int getposition() {
        return  this.columns.indexOf (this);
    }

    public void swapColumnRight(int index) {
        Column c = this.getColumn (index);
        int pos = c.getPosition();
        Column that = this.getColumn (pos + 1);
        if(that.getPosition() < this.columns.size ()) {
            c.setPosition(pos + 1);
            that.setPosition(pos);
        }
        Collections.swap (columns, index, index + 1);
    }

    public void swapColumnLeft(int index) {
        Column c = this.getColumn (index);
        int pos = c.getPosition();
        Column that = this.getColumn (pos - 1);
        if(that.getPosition() >= this.getColumn(0).getPosition()) {
            c.setPosition(pos - 1);
            that.setPosition(pos);
        }
        Collections.swap (columns, index, index - 1);
    }

    /**************************************************  configure card **********************************************************/

    public Card getCard(int indexColumn, int indexCard) {
        return this.getColumn(indexColumn).getCard(indexCard);
    }

    public void swapCardUp(int indexCard, int index) {
        this.getColumn(index).swapCardUp(indexCard);
    }

    public void swapCardDown(int indexCard, int index) {
        this.getColumn(index).swapCardDown(indexCard);
    }

    /**************************************************  configure swapCard letf right **********************************************************/

    public void swapCardRight(int indexCard, int index) {
        Column that = this.getColumn(index + 1);
        Column column = this.getColumn(index);
        Card card = column.getCard(indexCard);
        card.setPosition(that.getCards().size());
        that.addCard(card);
        column.removeCard(card);
    }

    public void swapCardLeft(int indexCard, int index) {
        Column that = this.getColumn(index - 1);
        Column column = this.getColumn(index);
        Card card = column.getCard(indexCard);
        card.setPosition(that.getCards().size());
        that.addCard(card);
        column.removeCard(card);
    }

    /**************************************************  init data *******************************************************************************/
    private void initData() {


        Column à_faire = new Column("à faire", 0,this),
                à_tester = new Column("à tester", 1,this),
                en_cours = new Column("en cours", 2,this),
                en_attente_de_paiement = new Column("en attente", 3,this);
        Card Carte_1 = new Card("Carte 1", 0,à_faire),
                Carte_2 = new Card("Carte 2", 0,à_tester),
                Carte_3 = new Card("Carte 3", 1,à_tester),
                Carte_4 = new Card("Carte 4", 0,en_cours),
                Carte_5 = new Card("Carte 5", 1,en_cours),
                Carte_6 = new Card("Carte 6", 2,en_cours);

        columns.add(à_faire);
        columns.add(à_tester);
        columns.add(en_cours);
        columns.add(en_attente_de_paiement);

        à_faire.addCard(Carte_1);

        à_tester.addCard(Carte_2);
        à_tester.addCard(Carte_3);

        en_cours.addCard(Carte_4);
        en_cours.addCard(Carte_5);
        en_cours.addCard(Carte_6);

    }
}
