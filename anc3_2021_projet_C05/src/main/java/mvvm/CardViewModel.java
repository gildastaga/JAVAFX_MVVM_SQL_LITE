package mvvm;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Card;

public class CardViewModel {
    public final Card card;
    private  StringProperty NameCarte = new SimpleStringProperty ();

    public CardViewModel(Card c) {
        this.card=c;
        this.NameCarte = new ReadOnlyStringWrapper (c.getName());
    }

    public StringProperty nameCarteProperty(){
        System.out.println (NameCarte+"mon nom de carte");
        return NameCarte;
    }
}
