package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cartes;
import mvvm.ViewModel;

public class View extends VBox {

    private final ViewModel viewModel;

    private static final int TEXTSIZE = 700, SPACING = 10;
    private final TextField editLine = new TextField();
    private final HBox columnZone = new HBox();
    private final ListView<String> Colonne1 = new ListView<>();
    private final ListView<String> Colonne2 = new ListView<>();
    private final ListView<String> Colonne3 = new ListView<>();

    public View(Stage primaryStage, ViewModel viewModel) {
        this.viewModel = viewModel;
        configComponents();
        Scene scene = new Scene(this, 1000, 600);
        primaryStage.setTitle("Trello");
        primaryStage.setScene(scene);
    }

    private void configComponents() {
        configColumnZone();
        configWindow();
    }

    private void configColumnZone() {
        columnZone.setSpacing(SPACING);
        editLine.setPrefWidth(TEXTSIZE);
        columnZone.getChildren().addAll(Colonne1, Colonne2, Colonne3);
    }

    /*private void configTextZone() {
        textZone.setSpacing(SPACING);
        lvLines.setPrefWidth(TEXTSIZE);
        textZone.getChildren().addAll(lvLines, lbNbLines);
    }*/

    private void configWindow() {
        this.setPadding(new Insets(SPACING));
        this.setSpacing(10);
        this.getChildren().addAll(editLine, columnZone);
    }
}
