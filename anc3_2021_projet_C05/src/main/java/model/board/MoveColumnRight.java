package model.board;

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
        this.getBoard().swapColumnRight(column.getpositions());
    }

    @Override
    public void undo() {
        this.getBoard().swapColumnLeft(getBoard().getposition());
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
