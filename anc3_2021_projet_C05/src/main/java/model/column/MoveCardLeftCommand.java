package model.column;

import model.Card;
import model.card.Cardcommand;


public class MoveCardLeftCommand extends Cardcommand {
    private final Card card;
    public MoveCardLeftCommand(Card card) {
        super(card);
        this.card = card;
    }

    @Override
    public void execute() {
        getCard().getColumn().swapCardLeft(getCard().getColumn(), getCard());
    }

    @Override
    public void undo() {
        getCard().getColumn().swapCardRight(getCard().getColumn(), getCard());
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "le déplacement de la carte vers la gauche:"+card.getName ().toUpperCase ();
    }
}
