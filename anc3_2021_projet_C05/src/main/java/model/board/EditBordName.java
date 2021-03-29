package model.board;

import model.Board;
import momento.CareTaker;
import momento.Originator;

public class EditBordName extends BoardCommand {

    private final String newName;
    Originator originator = new Originator();
    CareTaker careTaker = new CareTaker();

    public EditBordName(Board board,String newName) {
        super(board);
        this.newName = newName;
    }
    @Override
    public void execute() {
        this.getBoard ().setName ( newName);
        originator.setState (newName);
        careTaker.add (originator.saveStateToMemento ());
        this.getBoard ().getName ();
    }

    @Override
    public void undo() {

        this.getBoard ().setName (getBoard ().getName ());
        originator.restor (careTaker.undo ());
    }



    @Override
    public boolean canExecute() {
        return true;
    }
}
