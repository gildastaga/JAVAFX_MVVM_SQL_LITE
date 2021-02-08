package mvvm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Card;
import model.Column;

public class ColumnViewModel {


    private final Column column;
    private final StringProperty coloneName;
    private final ObservableList<Card> liscard = FXCollections.observableArrayList();

    public ColumnViewModel(Column c){
        this.column =c;
        this.coloneName= new ReadOnlyStringWrapper(c.getName());
        configData();
    }
    public IntegerProperty sizeProperty() {
        return column.sizeProperty();
    }

    public StringProperty colNameProperty(){
        return coloneName;
    }

    private void configData(){
        liscard.setAll(column.getCartePerColumn ());
    }

    public SimpleListProperty<Card> cardProperty(){
        return new SimpleListProperty<>(liscard);
    }
}
