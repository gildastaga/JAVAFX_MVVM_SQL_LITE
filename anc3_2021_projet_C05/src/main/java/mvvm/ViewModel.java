package mvvm;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;

public class ViewModel {

    private final Board board;
    private final ObservableList<Column> listColumn =FXCollections.observableArrayList();
    private final ObservableList<Card> cartes = FXCollections.observableArrayList();
    private   StringProperty boardname;
    private final StringProperty NameCarte = new SimpleStringProperty();

    public ViewModel(Board board){
        this.board = board;
        this.boardname = new ReadOnlyStringWrapper (board.getName());
        configData();
    }

    private void configData(){
        listColumn.setAll(board.getColonne());
        cartes.setAll();
    }

    public StringProperty boardNameProperty(){
        return boardname;
    }

    public  SimpleListProperty<Column> columnProperty(){
        return new SimpleListProperty<>(listColumn);
    }


    public SimpleListProperty<Card> getListCardByColumn() {
        return new SimpleListProperty<>(cartes);
    }
}
