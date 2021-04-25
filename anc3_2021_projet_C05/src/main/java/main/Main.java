package main;

import DAO.DaoFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModelBoard;
import mySqlitedao.BoardDao;
import view.*;


public class Main extends Application {

@Override
    public void start(Stage stage) throws Exception {

        String name = "MON TABLEAU";
        DaoFactory daoFactory = new DaoFactory ();
        daoFactory.main ();
        BoardDao boardDao = new BoardDao ();
        boardDao.create (new Board(name));
        ViewModelBoard viewModelBoard = new ViewModelBoard(boardDao.find (1));
        TrelloView view = new TrelloView(stage,viewModelBoard,boardDao.find (1));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
