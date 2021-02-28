package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Column;
import mvvm.ViewModel;
import view.ViewBoard;

public class TrelloView extends VBox {

    private final ViewBoard viewboard;
    private int width = 1025;
    private int heigth = 725;

    public TrelloView(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewboard = new ViewBoard(primaryStage, viewModel);
        Scene scene = new Scene(this, width, heigth);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trello");
        configVboxZone();
    }

    private void configVboxZone()  {
        this.getChildren().add(viewboard);
    }
}
