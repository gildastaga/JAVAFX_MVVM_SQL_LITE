package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
public class Board {

    private final String name;
    private final IntegerProperty size = new SimpleIntegerProperty();
    private final ObservableSet<Column> columns = FXCollections.observableSet( new TreeSet<>());

    public Board(String name) {
        this.name = name;
        initData();
        //this.size.bind(new SimpleIntegerProperty<>(CartePerColumn).sizeProperty());
    }

    public final String getName() {
        return name;
    }

    public Set<Column> getColonne() {
        return Collections.unmodifiableSet(columns);
    }


    private void initData() {
        Card Carte_1 = new Card("Carte 1"),
                Carte_2 = new Card("Carte 2"),
                Carte_3 = new Card("Carte 3"),
                Carte_4 = new Card("Carte 4"),
                Carte_5 = new Card("Carte 5"),
                Carte_6 = new Card("Carte 6");

        Column à_faire = new Column("à faire"),
                à_tester = new Column("à tester"),
                en_cours = new Column("en cours"),
                en_attente_de_paiement = new Column("en attente de paiement");

        columns.add(à_faire);
        columns.add(à_tester);
        columns.add(en_cours);
        columns.add(en_attente_de_paiement);

        à_faire.addCarte(Carte_1);

        à_tester.addCarte(Carte_2);
        à_tester.addCarte(Carte_3);

        en_cours.addCarte(Carte_4);
        en_cours.addCarte(Carte_5);
        en_cours.addCarte(Carte_6);

    }
}
