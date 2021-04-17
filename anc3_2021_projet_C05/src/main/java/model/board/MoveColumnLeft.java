package model.board;

import javafx.beans.property.StringProperty;
import model.Board;
import model.Column;

public class MoveColumnLeft extends BoardCommand {
    private final Column column;

    public MoveColumnLeft(Board board, Column column) {
        super(board);
        this.column = column;
    }

    @Override
    public void execute() {
        this.getBoard().swapColumnLeft(column.getposition ());
    }

    @Override
    public void undo() {
        this.getBoard().swapColumnRight(column.getposition ());
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "le déplacement de colonne vers la gauche";
    }
}
