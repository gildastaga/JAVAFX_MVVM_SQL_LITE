package model.column;

import model.Board;
import model.Card;
import model.Column;

public class RemoveCardCommand extends ColumnCommand {
    private Card card;

    public RemoveCardCommand(Column column, Card card) {
        super(column);
        this.card = card;
    }

    @Override
    public void execute() {
        this.getColumn().removeCard(card);
    }

    @Override
    public void undo() {
        this.getColumn().addCard(card);
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
