package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;
import model.Column;
import model.Type;
import mvvm.BoardViewModel;
import mvvm.ViewModel;
import view.ViewBoard;

public class TrelloView extends VBox {

    private  final VBox vbox = new VBox ();
    private final ViewBoard viewboard;
    private  EditableLabel nameBoard;
    private  final ViewModel viewModel;
    private  BoardViewModel boardviewModel;

    public TrelloView(Stage primaryStage,ViewModel viewModel ,Board board ) throws Exception {
        this.viewModel =viewModel;
        this.viewboard = new ViewBoard(new BoardViewModel (board));
        this.nameBoard= new EditableLabel (board.getName (),false, Type.BOARD);
        Scene scene = new Scene(this, 1050, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trello");
        configVboxZone();
        configBindings();
    }

    private void configVboxZone()  {
        this.getChildren().addAll (nameBoard.getTextField(),viewboard);
        this.setSpacing(10);
    }

    private void configBindings() throws Exception {
        configEditableLabel();
    }

    private void configEditableLabel() {
        nameBoard.getTextField().setOnMouseClicked((e) -> {
            if (e.getClickCount() == 2 ) {
                this.nameBoard.setEditable(true, Type.BOARD);
            }
        });

        nameBoard.getTextField().setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                this.viewModel.updateBordName(nameBoard.getTextField().getText());
                nameBoard.setTextField(false, nameBoard.getTextField().getText(), Type.BOARD);
            }
        });
    }
}
