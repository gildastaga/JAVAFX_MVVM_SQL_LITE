package model.column;

import javafx.beans.property.StringProperty;
import model.Board;
import model.Card;
import model.Column;

public class AddCardCommand extends ColumnCommand {
    private Card card;

    public AddCardCommand(Column column, Card card) {
        super(column);
        this.card = card;
    }

    @Override
    public StringProperty getmessage() {
        return null;
    }

    @Override
    public void execute() {
        this.getColumn().addCard(card);
    }

    @Override
    public void undo() {
        this.getColumn().removeCard(card);
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return null;
    }

}
