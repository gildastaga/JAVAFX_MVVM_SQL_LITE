package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Tableau;
import mvvm.ViewModel;
import view.View;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Tableau tableau = new Tableau();
        ViewModel viewModel = new ViewModel(tableau);
        View view = new View(stage, viewModel);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
