package mvvm;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import model.Cartes;
import model.Tableau;

public class BoardViewModel {
    private final Tableau tableau;
    private final StringProperty tableauName;


    public BoardViewModel(Tableau tableau) {
        this.tableau = tableau;
        this.tableauName = new ReadOnlyStringWrapper(tableau.getName());
    }


}
