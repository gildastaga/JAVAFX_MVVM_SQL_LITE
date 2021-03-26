package model.column;

import model.Card;
import model.Column;

public class MoveCardLeftCommand extends ColumnCommand {
    private final Card card;

    public MoveCardLeftCommand(Column column, Card card) {
        super(column);
        this.card = card;
    }

    @Override
    public void execute() {
        this.getColumn().swapCardLeft(getColumn(), card);
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
