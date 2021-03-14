package mvvm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Column;
import view.ViewBoard;
import view.ViewColumn;

public class BoardViewModel {

    private final Board board;
    private final StringProperty boardName;
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);

    public BoardViewModel(Board board){
        this.board = board;
        boardName = new ReadOnlyStringWrapper (board.getName());
        configData();
        //configColumnSelection();
    }

    public StringProperty getBoardNameProperty(){
        return boardName;
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

    public void lineSelectedColumn(ReadOnlyIntegerProperty index) {
        numSelectedColumn.bind(index);
    }

    public StringProperty getBordNameProperty() {
        return boardName;
    }

    private Column getColumn(int index) {
        return index == -1  ? null : board.getColumn(index);
    }

    public int getNumSelectedColumn() {
        return numSelectedColumn.get ();
    }

    public IntegerProperty numSelectedColumnProperty() {
        return numSelectedColumn;
    }
    private void configColumnSelection() {
        numSelectedColumn.addListener((obs, oldval, newval) ->
                configData ());
    }
    private Column getColumnSelected(int index) {
        return index == -1  ? null : board.getColumn(index);
    }

    public SimpleListProperty<Column> getLsViewColum(ViewBoard viewBoard) throws Exception {
        return new SimpleListProperty<>(columnList);
    }

    public void addColumn() {
        Column c = getColumn(numSelectedColumn.get ());
        if (c == null ){
            board.addColumn(new Column ("Column "+board.getColumns ().size (),board.getColumns ().size ()+1,board));
        }
        configData();
    }

    public void updateBordName(String name) {
        this.board.setName(name);
        this.boardName.setValue(this.board.getName());
    }

}
