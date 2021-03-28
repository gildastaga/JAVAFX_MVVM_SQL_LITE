package mvvm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import model.Processor;
import model.board.MoveColumnLeft;
import model.board.MoveColumnRight;
import model.board.RemoveColumnCommand;
import model.column.AddCardCommand;
import model.column.MoveCardLeftCommand;
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
        MoveColumnLeft moveColumnLeft = new MoveColumnLeft(column.getBoard(), column);
        Processor.getInstance().execute(moveColumnLeft);
        // column.getBoard ().swapColumnLeft (this.column.getpositions ());
    }

    public void swapColright()  {
        MoveColumnRight moveColumnRight = new MoveColumnRight(column.getBoard(), column);
        Processor.getInstance().execute(moveColumnRight);
        //column.getBoard ().swapColumnRight (this.column.getpositions ());
    }

    public void updateColumnName(String name) {
        this.column.setName(name);
    }

    public  void  addCard( ) {
        Card c = new Card ("Card "+ cards.size (),column);
        AddCardCommand addCardCommand = new AddCardCommand(column, c);
        Processor.getInstance().execute(addCardCommand);
        //column.addCard(new Card ("Card "+ cards.size (),column));
    }

    public void deleteColumn() {
        RemoveColumnCommand removeColumnCommand = new RemoveColumnCommand(column.getBoard(), column);
        Processor.getInstance().execute(removeColumnCommand);
        //column.getBoard ().removeColumn (column);
    }

}
