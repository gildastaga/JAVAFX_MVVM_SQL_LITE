package model.board;

import model.Board;

public class MoveColumnLeft extends BoardCommand {

    public MoveColumnLeft(Board board) {
        super(board);
    }

    @Override
    public void execute() {
        this.getBoard().swapColumnLeft(getBoard().getposition());
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
