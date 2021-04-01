package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private StringProperty message = new SimpleStringProperty () ;
    private final MenuBar menu = new MenuBar();
    private final Menu fichierButton = new Menu("Fichier");
    private final Menu éditionButton = new Menu("Edition");
    private final MenuItem colonne = new MenuItem("Nouvelle colonne");
    private final MenuItem quitter = new MenuItem("Quitter");
    private final MenuItem annuler = new MenuItem ("Annuler :"+message.get ());
    private final MenuItem refaire = new MenuItem("Refaire");

    private final IntegerProperty menuItemfichieSelected = new SimpleIntegerProperty ();
    private final ViewBoard viewboard;
    private  EditableLabel nameBoard;
    private  ViewModel viewModel;
    private ViewModelBoard viewModelBoard;
    private final Stage stage;

    public TrelloView(Stage stage,ViewModelBoard viewModelBoard ,Board board ) throws Exception {
        this.viewModelBoard = viewModelBoard;
        this.viewboard = new ViewBoard(new ViewModelBoard(board));
        this.stage = stage;
        this.nameBoard = new EditableLabel (board.getName (),false, Type.BOARD);
        Scene scene = new Scene(this, 1050, 800);
        stage.setScene(scene);
        stage.setTitle("Trello");
        configVboxZone();
        ConfigMenu();
        configBindings();
    }


    private void configVboxZone()  {
        this.getChildren().addAll (menu,nameBoard.getTextField(),viewboard);
        annuler.setAccelerator (KeyCombination.keyCombination ("Ctrl+z"));
        refaire.setAccelerator (KeyCombination.keyCombination ("Ctrl+y"));
        éditionButton.getItems().addAll(annuler, refaire);
        fichierButton.getItems().addAll(colonne, quitter);
        menu.getMenus().addAll(fichierButton, éditionButton);
        this.setSpacing(5);

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
        nameBoard.getTextField().setOnMouseClicked((e) -> {
            if (e.getClickCount() == 2 ) {
                this.nameBoard.setEditable(true, Type.BOARD);
            }
        });

        nameBoard.getTextField().setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                this.viewModelBoard.updateBordName(nameBoard.getTextField().getText());
                nameBoard.setTextField(false, nameBoard.getTextField().getText(), Type.BOARD);
            }
        });

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
        ConfigMenu();
    }
   /* private void configbind(){
        viewModelBoard.menuItemEditSelected ().bind (éditionButton.);


        menuItemfichieSelected.bind (viewModelBoard.menuItemfichieSelected ());
    }*/

    public void ConfigMenu(){
        System.out.println (viewModelBoard.getMassage ().get ()+"10110101010101");
        message =viewModelBoard.getMassage ();
        System.out.println (message.get () +"  ppppppppppppppp");
        annuler.disableProperty().bind(viewModelBoard.disableUndoProperty());
        refaire.disableProperty().bind(viewModelBoard.disableRedoProperty());
    }


}
