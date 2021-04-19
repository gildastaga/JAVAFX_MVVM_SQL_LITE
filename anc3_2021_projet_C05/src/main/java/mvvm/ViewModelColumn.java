package mvvm;

import javafx.beans.property.*;
import model.Card;
import model.Column;
import model.board.MoveColumnLeft;
import model.board.MoveColumnRight;
import model.board.RemoveColumnCommand;
import model.column.AddCardCommand;
import model.column.EditColumnName;

public class ViewModelColumn {

    private final Column column;
    private final StringProperty columnName;
    private final SimpleListProperty cards = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    private String message;
    private BooleanProperty imleftColumDisabled= new SimpleBooleanProperty(false),
                             imRightColumDisabled = new SimpleBooleanProperty(false);
    private final ViewModelBoard viewModelBoard;

    public ViewModelColumn(Column column, ViewModelBoard viewModelBoard){
        this.column = column;
        columnName = new ReadOnlyStringWrapper (column.getName());
        this.viewModelBoard = viewModelBoard;
        configData();
        configColumnSelection();
        configActionnableImages ();
    }

    public void configData() {
        cards.setValue(column.getCards ());
        columnName.setValue(column.getName());
    }

    public IntegerProperty getNumSelectedColumnProperty() {
        return numSelectedColumn;
    }

    public SimpleListProperty<Card> getCardsProperty()  {
        return this.cards;
    }

    /**************************************************************** configdisable ************************************************/

    private void configColumnSelection() {
        numSelectedColumn.addListener((obs, oldval, newval) ->
                configActionnableImages ());
    }

    private void configActionnableImages() {
        imleftColumDisabled.setValue(column.getposition () == 0);
        imRightColumDisabled.setValue( column.getposition() == column.getBoard ().getColumns ().size ());
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
        message = "deplacement de la colonne "+column.getName ()+" vers la gauche ";
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
    }

    public void swapColright()  {
        MoveColumnRight moveColumnRight = new MoveColumnRight(column.getBoard(), column);
        Processor.getInstance().execute(moveColumnRight);
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
    }

    public void updateColumnName(String name) {
        EditColumnName editColumnName = new EditColumnName (column, name);
        Processor.getInstance ().execute (editColumnName);
        viewModelBoard.refreshMenuDisable();
    }

    public  void  addCard( ) {
        Card c = new Card ("Card "+ cards.size (),column);
        AddCardCommand addCardCommand = new AddCardCommand(column, c);
        Processor.getInstance().execute(addCardCommand);
        viewModelBoard.refreshMenuDisable();
    }

    public void deleteColumn() {
        RemoveColumnCommand removeColumnCommand = new RemoveColumnCommand(column.getBoard(), column);
        Processor.getInstance().execute(removeColumnCommand);
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
    }
}
