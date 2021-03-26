package model.board;

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

    }

    @Override
    public boolean canExecute() {
        return false;
    }
}
