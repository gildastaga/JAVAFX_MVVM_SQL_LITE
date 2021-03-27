package mvvm;

import javafx.beans.property.*;
import model.Board;
import model.Column;
import model.Processor;

public class ViewModelBoard {

    private final Board board;
    private final StringProperty boardName;
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);

    public ViewModelBoard(Board board){
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
        Column c = getColumn(numSelectedColumn.get ());
        if (c == null ){
            board.addColumn(new Column ("Column "+board.getColumns ().size (),board));
        }
        configData();
    }

    public void undo (){
        Processor.getInstance().undo();
    }

    public void redo(){
        Processor.getInstance().redo();
    }


}
