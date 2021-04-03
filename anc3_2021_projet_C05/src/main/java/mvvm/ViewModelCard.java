package mvvm;

import javafx.beans.property.*;
import model.Card;
import model.card.EditCardName;
import model.column.*;

public class ViewModelCard {
    public final Card card;
    private StringProperty NameCarte = new SimpleStringProperty ();
    private final IntegerProperty numSelectedCard = new SimpleIntegerProperty (-1);
    private final ViewModelBoard viewModelBoard;

    private BooleanProperty imleftCardDisabled= new SimpleBooleanProperty(false),
            imRightCardDisabled = new SimpleBooleanProperty(false),
            imUpCardDisabled = new SimpleBooleanProperty (false),
            imDowCardDisabled = new SimpleBooleanProperty (false);

    public ViewModelCard(Card c, ViewModelBoard viewModelBoard) {
        this.card = c;
        this.NameCarte = new ReadOnlyStringWrapper (c.getName());
        this.viewModelBoard = viewModelBoard;
        configCardSelection();
        configActionnableImages();
    }

    public StringProperty nameCarteProperty(){
        return NameCarte;
    }

    public void swapCardDown() {
        MoveCardDownCommand moveCardDownCommand = new MoveCardDownCommand(card.getColumn(), card);
        Processor.getInstance().execute(moveCardDownCommand);
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
        //card.getColumn ().swapCardDown(this.card.getPosition ());
    }

    public void swapCardUp() {
        MoveCardUpCommand moveCardUpCommand = new MoveCardUpCommand(card.getColumn(), card);
        Processor.getInstance().execute(moveCardUpCommand);
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
       // card.getColumn ().swapCardUp (this.card.getPosition ());
    }

   public void swapCardRight() {
       MoveCardRightCommand moveCardRightCommand = new MoveCardRightCommand( card);
       Processor.getInstance().execute(moveCardRightCommand);
       configActionnableImages();
       viewModelBoard.refreshMenuDisable();
        //card.getColumn ().swapCardRight (card.getColumn (),card);
    }

    public void swapCardLeft() {
        MoveCardLeftCommand moveCardLeftCommand = new MoveCardLeftCommand( card);
        Processor.getInstance().execute(moveCardLeftCommand);
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
        //card.getColumn ().swapCardLeft (card.getColumn (),card);
    }

    public void deleteCard() {
        RemoveCardCommand removeCardCommand = new RemoveCardCommand(card.getColumn(), card);
        Processor.getInstance().execute(removeCardCommand);
        configActionnableImages();
        viewModelBoard.refreshMenuDisable();
    }

    public void updateCardName(String name) {
        EditCardName editCardName =new EditCardName (card, name);
        Processor.getInstance ().execute (editCardName);
        viewModelBoard.refreshMenuDisable();
        //this.card.setNameCard(name);
    }

    public BooleanProperty imuptCardDisabledProperty(){
        return imUpCardDisabled;
    }

    public BooleanProperty imdowCardDisableProperty(){
        return imDowCardDisabled;
    }
    public BooleanProperty imleftCardDisabledProperty(){
        return imleftCardDisabled;
    }

    public BooleanProperty imRightCardDisabledProperty(){
        return imRightCardDisabled;
    }

    private void configCardSelection() {
        numSelectedCard.addListener((obs, oldval, newval) ->
                configActionnableImages ());
    }

    private void configActionnableImages() {
        imUpCardDisabled.setValue(card.getPosition () == 0);
        imDowCardDisabled.setValue( card.getPosition () == card.getColumn ().getCards ().size ()-1);
        imleftCardDisabled.setValue(card.getColumn ().getposition () == 0);
        imRightCardDisabled.setValue( card.getColumn ().getposition() == card.getColumn ().getBoard ().getColumns ().size ()-1);
    }
}
