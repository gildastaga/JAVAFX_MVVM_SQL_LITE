package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.ViewModel;
import view.ViewBoard;

public class TrelloView extends VBox {

    private  final HBox hBox = new HBox ();
    private final ViewBoard viewboard;
    private  EditableLabel nameBoard;
    private  final ViewModel viewModel;

    public TrelloView(Stage primaryStage,ViewModel viewModel ,Board board ) {
        this.viewModel =viewModel;
        this.viewboard = new ViewBoard(new BoardViewModel (board));
        this.nameBoard= new EditableLabel (board.getName (),false);
        Scene scene = new Scene(this, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trello");
        configVboxZone();
    }

    private void configVboxZone()  {
        hBox.getChildren ().addAll (nameBoard.getName ());
        this.getChildren().addAll (hBox,viewboard);
    }
}
