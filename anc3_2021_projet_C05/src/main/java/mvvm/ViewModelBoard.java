package mvvm;

import javafx.beans.property.*;
import model.Board;
import model.Card;
import model.Column;
import model.Processor;
import model.board.AddColumnCommand;
import model.column.AddCardCommand;

public class ViewModelBoard {

    private final Board board;
    private final StringProperty boardName;
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    public SimpleBooleanProperty desableUndo = new SimpleBooleanProperty();
    public SimpleBooleanProperty desableRedo = new SimpleBooleanProperty();

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
        this.board.setName(name);
    }

    private Column getColumn(int index) {
        return index == -1  ? null : board.getColumn(index);
    }

    public void addColumn() {
        Column column = new Column("colonne" + columnList.getSize(), board);
        AddColumnCommand addColumnCommand = new AddColumnCommand(board, column);
        Processor.getInstance().execute(addColumnCommand);
        configData();
    }

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
