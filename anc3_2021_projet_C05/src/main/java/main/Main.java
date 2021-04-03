package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModel;
//import view.View;
import mvvm.ViewModelBoard;
import view.*;


public class Main extends Application {

@Override
    public void start(Stage stage) throws Exception {
        String name = "MON TABLEAU";
        Board board = new Board (name);
        //ViewModel viewModel = new ViewModel(board);
        ViewModelBoard viewModelBoard = new ViewModelBoard(board);
        TrelloView view = new TrelloView(stage,viewModelBoard,board);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
