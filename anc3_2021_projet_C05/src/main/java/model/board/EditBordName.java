package model.board;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Board;
import momento.CareTaker;
import momento.Originator;

public class EditBordName extends BoardCommand {

    private final String newName;
   // private  StringProperty message = new SimpleStringProperty ();
    Originator originator = new Originator();
    CareTaker careTaker = new CareTaker();

    public EditBordName(Board board,String newName) {
        super(board);
        this.newName = newName;
    }

    @Override
    public StringProperty getmessage() {
        return message;
    }

    @Override
    public void execute() {
        message = new SimpleStringProperty ("change le nom du tableau "+this.getBoard ().getName ()+" en "+newName);
        this.getBoard ().setName ( newName);
        originator.setState (newName);
        careTaker.add (originator.saveStateToMemento ());

    }

    @Override
    public void undo() {
        message = new SimpleStringProperty ("change le nom du tableau en"+this.getBoard ().getName ()+" en "+newName);
        this.getBoard ().setName (getBoard ().getName ());
        originator.restor (careTaker.undo ());
    }



    @Override
    public boolean canExecute() {
        return true;
    }
}
