package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;;
import javafx.scene.layout.HBox;
import model.Card;
import model.Type;
import mvvm.CardViewModel;

import java.io.FileInputStream;
import java.util.Optional;

public class ViewCard extends BorderPane {

    private final ImageView Imup = new ImageView();
    private final ImageView Imdown = new ImageView();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    private final HBox hbox = new HBox();
    private  CardViewModel cardViewModel;
    private final EditableLabel nameCard ;

    ViewCard(Card card) {
        this.cardViewModel= new CardViewModel (card);
        this.nameCard = new EditableLabel (card.getName (),false, Type.CARD);
        try {
            configComponents();
            configBindings();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private void configComponents() throws Exception {
        configImages();
        configVboxZone();
        configActions();
    }

    private void configVboxZone() {
        this.setTop (Imup);
        this.setBottom (Imdown);
        this.setRight (Imright);
        this.setLeft (Imleft);
        this.setCenter (nameCard.getTextField ());
        BorderPane.setAlignment (nameCard.getTextField (), Pos.CENTER_RIGHT);
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
                this.cardViewModel.swapCardUp();
            }
        });
    }

    private void configActionDown() {
        Imdown.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.cardViewModel.swapCardDown();
            }
        });
    }

    private void configActionRight() {
        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.cardViewModel.swapCardRight();
            }
        });
    }
    private  void configActionLeft( ){
        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.cardViewModel.swapCardLeft();
            }
        });
    }

    private void configBindings() throws Exception {
        configEditableLabel();
    }

    private void configEditableLabel() {
        nameCard.getTextField().setOnMouseClicked((e) -> {
            if (e.getClickCount() == 2 ) {
                this.nameCard.setEditable(true, Type.BOARD);
            }
        });

        nameCard.getTextField().setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                this.cardViewModel.updateCardName(nameCard.getTextField().getText());
                nameCard.setTextField(false, nameCard.getTextField().getText(), Type.BOARD);
            }
        });
    }

    private void deleteAction() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("confirmation d'action ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("can you delete this :"+nameCard.getTextField () );
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.cardViewModel.deleteCard();
                }
            }
        });
    }
}
