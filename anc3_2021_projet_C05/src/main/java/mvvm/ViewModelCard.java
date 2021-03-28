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
    }

    public void swapCardUp() {
        MoveCardUpCommand moveCardUpCommand = new MoveCardUpCommand(card.getColumn(), card);
        Processor.getInstance().execute(moveCardUpCommand);
    }

   public void swapCardRight() {
       MoveCardRightCommand moveCardRightCommand = new MoveCardRightCommand(card);
       Processor.getInstance().execute(moveCardRightCommand);
    }

    public void swapCardLeft() {
        MoveCardLeftCommand moveCardLeftCommand = new MoveCardLeftCommand(card);
        Processor.getInstance().execute(moveCardLeftCommand);
    }

    public void deleteCard() {
        RemoveCardCommand removeCardCommand = new RemoveCardCommand(card.getColumn(), card);
        Processor.getInstance().execute(removeCardCommand);
    }

    public void updateCardName(String name) {
        this.card.setNameCard(name);
    }

}
