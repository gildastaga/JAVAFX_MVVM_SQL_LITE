package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModel;
//import view.View;
import view.*;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        String name ="board";
        Board board = new Board (name);
        ViewModel viewModel = new ViewModel(board);
        View view = new View(stage,viewModel);
        //ViewBoard viewboard = new ViewBoard(stage, viewModel);
       //ViewColumn viewcolumn = new ViewColumn(stage, viewModel);
       // ViewCard viewcard = new ViewCard(stage, viewModel);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
