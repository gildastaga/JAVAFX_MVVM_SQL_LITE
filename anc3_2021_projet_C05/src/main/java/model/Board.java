package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.*;

public class Board {

    private String name;
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
        Collections.sort(columns, new SortColumnByPosition());
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

    public void swapColumnRight(int index) {
        Column c = this.columns.get(index);
        int pos = c.getPosition();
        Column that = this.columns.get(pos + 1);
        if(this.columns.get(pos + 1).getPosition() < columns.size() ) {
            c.setPosition(pos + 1);
            that.setPosition(pos);
        }
    }

    public void swapColumnLeft(int index) {
        Column c = this.columns.get(index);
        int pos = c.getPosition();
        Column that = this.columns.get(pos - 1);
        if(this.columns.get(pos - 1).getPosition() >= this.getColumn(0).getPosition()) {
            c.setPosition(pos - 1);
            that.setPosition(pos);
        }
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

    /**************************************************  init data **********************************************************/
    private void initData() {
        Card Carte_1 = new Card("Carte 1", 0),
                Carte_2 = new Card("Carte 2", 0),
                Carte_3 = new Card("Carte 3", 1),
                Carte_4 = new Card("Carte 4", 0),
                Carte_5 = new Card("Carte 5", 1),
                Carte_6 = new Card("Carte 6", 2);

        Column à_faire = new Column("à faire", 0),
                à_tester = new Column("à tester", 1),
                en_cours = new Column("en cours", 2),
                en_attente_de_paiement = new Column("en attente de paiement", 3);

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
