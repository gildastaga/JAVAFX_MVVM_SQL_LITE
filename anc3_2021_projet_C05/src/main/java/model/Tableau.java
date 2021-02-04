package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Tableau {
    private final String name;
    private ObservableSet<Colonnes> CartePerColumn;
    private final IntegerProperty size = new SimpleIntegerProperty();

    private final ObservableSet<Colonnes> colonnes = FXCollections.observableSet( new TreeSet<>());
    public Tableau(String name){
        this.name = name;
        initData();
        CartePerColumn = FXCollections.observableSet(new TreeSet<>());
        //this.size.bind(new SimpleIntegerProperty<>(CartePerColumn).sizeProperty());
    }

   /* public Tableau(String name) {
        this.name = name;
        initData();
    }*/

    public final String getName() {
        return name;
    }

    public Set<Colonnes> getColonne() {
        return Collections.unmodifiableSet(colonnes);
    }


    private void initData() {
        Cartes Carte_1 = new Cartes("Carte 1"),
                Carte_2 = new Cartes("Carte 2"),
                Carte_3 = new Cartes("Carte 3"),
                Carte_4 = new Cartes("Carte 4"),
                Carte_5 = new Cartes("Carte 5"),
                Carte_6 = new Cartes("Carte 6");

        Colonnes à_faire = new Colonnes("à faire"),
                à_tester = new Colonnes("à tester"),
                en_cours = new Colonnes("en cours"),
                en_attente_de_paiement = new Colonnes("en attente de paiement");

        colonnes.add(à_faire);
        colonnes.add(à_tester);
        colonnes.add(en_cours);
        colonnes.add(en_attente_de_paiement);

        à_faire.addCarte(Carte_1);

        à_tester.addCarte(Carte_2);
        à_tester.addCarte(Carte_3);

        en_cours.addCarte(Carte_4);
        en_cours.addCarte(Carte_5);
        en_cours.addCarte(Carte_6);

    }

}
