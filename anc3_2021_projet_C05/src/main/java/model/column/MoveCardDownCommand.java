package model.column;

import model.Column;

public class MoveCardDownCommand extends  ColumnCommand {

    public MoveCardDownCommand(Column column) {
        super(column);
    }

    @Override
    public void execute() {
        this.getColumn().swapCardDown(getColumn().getpositions());
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
