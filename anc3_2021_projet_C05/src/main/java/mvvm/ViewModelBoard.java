package mvvm;

import javafx.beans.property.*;
import model.Board;
import model.Column;
import model.board.AddColumnCommand;
import model.board.EditBordName;

public class ViewModelBoard {

    private final Board board;
    private final StringProperty boardName;
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    private final IntegerProperty menuItemEditSelected = new SimpleIntegerProperty (-1);
    private final IntegerProperty menuItemfichieSelected = new SimpleIntegerProperty (-1);
    public SimpleBooleanProperty desableUndo = new SimpleBooleanProperty(false);
    public SimpleBooleanProperty desableRedo = new SimpleBooleanProperty(false);
    //private StringProperty message;

    public ViewModelBoard(Board board) {
        this.board = board;
        boardName = new ReadOnlyStringWrapper (board.getName());
        configData();
    }

    public void configData() {
        columnList.setValue(board.getColumns());
    }

    public SimpleListProperty<Column> getColumnsProperty() {
        return columnList;
    }

    public IntegerProperty getNumLineSelectedColumnProperty() {
        return numSelectedColumn;
    }

    public void updateBordName(String name) {
        EditBordName editBordName = new EditBordName (board, name);
        Processor.getInstance ().execute (editBordName);

    }

    public void addColumn() {
        Column column = new Column("colonne" + columnList.getSize(), board);
        AddColumnCommand addColumnCommand = new AddColumnCommand(board, column);
        Processor.getInstance().execute(addColumnCommand);
       // message = new SimpleStringProperty ("ajouter une nouvelle Colonne  "+column.getName ());
        configData();
    }

    public StringProperty getMassage(){
        if (Processor.getInstance ().getMessagePro () != null) {
            System.out.println (Processor.getInstance ().getMessagePro ().get ());
            return Processor.getInstance ().getMessagePro ();
        }else {
            return new SimpleStringProperty ();
        }
    }

  /*  private void configMenuItenSelection() {
        menuItemEditSelected.addListener((obs, oldval, newval) ->
                configdisableMenu ());

        menuItemfichieSelected.addListener((obs, oldval, newval) ->
                configdisableMenu ());
    }

    public IntegerProperty menuItemEditSelected() {
        return menuItemEditSelected;
    }
    public IntegerProperty menuItemfichieSelected() {
        return menuItemfichieSelected;
    }

    private void configdisableMenu() {
        desableUndo.setValue(Processor.getInstance ().getHistory ().isEmpty ());
        desableRedo.setValue( Processor.getInstance ().getUndoHistory ().isEmpty ());
    }*/

    public SimpleBooleanProperty disableUndoProperty(){
        return desableUndo;
    }

    public SimpleBooleanProperty disableRedoProperty(){
        return desableRedo;
    }

    public void undo () {
        Processor.getInstance().undo();
    }

    public void redo() {
        Processor.getInstance().redo();
    }


}
