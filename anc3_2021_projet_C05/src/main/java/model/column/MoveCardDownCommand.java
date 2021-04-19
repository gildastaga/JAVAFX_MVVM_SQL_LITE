package model.column;

import javafx.beans.property.StringProperty;
import model.Card;
import model.Column;

public class MoveCardDownCommand extends  ColumnCommand {
    private final Card card;

    public MoveCardDownCommand(Column column, Card card) {
        super(column);
        this.card = card;
    }

    @Override
    public void execute() {
        this.getColumn().swapCardDown(card.getPosition());
    }

    @Override
    public void undo() {
        this.getColumn().swapCardUp(card.getPosition());
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "le déplacement de la carte vers le bas :"+card.getName ().toUpperCase ();
    }
}
