package model.board;

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
        this.getBoard().swapColumnLeft(column.getpositions());
    }

    @Override
    public void undo() {
        this.getBoard().swapColumnRight(column.getpositions());
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
