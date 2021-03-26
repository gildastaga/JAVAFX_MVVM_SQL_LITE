package model.column;

import model.Card;
import model.Column;

public class MoveCardRightCommand extends ColumnCommand {
    private final Card card;

    public MoveCardRightCommand(Column column, Card card) {
        super(column);
        this.card = card;
    }

    @Override
    public void execute() {
        this.getColumn().swapCardRight(getColumn(), card);
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
