package mvvm;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import model.Board;
import model.Column;
import model.board.AddColumnCommand;
import model.board.EditBordName;
import mySqlitedao.BoardDao;
import mySqlitedao.ColumnDao;

import java.sql.SQLException;


public class ViewModelBoard {

    private final Board board;
    private final StringProperty boardName = new SimpleStringProperty();
    private final SimpleListProperty columnList = new SimpleListProperty<>();
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty (-1);
    public SimpleBooleanProperty desableUndo = new SimpleBooleanProperty();
    public SimpleBooleanProperty desableRedo = new SimpleBooleanProperty();
    public SimpleStringProperty actionNameUndo = new SimpleStringProperty();
    public SimpleStringProperty actionNameRedo = new SimpleStringProperty();
    BoardDao boardDao = new BoardDao ();
    ColumnDao columnDao = new ColumnDao ();
    public ViewModelBoard(Board board) {
        this.board = board;
        configData();
    }


    public void configData() {
        try {
            columnList.setValue ((ObservableList) columnDao.findAll (boardDao.find (1).getId ()));
            System.out.println (columnList+"vmb");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
       // columnList.setValue(board.getColumns());
        refreshMenuDisable();
        boardName.setValue(board.getName());
    }

    public void  refreshMenuDisable() {
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
        //boardName.setValue(Object.class.getName());
        if(Processor.getInstance().getSizeCommand()){
            actionNameUndo.setValue("");
        }
    }

    public void redo() {
        Processor.getInstance().redo();
        refreshMenuDisable();
        //boardName.setValue(Object.class.getName());
        if(Processor.getInstance().getSizeUndoCommand()){
            actionNameRedo.setValue(actionNameUndo.getValue());
        }
    }

}
