package model;

public interface Command {

    void execute();
    void undo();
    boolean canExecute();
    String getActionName();
}
