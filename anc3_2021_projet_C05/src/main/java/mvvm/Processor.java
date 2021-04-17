package mvvm;

import javafx.beans.property.StringProperty;
import model.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Processor {
    private static Processor processor ;
    private StringProperty message;
    private final List<Command> history = new ArrayList<>();
    private final List<Command> undoHistory = new ArrayList<>();

    public static Processor getInstance() {
        if(processor == null){
            processor = new Processor();
        }
        return processor;
    }

    public Command getLastUndoCommand(){
        if(!undoHistory.isEmpty()){
            return undoHistory.get(undoHistory.size()-1);
        }
        return null;
    }


    public Command getLastCommand(){
        if(!history.isEmpty()){
            return history.get(history.size() - 1);
        }
        return null;
    }

    public void removeLastCommand() {
        if(!history.isEmpty()){
             history.remove(history.size() - 1);
        }
    }

    public  boolean canExecute() {
        return getLastCommand () == null ? false : getLastCommand ().canExecute ();
    }

    public void execute(Command command) {
        command.execute();
        if(command.canExecute()){
            this.history.add(command);
        }else{
            history.clear();
        }
    }

    public void undo() {
        if(canExecute()){
            Command c = getLastCommand();
            c.undo();
            removeLastCommand();
            undoHistory.add(c);
        }
    }

    public void redo() {
        if(!undoHistory.isEmpty()){
            Command c = undoHistory.get(undoHistory.size() - 1);
            undoHistory.remove(undoHistory.size() - 1);
            this.execute(c);
        }
    }

    public boolean getSizeCommand() {
        return history.isEmpty();
    }

    public boolean getSizeUndoCommand() {
        return undoHistory.isEmpty();
    }

}
