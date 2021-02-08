package mvvm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Card;
import model.Column;

public class ColumnViewModel {


    private final Column column;
    private final StringProperty coloneName;
    private final ObservableList<Card> liscard = FXCollections.observableArrayList();

    private  final IntegerProperty selectedCard = new SimpleIntegerProperty ();

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
    public  void addCard(){
        Card c = getCard();
        if (c == null ){
            column.addCard(new Card ("Card "+liscard.size ()+1));
            // a reflechir
        }else {
            column.addCard(new Card ("Card "+liscard.size ()+1));
        }
        configData();
    }
    private Card getCard() {
        int index= selectedCard.get();
        return index == -1 || index ==0 ? null : liscard.get(index);
    }
}
