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

    public static Processor getInstance(){
        if(processor == null){
            processor = new Processor();
        }
        return processor;
    }

    public  StringProperty getMessagePro(){
        return  message;
    }

    public List<Command> getHistory() {
        return history;
    }

    public List<Command> getUndoHistory() {
        return undoHistory;
    }

    private Command getLastCommand(){
        if(!history.isEmpty()){
            return history.get(history.size() - 1);
        }
        return null;
    }

    private void removeLastCommand(){
        if(!history.isEmpty()){
             history.remove(history.size() - 1);
        }
    }

    private  boolean canExecute(){
        return getLastCommand () == null ? false : getLastCommand ().canExecute ();
    }

    public void execute(Command command){
        command.execute();
        if(command.canExecute()){
            this.history.add(command);
          message=  command.getmessage ();
            System.out.println (message);
        }else{
            history.clear();
        }
    }

    public void undo(){
        if(canExecute()){
            Command c = getLastCommand();
            message = c.getmessage ();
            System.out.println (message+" undo");
            c.undo();
            removeLastCommand();
            undoHistory.add(c);
        }else{
           //throw new RuntimeException("Aucune commande annuler");
        }
    }

    public void redo(){
        if(!undoHistory.isEmpty()){
            Command c = undoHistory.get(undoHistory.size() - 1);
            message = c.getmessage ();
            System.out.println (message+" redo");
            undoHistory.remove(undoHistory.size() - 1);
            this.execute(c);
        }else{
          //  throw  new RuntimeException("Aucune commande à refaire");
        }
    }

    public boolean getSizeCommand(){
        return history.isEmpty();
    }

    public boolean getSizeUndoCommand(){
        return undoHistory.isEmpty();
    }


}
