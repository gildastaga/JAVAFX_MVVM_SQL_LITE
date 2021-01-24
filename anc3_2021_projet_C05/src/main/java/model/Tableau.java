package model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Tableau {
    private final Set<Colonnes> colonnes = new TreeSet<>();

    public Tableau() {
        initData();
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
