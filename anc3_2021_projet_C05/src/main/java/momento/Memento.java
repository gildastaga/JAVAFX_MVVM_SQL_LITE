package momento;

import javafx.application.Application;
import javafx.stage.Stage;

public class Memento {
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setStage( String stage){
        this.state = stage;
    }
}
