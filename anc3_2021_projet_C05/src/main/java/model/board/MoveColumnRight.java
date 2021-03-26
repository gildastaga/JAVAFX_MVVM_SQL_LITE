package model.board;

import model.Board;

public class MoveColumnRight extends BoardCommand {

    public MoveColumnRight(Board board) {
        super(board);
    }

    @Override
    public void execute() {
        this.getBoard().swapColumnRight(getBoard().getposition());
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
