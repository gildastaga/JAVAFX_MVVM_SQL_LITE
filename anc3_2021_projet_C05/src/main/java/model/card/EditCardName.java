package model.card;

import model.Board;
import model.Card;
import model.board.BoardCommand;

public class EditCardName extends Cardcommand {

    private final String newName;

    public EditCardName(Card card, String newName) {
        super(card);
        this.newName = newName;
    }
    @Override
    public void execute() {
        this.getCard ().setNameCard (newName);
    }

    @Override
    public void undo() {
        this.getCard ().setNameCard (getCard ().getName ());
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
