package model.column;

import javafx.beans.property.StringProperty;
import model.Column;

import java.util.ArrayList;

public class EditColumnName extends  ColumnCommand{

    private final String colunName;
    private ArrayList<String> stringArrayList= new ArrayList<> ();

    public EditColumnName(Column column, String newName) {
        super (column);
        this.colunName = newName;
    }

    @Override
    public StringProperty getmessage() {
        return null;
    }

    @Override
    public void execute() {
        stringArrayList.add (this.getColumn ().getName ());
        this.getColumn ().setName (colunName);
    }

    @Override
    public void undo() {
        this.getColumn ().setName (stringArrayList.get (stringArrayList.size ()-1));
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
