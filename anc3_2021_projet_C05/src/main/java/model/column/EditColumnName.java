package model.column;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Column;

public class EditColumnName extends  ColumnCommand{
    private final String columnName;
    private Column column;

    public EditColumnName(Column column, String columnName) {
        super (column);
        this.column = column;
        this.columnName = columnName;
    }

    @Override
    public void execute() {
        this.getColumn ().setName(columnName);
    }

    @Override
    public void undo() {
        this.getColumn ().setName(columnName);
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
