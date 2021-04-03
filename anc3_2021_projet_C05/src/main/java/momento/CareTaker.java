package momento;

import momento.Memento;

import java.util.ArrayList;
import java.util.List;


public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();
    private int currStage = -1;

    public CareTaker(){}

    public void add(Memento state){
        mementoList.add(state);
        currStage =this.mementoList.size ()-1;
    }

    public Memento get(int index){
        return mementoList.get(index);
    }

    public Memento undo(){
        if (currStage <= 0 ){
            currStage = 0;
            return get(currStage);
        }
        currStage--;
        return    get (currStage);
    }

    public Memento redo(){
        if (currStage >= mementoList.size ()-1){
            currStage = mementoList.size ()-1;
            return get(currStage);
        }
        currStage++;
        return get (currStage);
    }
}

