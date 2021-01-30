package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Tableau;
import mvvm.ViewModel;
//import view.View;
import view.ViewBoard;
import view.ViewCard;
import view.ViewCard;
import view.ViewColumn;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Tableau tableau = new Tableau();
        ViewModel viewModel = new ViewModel(tableau);
        //ViewCard viewcard = new ViewCard(stage, viewModel);
        ViewColumn viewcolumn = new ViewColumn(stage, viewModel);
        //ViewBoard viewboard = new ViewBoard(stage, viewModel);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
