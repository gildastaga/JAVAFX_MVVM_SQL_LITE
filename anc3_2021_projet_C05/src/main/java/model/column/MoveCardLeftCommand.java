package model.column;

import javafx.beans.property.StringProperty;
import model.Card;
import model.Column;
import model.card.Cardcommand;

public class MoveCardLeftCommand extends Cardcommand {

    public MoveCardLeftCommand(Card card) {
        super(card);
    }

    @Override
    public StringProperty getmessage() {
        return null;
    }

    @Override
    public void execute() {
        System.out.println("1");
        getCard().getColumn().swapCardLeft(getCard().getColumn(), getCard());
    }

    @Override
    public void undo() {
        System.out.println("2");
        getCard().getColumn().swapCardRight(getCard().getColumn(), getCard());
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
