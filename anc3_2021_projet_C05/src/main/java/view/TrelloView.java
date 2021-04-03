package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;
import model.Type;
import mvvm.ViewModelBoard;
import mvvm.ViewModel;

public class TrelloView extends VBox {

    private String message;
    private final MenuBar menu = new MenuBar();
    private final Menu fichierButton = new Menu("Fichier");
    private final Menu éditionButton = new Menu("Edition");
    private final MenuItem colonne = new MenuItem("Nouvelle colonne");
    private final MenuItem quitter = new MenuItem("Quitter");
    private final MenuItem annuler = new MenuItem("Annuler ");
    private final MenuItem refaire = new MenuItem("Refaire");

    private final ViewBoard viewboard;
    private  EditableLabel nameBoard;
    private  ViewModel viewModel;
    private ViewModelBoard viewModelBoard;
    private final Stage stage;

    public TrelloView(Stage stage,ViewModelBoard viewModelBoard ,Board board ) throws Exception {
        this.viewModelBoard = viewModelBoard;
        this.viewboard = new ViewBoard(viewModelBoard);
        this.stage = stage;
        ConfigMenu();
        this.nameBoard = new EditableLabel (board.getName());
        Scene scene = new Scene(this, 1050, 800);
        stage.setScene(scene);
        stage.setTitle("Trello");
        configVboxZone();
        configBindings();
    }

    private void configVboxZone()  {
        this.getChildren().addAll (menu, nameBoard, viewboard);
        annuler.setAccelerator (KeyCombination.keyCombination ("Ctrl+z"));
        refaire.setAccelerator (KeyCombination.keyCombination ("Ctrl+y"));
        éditionButton.getItems().addAll(annuler, refaire);
        fichierButton.getItems().addAll(colonne, quitter);
        menu.getMenus().addAll(fichierButton, éditionButton);
        this.setSpacing(20);
    }

    private void configBindings() throws Exception {
        configEditableLabel();
        menu.setOnDragDetected (e ->{
            if(e.equals (fichierButton)){
                fichierButton.setDisable (false);
            }
        });
    }
    
    private void configEditableLabel() {

        colonne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewModelBoard.addColumn();
            }
        });

        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewModelBoard.undo();
            }
        });

        refaire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewModelBoard.redo();
            }
        });
    }

    public void ConfigMenu(){
        annuler.disableProperty().bind(viewModelBoard.disableUndoProperty());
        refaire.disableProperty().bind(viewModelBoard.disableRedoProperty());
        // TODO
    }

}
