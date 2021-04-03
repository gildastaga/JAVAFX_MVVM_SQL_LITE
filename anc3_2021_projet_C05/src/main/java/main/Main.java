package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModel;
//import view.View;
<<<<<<< HEAD
=======
import mvvm.ViewModelBoard;
>>>>>>> recap
import view.*;


public class Main extends Application {

@Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD
        String name ="Mon Tableau";
        Board board = new Board (name);
        ViewModel viewModel = new ViewModel(board);
        TrelloView view = new TrelloView(stage,viewModel);
=======
        String name = "MON TABLEAU";
        Board board = new Board (name);
        //ViewModel viewModel = new ViewModel(board);
        ViewModelBoard viewModelBoard = new ViewModelBoard(board);
        TrelloView view = new TrelloView(stage,viewModelBoard,board);
>>>>>>> recap
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
