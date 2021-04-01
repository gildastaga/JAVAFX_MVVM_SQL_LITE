package model.board;

import javafx.beans.property.StringProperty;
import model.Board;
import model.Command;

public abstract class BoardCommand implements Command {
    private final Board board;

   public StringProperty message;

    public String getMessage() {
        return message.get ();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public BoardCommand(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
