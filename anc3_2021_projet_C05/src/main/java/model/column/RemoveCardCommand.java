package model.column;

import javafx.beans.property.StringProperty;
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

    @Override
    public String getActionName() {
        return "la supression d'une carte :"+card.getName ().toUpperCase ();
    }
}
