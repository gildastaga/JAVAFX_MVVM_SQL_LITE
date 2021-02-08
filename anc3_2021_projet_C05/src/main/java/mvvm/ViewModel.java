package mvvm;

import javafx.beans.property.*;
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

    private  final IntegerProperty selectedColumn = new SimpleIntegerProperty ();

    public ViewModel(Board board){
        this.board = board;
        this.boardname = new ReadOnlyStringWrapper (board.getName());
        configData();
    }

    private void configData(){
        listColumn.setAll(board.getColonne());
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

   /* private void configCourseSelection() {
        selectedColumn.addListener((obs, oldval, newval) -> {
            int index = newval.intValue();
            if (index == -1) {
                listColumn.clear();
            } else {
                setCourseStudents();
            }
           // configActionnableButtons();
        });
    }*/


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

}
