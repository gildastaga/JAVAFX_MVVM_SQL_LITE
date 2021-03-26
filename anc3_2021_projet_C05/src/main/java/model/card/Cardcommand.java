package model.card;

import model.Card;
import model.Command;

public abstract class Cardcommand implements Command {
    private Card card;

    public Cardcommand(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}

