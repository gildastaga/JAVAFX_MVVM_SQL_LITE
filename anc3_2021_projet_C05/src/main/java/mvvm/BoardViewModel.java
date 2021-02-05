package mvvm;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cartes;
import model.Colonnes;
import model.Tableau;

public class BoardViewModel {

    private final ObservableList<Colonnes> listColumn = FXCollections.observableArrayList();
    private final ObservableList<Cartes> cartes = FXCollections.observableArrayList();
    private final Tableau tableau;
    private final StringProperty tableauName = new SimpleStringProperty();
    private final StringProperty NameCarte = new SimpleStringProperty();

    public BoardViewModel(Tableau tableau) {
        this.tableau = tableau;
        configData();
        //this.tableauName = new ReadOnlyStringWrapper(tableau.getName());
    }

    public SimpleListProperty<Cartes> getListCardByColumn() {

        return new SimpleListProperty<>(cartes);
    }

    private void configData(){
        listColumn.setAll(tableau.getColonne());
        cartes.setAll();
    }

    public SimpleListProperty<Colonnes> columnProperty(){
        return new SimpleListProperty<>(listColumn);
    }

    public StringProperty editLineProperty() {
        return NameCarte;
    }

}
