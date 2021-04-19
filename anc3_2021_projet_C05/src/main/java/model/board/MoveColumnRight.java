package model.board;

import javafx.beans.property.StringProperty;
import model.Board;
import model.Column;

public class MoveColumnRight extends BoardCommand {
    private final Column column;

    public MoveColumnRight(Board board, Column column) {
        super(board);
        this.column = column;
    }

    @Override
    public void execute() {
        this.getBoard().swapColumnRight(column.getposition ());
    }

    @Override
    public void undo() {
        this.getBoard().swapColumnLeft(column.getposition ());
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "le déplacement de colonne "+column.getName ().toUpperCase ()+" vers la droite";
    }
}
