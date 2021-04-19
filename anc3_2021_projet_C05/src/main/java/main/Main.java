package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModelBoard;
import view.*;


public class Main extends Application {

@Override
    public void start(Stage stage) throws Exception {

        String name = "MON TABLEAU";
        Board board = new Board (name);
        ViewModelBoard viewModelBoard = new ViewModelBoard(board);
        TrelloView view = new TrelloView(stage,viewModelBoard,board);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
