package model.board;


import model.Board;

public class EditBordName extends BoardCommand {
    private final String newName;
    private final String lastName;

    public EditBordName(Board board, String newName, String lastName) {
        super(board);
        this.newName = newName;
        this.lastName = lastName;
    }

    @Override
    public void execute() {
        this.getBoard ().setName (newName);
    }

    @Override
    public void undo() {
        this.getBoard ().setName (lastName);
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public String getActionName(){
        return "la modification du nom ";
    }
}
