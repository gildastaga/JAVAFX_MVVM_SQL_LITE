package model;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    private static Processor processor ;
    private final List<Command> history = new ArrayList<>();
    private final List<Command> undoHistory = new ArrayList<>();

    public static Processor getInstance(){
        if(processor == null){
            processor = new Processor();
        }
        return processor;
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
        if(getLastCommand() == null){
            return false;
        }
        return getLastCommand().canExecute();
    }

    public void execute(Command command){
        command.execute();
        if(command.canExecute()){
            this.history.add(command);
        }else{
            history.clear();
        }
    }

    public void undo(){
        if(canExecute()){
            Command c = getLastCommand();
            c.undo();
            removeLastCommand();
            undoHistory.add(c);
        }else{
            throw new RuntimeException("Aucune commande annuler");
        }
    }

    public void redo(){
        if(!undoHistory.isEmpty()){
            Command c = undoHistory.get(undoHistory.size() - 1);
            undoHistory.remove(undoHistory.size() - 1);
            this.execute(c);
        }else{
            throw  new RuntimeException("Aucune commande à refaire");
        }
    }

}
