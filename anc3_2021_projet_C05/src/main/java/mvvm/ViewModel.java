package mvvm;

import javafx.beans.property.*;
import model.Board;

public class ViewModel {
    private final Board board;
    private final SimpleListProperty columns = new SimpleListProperty<>();
    private final SimpleListProperty cards = new SimpleListProperty<>();

    public ViewModel(Board board){
        this.board = board;
    }

}