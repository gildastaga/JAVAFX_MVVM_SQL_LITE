package model.column;

import model.Column;

public class MoveCardUpCommand extends ColumnCommand {

    public MoveCardUpCommand(Column column) {
        super(column);
    }

    @Override
    public void execute() {
        this.getColumn().swapCardUp(getColumn().getpositions());
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
