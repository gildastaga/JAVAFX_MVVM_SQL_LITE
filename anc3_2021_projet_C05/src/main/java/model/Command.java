package model;

import javafx.beans.property.StringProperty;

public interface Command {
    void execute();
    void undo();
    boolean canExecute();
    String getActionName();
}
