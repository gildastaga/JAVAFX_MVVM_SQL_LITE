package mvvm;

import javafx.beans.property.*;
import model.Card;
import model.Column;
import model.Processor;
import model.column.*;

public class ViewModelCard {
    public final Card card;
    private StringProperty NameCarte = new SimpleStringProperty ();
    private  final IntegerProperty selectedCard = new SimpleIntegerProperty ();

    public ViewModelCard(Card c) {
        this.card=c;
        this.NameCarte = new ReadOnlyStringWrapper (c.getName());
    }

    public StringProperty nameCarteProperty(){
        return NameCarte;
    }

    public void swapCardDown() {
        MoveCardDownCommand moveCardDownCommand = new MoveCardDownCommand(card.getColumn(), card);
        Processor.getInstance().execute(moveCardDownCommand);
        //card.getColumn ().swapCardDown(this.card.getPosition ());
    }

    public void swapCardUp() {
        MoveCardUpCommand moveCardUpCommand = new MoveCardUpCommand(card.getColumn(), card);
        Processor.getInstance().execute(moveCardUpCommand);
       // card.getColumn ().swapCardUp (this.card.getPosition ());
    }

   public void swapCardRight() {
       MoveCardRightCommand moveCardRightCommand = new MoveCardRightCommand(card.getColumn(), card);
       Processor.getInstance().execute(moveCardRightCommand);
        //card.getColumn ().swapCardRight (card.getColumn (),card);
    }

    public void swapCardLeft() {
        MoveCardLeftCommand moveCardLeftCommand = new MoveCardLeftCommand(card.getColumn(), card);
        Processor.getInstance().execute(moveCardLeftCommand);
        //card.getColumn ().swapCardLeft (card.getColumn (),card);
    }

    public void deleteCard() {
        RemoveCardCommand removeCardCommand = new RemoveCardCommand(card.getColumn(), card);
        Processor.getInstance().execute(removeCardCommand);
    }

    public void updateCardName(String name) {
        this.card.setNameCard(name);
    }

}
