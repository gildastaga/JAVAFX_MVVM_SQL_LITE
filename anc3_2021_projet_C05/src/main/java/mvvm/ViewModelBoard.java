package mvvm;

import javafx.beans.property.*;
import model.Board;
import model.Card;
import model.Column;
import model.board.AddColumnCommand;
import model.board.EditBordName;
import model.column.AddCardCommand;

public class ViewModelBoard {

    private final Board board;
    private final StringProperty boardName = new SimpleStringProperty();
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    public SimpleBooleanProperty desableUndo = new SimpleBooleanProperty();
    public SimpleBooleanProperty desableRedo = new SimpleBooleanProperty();
    public SimpleStringProperty actionNameUndo = new SimpleStringProperty();
    public SimpleStringProperty actionNameRedo = new SimpleStringProperty();


    public ViewModelBoard(Board board) {
        this.board = board;
        configData();
    }

    public void configData() {
        columnList.setValue(board.getColumns());
        refreshMenuDisable();
        boardName.setValue(board.getName());
    }

    public void refreshMenuDisable() {
        desableRedo.setValue(Processor.getInstance().getSizeUndoCommand());
        desableUndo.setValue(Processor.getInstance().getSizeCommand());
        if(Processor.getInstance().getLastCommand() != null) {
            actionNameUndo.setValue(Processor.getInstance().getLastCommand().getActionName());
        }
        if(Processor.getInstance().getLastUndoCommand() != null) {
            actionNameRedo.setValue(actionNameUndo.getValue());
        }
        boardName.setValue(board.getName());
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
    }

    public void addColumn() {
        Column column = new Column("colonne" + columnList.getSize(), board);
        AddColumnCommand addColumnCommand = new AddColumnCommand(board, column);
        Processor.getInstance().execute(addColumnCommand);
        refreshMenuDisable();
    }

    public StringProperty getNameBoard() {
        return boardName;
    }

    public SimpleBooleanProperty disableUndoProperty() {
        return desableUndo;
    }

    public SimpleBooleanProperty disableRedoProperty() {
        return desableRedo;
    }

    public SimpleStringProperty actionNameUndoProperty() {
        return  actionNameUndo;
    }

    public SimpleStringProperty actionNameRedoProperty() {
        return  actionNameRedo;
    }

    public void undo () {
        Processor.getInstance().undo();
        refreshMenuDisable();
        boardName.setValue(board.getName());
        if(Processor.getInstance().getSizeCommand()){
            actionNameUndo.setValue("");
        }
    }

    public void redo() {
        Processor.getInstance().redo();
        refreshMenuDisable();
        boardName.setValue(board.getName());
        if(Processor.getInstance().getSizeUndoCommand()){
            actionNameRedo.setValue(actionNameUndo.getValue());
        }
    }

}
