package mvvm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import view.ViewBoard;
import view.ViewColumn;

public class ViewModelColumn {

    private final Column column;
    private final StringProperty columnName;
    private final SimpleListProperty cards = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    private final IntegerProperty numSelectedCard = new SimpleIntegerProperty (-1);

    private BooleanProperty imleftColumDisabled= new SimpleBooleanProperty(false),
                             imRightColumDisabled = new SimpleBooleanProperty(false);

    public ViewModelColumn(Column column){
        this.column = column;
        columnName = new ReadOnlyStringWrapper (column.getName());
        configData();
        configColumnSelection();
        configActionnableImages ();
    }


    public void configData() {
        cards.setValue(column.getCards ());
    }

    public IntegerProperty getNumSelectedColumnProperty() {
        return numSelectedColumn;
    }
    public IntegerProperty getNumLineSelectedCardProperty() {
        return numSelectedCard;
    }

    public SimpleListProperty<Card> getCardsProperty()  {
        return this.cards;
    }


    /**************************************************************** configdisable ************************************************/
    public void selectedColumBinding(ReadOnlyIntegerProperty integerProperty) {
        numSelectedColumn.bind(integerProperty);
    }

    private void configColumnSelection() {

        numSelectedColumn.addListener((obs, oldval, newval) ->
                configActionnableImages ());
    }
    private void configActionnableImages() {
       // imleftColumDisabled.setValue(column.getpositions () > 0);
        //imRightColumDisabled.setValue( column.getpositions () < column.getBoard ().getColumns ().size ());
    }

    public BooleanProperty imleftColumDisabledProperty() {
        return imleftColumDisabled;
    }

    public  BooleanProperty imRightColumDisabledProperty(){
        return imRightColumDisabled;
    }


    /****************************************************************  deplacement ***************************************************/

    public void swapColleft()  {
         column.getBoard ().swapColumnLeft (this.column.getpositions ());
    }

    public void swapColright()  {
        column.getBoard ().swapColumnRight (this.column.getpositions ());
    }

    public void updateColumnName(String name) {
        this.column.setName(name);
    }

    public  boolean  addCard( ) {
       return column.addCard(new Card ("Card "+ cards.size (),column));
    }

    public void deleteColumn() {
        column.getBoard ().removeColumn (column);
    }

}
