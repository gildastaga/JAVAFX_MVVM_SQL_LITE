package mvvm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import model.Colonnes;

public class ColoneViewModel {
    private final Colonnes colone;
    private final StringProperty coloneName;
    public ColoneViewModel(Colonnes c){
        this.colone=c;
        this.coloneName= new ReadOnlyStringWrapper(c.getName());
    }
    public IntegerProperty sizeProperty() {
        return colone.sizeProperty();
    }

    public StringProperty colNameProperty(){
        return coloneName;
    }
}
