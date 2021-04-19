package model.card;

import javafx.beans.property.StringProperty;
import model.Card;

public class EditCardName extends Cardcommand {
    private final String cardName;

    public EditCardName(Card card, String cardName) {
        super(card);
        this.cardName = cardName;
    }

    @Override
    public void execute() {
        this.getCard ().setNameCard (cardName);
    }

    @Override
    public void undo() {
        this.getCard ().setNameCard (cardName);
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName() {
        return "la modification du nom ";
    }
}
