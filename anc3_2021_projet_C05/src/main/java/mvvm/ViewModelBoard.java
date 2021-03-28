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
       // this.board.setName(name);
    }

    public void addColumn() {
        //Column c = getColumn(numSelectedColumn.get ());
        Column column = new Column("colonne" + columnList.getSize(), board);
        //if (c == null ){
            AddColumnCommand addColumnCommand = new AddColumnCommand(board, column);
            Processor.getInstance().execute(addColumnCommand);
           // board.addColumn(new Column ("Column "+board.getColumns ().size (),board));
        //}
        configData();
    }

    public void undo () {
        Processor.getInstance().undo();
    }

    public void redo() {
        Processor.getInstance().redo();
    }

    public String getMessage(){
        return Processor.class.getName ();
    }
}
