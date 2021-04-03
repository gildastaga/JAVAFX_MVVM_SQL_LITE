package model.column;

import javafx.beans.property.StringProperty;
import model.Card;
import model.card.Cardcommand;

public class MoveCardRightCommand extends Cardcommand {

    public MoveCardRightCommand(Card card) {
        super(card);
    }

    @Override
    public StringProperty getmessage() {
        return null;
    }

    @Override
    public void execute() {
        getCard().getColumn().swapCardRight(getCard().getColumn(), getCard());
    }

    @Override
    public void undo() {
        getCard().getColumn().swapCardLeft(getCard().getColumn(), getCard());
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
