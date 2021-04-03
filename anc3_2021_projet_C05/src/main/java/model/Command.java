package model;

import javafx.beans.property.StringProperty;

public interface Command {
    StringProperty getmessage ();
    void execute();
    void undo();
    boolean canExecute();
    String getActionName();
}
