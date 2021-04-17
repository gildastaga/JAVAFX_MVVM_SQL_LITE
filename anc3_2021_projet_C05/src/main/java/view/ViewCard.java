package view;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;;
import javafx.scene.layout.HBox;
import model.Card;
import model.Type;
import mvvm.ViewModelBoard;
import mvvm.ViewModelCard;

import java.io.FileInputStream;
import java.util.Optional;

public class ViewCard extends BorderPane {

    private final ImageView Imup = new ImageView();
    private final ImageView Imdown = new ImageView();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    private final HBox hbox = new HBox();
    private ViewModelCard viewModelCard;
    private final EditableLabel nameCard ;
    private final ViewModelBoard viewModelBoard;

    ViewCard(Card card, ViewModelBoard viewModelBoard) {
        this.viewModelCard = new ViewModelCard(card, viewModelBoard);
        this.viewModelBoard = viewModelBoard;
        this.nameCard = new EditableLabel (card.getName(), viewModelBoard, null, null);
        try {
            configComponents();
            configDisabledBindings();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private void configComponents() throws Exception {
        configImages();
        configVboxZone();
        configDisabledBindings();
        configActions();
    }

    private void configVboxZone() {
        this.setTop (Imup);
        this.setBottom (Imdown);
        this.setRight (Imright);
        this.setLeft (Imleft);
        this.setCenter (nameCard);
        BorderPane.setAlignment (Imup, Pos.TOP_CENTER);
        BorderPane.setAlignment (Imdown,Pos.BOTTOM_CENTER);
        Imright.setTranslateX(0);
        this.setStyle("-fx-background-color: skyblue; -fx-border-radius: 5%;");
    }

    private void configImages() throws Exception {
        FileInputStream UP = new FileInputStream("src/images/up.png");
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream DOWN = new FileInputStream("src/images/down.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");

        Imup.setImage(new Image(UP));
        Imdown.setImage(new Image(DOWN));
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));
    }
    private void configDisabledBindings() {
        Imup.disableProperty ().bind(viewModelCard.imuptCardDisabledProperty());
        Imdown.disableProperty ().bind(viewModelCard.imdowCardDisableProperty());
        Imleft.disableProperty().bind(viewModelCard.imleftCardDisabledProperty());
        Imright.disableProperty().bind(viewModelCard.imRightCardDisabledProperty ());
    }

    private void configActions() throws Exception {
        configActionUp();
        configActionDown();
        configActionRight();
        configActionLeft();
        deleteAction();
    }

    private void configActionUp() {
        Imup.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.viewModelCard.swapCardUp();
            }
        });
    }

    private void configActionDown() {
        Imdown.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.viewModelCard.swapCardDown();
            }
        });
    }

    private void configActionRight() {
        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.viewModelCard.swapCardRight();
            }
        });
    }
    private  void configActionLeft( ) {
        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.viewModelCard.swapCardLeft();
            }
        });
    }

    private void deleteAction() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("confirmation d'action ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("are you sure you want to delete this card? ");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.viewModelCard.deleteCard();
                }
            }
        });
    }
}
