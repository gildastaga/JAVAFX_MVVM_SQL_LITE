package momento;

public class Originator {
    private String state;
    public Originator(){}
    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setStage(String stage){
        this.state = stage;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void restor(Memento memento){
        state = memento.getState();
    }


}