package model.column;

import model.Column;

import java.util.ArrayList;

public class EditColumnName extends  ColumnCommand{
    private final String columnName;

    public EditColumnName(Column column, String columnName) {
        super (column);
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
        return null;
    }
}
