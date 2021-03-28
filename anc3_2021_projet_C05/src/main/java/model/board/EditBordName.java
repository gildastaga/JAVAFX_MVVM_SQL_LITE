package model.board;

import model.Board;

public class EditBordName extends BoardCommand {

    private final String newName;

    public EditBordName(Board board,String newName) {
        super(board);
        this.newName = newName;
    }
    @Override
    public void execute() {
        this.getBoard ().setName ( newName);
    }

    @Override
    public void undo() {
        this.getBoard ().setName (getBoard ().getName ());
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
