package mvvm;

import javafx.beans.property.*;
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

    private  final IntegerProperty selectedColumn = new SimpleIntegerProperty ();
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
// questoin pour marceline ou prof comment assinuer Bordviewmodel dans le constricteur ?
    // car la ca fontion avec viewmodel
    public void addColumn(){
        Column c = getColumn();
        if (c == null ){
            // a reflechir
        }else {
            System.out.println ("addcolumn vm else");
            board.addColumn(new Column ("Column "+listColumn.size ()));
            configData();
        }
    }

    private Column getColumn() {
        int index= selectedColumn.get();
        return index == -1 ? null : listColumn.get (index);
    }

 //   public StringProperty editLineProperty() {return NameCarte;}

}
