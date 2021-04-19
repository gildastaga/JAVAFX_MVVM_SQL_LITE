package model.column;

import javafx.beans.property.StringProperty;
import model.Card;
import model.card.Cardcommand;

public class MoveCardRightCommand extends Cardcommand {
    private final Card card;
    public MoveCardRightCommand(Card card) {
        super(card);
        this.card = card;
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
        return "le déplacement de la carte :"+card.getName ().toUpperCase ()+"  vers la droite";
    }
}
