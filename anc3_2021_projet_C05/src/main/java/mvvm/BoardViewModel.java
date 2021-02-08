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

public class BoardViewModel {

    private final ObservableList<Column> listColumn = FXCollections.observableArrayList();
    private final ObservableList<Card> cartes = FXCollections.observableArrayList();
    private final Board board;
    private  StringProperty boardName = new SimpleStringProperty();
   // private final StringProperty NameCarte = new SimpleStringProperty();

    public BoardViewModel(Board board) {
        this.board = board;
        configData();
        this.boardName = new ReadOnlyStringWrapper (board.getName());
    }

    public SimpleListProperty<Card> getListCardByColumn() {

        return new SimpleListProperty<>(cartes);
    }

    private void configData(){
        listColumn.setAll(board.getColonne());
        cartes.setAll();
    }

    public SimpleListProperty<Column> columnProperty(){
        return new SimpleListProperty<>(listColumn);
    }

 //   public StringProperty editLineProperty() {return NameCarte;}

}
