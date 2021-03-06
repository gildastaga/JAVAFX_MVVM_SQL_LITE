package model.board;

import javafx.beans.property.StringProperty;
import model.Board;
import model.Column;

public class AddColumnCommand extends BoardCommand {
    private final Column column;

    public AddColumnCommand(Board board, Column column) {
        super(board);
        this.column = column;
    }

    @Override
    public void execute() {
        this.getBoard().addColumn(column);
    }

    @Override
    public void undo() {
        this.getBoard().removeColumn(column);
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "l'ajout d'une colonne"+column.getName ().toUpperCase ();
    }
}
