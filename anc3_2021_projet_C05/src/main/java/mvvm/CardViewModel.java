package mvvm;

import javafx.beans.property.*;
import model.Card;
import model.Column;

public class CardViewModel {
    public final Card card;
    private StringProperty NameCarte = new SimpleStringProperty ();
    private  final IntegerProperty selectedCard = new SimpleIntegerProperty ();

    public CardViewModel(Card c) {
        this.card=c;
        this.NameCarte = new ReadOnlyStringWrapper (c.getName());
    }

    public StringProperty nameCarteProperty(){
        return NameCarte;
    }

    public void swapCardDown() {
        card.getColumn ().swapCardDown(this.card.getPosition ());
        //board.swapCardDown(numLineSelectedCard.intValue(), column.getPosition());
    }

    public void swapCardUp() {
        card.getColumn ().swapCardUp (this.card.getPosition ());
        //board.swapCardUp(numLineSelectedCard.intValue(), column.getPosition());
    }

   public void swapCardRight() {
        card.getColumn ().swapCardRight (card.getColumn ().getPosition (),card.getPosition ());
       // board.swapCardRight(numLineSelectedCard.intValue(), column.getPosition());
    }

    public void swapCardLeft() {
        card.getColumn ().swapCardLeft (card.getColumn ().getPosition (),card.getPosition ());
        //board.swapCardLeft(numLineSelectedCard.intValue(), column.getPosition());
    }
    public void deleteCard() {
        this.card.getColumn ().removeCard (card);
       // column.removeCard (card);
    }

    public void updateCardName(String name) {
        this.card.setNameCard(name);
    }

}
