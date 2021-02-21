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

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Column> getColumns() {
       // Collections.sort(columns, new SortColumnByPosition());
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

    public void swapCardUp(Card card, int index) {
        this.getColumn(index).swapCardUp(card);
    }

    public void swapCardDown(Card card, int index) {
        this.getColumn(index).swapCardDown(card);
    }
    public void swapColleft(int index) {
        Collections.swap(columns, index, index-1);
    }
    public void swapColright( int index) {
        Collections.swap(columns, index, index+1);
    }
    /**************************************************  configure card **********************************************************/

    public Card getCard(int indexColumn, int indexCard) {
        return this.getColumn(indexColumn).getCard(indexCard);
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
