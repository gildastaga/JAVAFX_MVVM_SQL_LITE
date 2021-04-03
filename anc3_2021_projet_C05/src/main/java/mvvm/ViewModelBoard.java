package mvvm;

import javafx.beans.property.*;
import model.Board;
import model.Card;
import model.Column;
import model.Processor;
import model.board.AddColumnCommand;
import model.board.EditBordName;
import model.column.AddCardCommand;

public class ViewModelBoard {

    private final Board board;
    private final StringProperty boardName;
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    public SimpleBooleanProperty desableUndo = new SimpleBooleanProperty();
    public SimpleBooleanProperty desableRedo = new SimpleBooleanProperty();
    public SimpleStringProperty actionName = new SimpleStringProperty();

    public ViewModelBoard(Board board) {
        this.board = board;
        boardName = new ReadOnlyStringWrapper (board.getName());
        configData();
    }

    public void configData() {
        columnList.setValue(board.getColumns());
        refreshMenuDisable();
        boardName.setValue(board.getName());
    }

    public void refreshMenuDisable(){
        desableRedo.setValue(Processor.getInstance().getSizeUndoCommand());
        desableUndo.setValue(Processor.getInstance().getSizeCommand());
    }

    public SimpleListProperty<Column> getColumnsProperty() {
        return columnList;
    }

    public IntegerProperty getNumLineSelectedColumnProperty() {
        return numSelectedColumn;
    }

    public void updateBordName(String name) {
        EditBordName editBordName = new EditBordName (board, name, board.getName());
        Processor.getInstance ().execute (editBordName);
        refreshMenuDisable();
       // this.board.setName(name);
    }

    public void addColumn() {
        Column column = new Column("colonne" + columnList.getSize(), board);
        AddColumnCommand addColumnCommand = new AddColumnCommand(board, column);
        Processor.getInstance().execute(addColumnCommand);
        refreshMenuDisable();
    }

    public SimpleBooleanProperty disableUndoProperty(){
        return desableUndo;
    }

    public SimpleBooleanProperty disableRedoProperty(){
        return desableRedo;
    }

    public SimpleStringProperty actionNameProperty(){
        return  actionName;
    }

    public void undo () {
        Processor.getInstance().undo();
        refreshMenuDisable();
        boardName.setValue(board.getName());
    }

    public void redo() {
        Processor.getInstance().redo();
        refreshMenuDisable();
        boardName.setValue(board.getName());
    }

    public String getMessage(){
        return Processor.class.getName ();
    }
}
