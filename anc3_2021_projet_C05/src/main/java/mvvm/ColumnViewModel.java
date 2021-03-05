package mvvm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import view.ViewBoard;
import view.ViewColumn;

public class ColumnViewModel {

    private final Column column;
    private final StringProperty columnName;
    private final SimpleListProperty cards = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    private final IntegerProperty numSelectedCard = new SimpleIntegerProperty (-1);

    private BooleanProperty imleftColumDisabled= new SimpleBooleanProperty(true),
            imRightColumDisabled = new SimpleBooleanProperty(true),
            newNameColumnDisabled= new SimpleBooleanProperty (true);

    public ColumnViewModel(Column column){
        this.column = column;
        columnName = new ReadOnlyStringWrapper (column.getName());
        configData();
        configColumnSelection();
        configActionnableImages ();
    }


    public void configData() {
        cards.setValue(column.getCards ());
    }


    public IntegerProperty getNumLineSelectedCardProperty() {
        return numSelectedCard;
    }

    public void lineSelectedCard(ReadOnlyIntegerProperty index) {
        numSelectedCard.bind(index);
    }


    public IntegerProperty numSelectedCardProperty() {
        return numSelectedCard;
    }

    public SimpleListProperty<Card> getCardsProperty()  {
        return this.cards;
    }


    /////////////////////////////////configdisable////////////////////


    private void configColumnSelection() {
        numSelectedColumn.addListener((obs, oldval, newval) ->
                configActionnableImages ());
    }
    private void configActionnableImages() {
        imleftColumDisabled.setValue(column.getPosition () == 0);
         imRightColumDisabled.setValue( column.getPosition () == column.getBoard ().getColumns ().size ());
    }

    public BooleanProperty imleftColumDisabledProperty() {
        return imleftColumDisabled;
    }

    public  BooleanProperty imRightColumDisabledProperty(){
        return imRightColumDisabled;
    }


    ///////////deplacement //////

    public void swapColleft()  {
         column.getBoard ().swapColumnLeft (this.column.getPosition ());
    }

    public void swapColright()  {
        column.getBoard ().swapColumnRight (this.column.getPosition ());
    }

    public  boolean  addCard( ) {
       return column.addCard(new Card ("Card "+cards.size (),(cards.size ()+1),column));
    }

    public void deleteColumn() {
        column.getBoard ().removeColumn (column);
    }

}
