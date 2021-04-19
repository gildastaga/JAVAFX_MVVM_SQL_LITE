package model.board;

import javafx.beans.property.StringProperty;
import model.Board;
import model.Column;

public class RemoveColumnCommand extends BoardCommand {
    private final Column column;

    public RemoveColumnCommand(Board board, Column column) {
        super(board);
        this.column = column;
    }

    @Override
    public void execute() {
        this.getBoard().removeColumn(column);
    }

    @Override
    public void undo() {
        this.getBoard().addColumn(column);
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "la supression de la colonne"+column.getName ().toUpperCase ();
    }
}
