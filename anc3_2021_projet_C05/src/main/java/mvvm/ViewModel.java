package mvvm;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Tableau;

public class ViewModel {
    private final Tableau tableau;

    public ViewModel(Tableau tableau){
        this.tableau = tableau;
    }

}
