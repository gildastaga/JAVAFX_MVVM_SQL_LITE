package view;

<<<<<<< HEAD
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.ViewModel;
=======
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
>>>>>>> recap

import java.io.FileInputStream;
import java.util.Optional;

<<<<<<< HEAD
public class ViewCard extends VBox {

    private final VBox up = new VBox();
    private final HBox left_right = new HBox();
    private final VBox down = new VBox();
    private  ViewModel viewModel;
    private final Label lbCarte = new Label();
=======
public class ViewCard extends BorderPane {

>>>>>>> recap
    private final ImageView Imup = new ImageView();
    private final ImageView Imdown = new ImageView();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
<<<<<<< HEAD
    private int weight = 200;
    private int heigth = 150;
    private static final double SPACING = 10;
    private Card card;
    private Column column;
    private ViewColumn viewColumn;
    private ViewBoard viewBoard;


    public ViewCard(ViewModel viewModel, Card card, Column column, ViewColumn viewColumn, ViewBoard viewBoard) throws Exception {
        this.viewModel = viewModel;
        this.card = card;
        this.column = column;
        this.viewColumn = viewColumn;
        this.viewBoard = viewBoard;
        Scene scene = new Scene(this, weight, heigth);
        configComponents();
    }

    private void configComponents() throws Exception {
        configVboxZone();
        configImages();
        componentsDecoration();
        configCard();
    }

    private void configVboxZone() {
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
        this.setMaxWidth(180);

        this.getChildren().addAll(up, left_right, down);
        this.setStyle("-fx-background-color: skyblue; -fx-border-radius: 10%;");
    }

    private void configImages() throws Exception{
=======
    private final HBox hbox = new HBox();
    private ViewModelCard viewModelCard;
    private final EditableLabel nameCard ;
    private final ViewModelBoard viewModelBoard;

    ViewCard(Card card, ViewModelBoard viewModelBoard) {
        this.viewModelCard = new ViewModelCard(card, viewModelBoard);
        this.viewModelBoard = viewModelBoard;
        this.nameCard = new EditableLabel (card.getName());
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
>>>>>>> recap
        FileInputStream UP = new FileInputStream("src/images/up.png");
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream DOWN = new FileInputStream("src/images/down.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");

        Imup.setImage(new Image(UP));
        Imdown.setImage(new Image(DOWN));
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));
<<<<<<< HEAD
        up.getChildren().add(Imup);
        left_right.getChildren().addAll(Imleft, lbCarte, Imright);
        down.getChildren().add(Imdown);
    }

    private void componentsDecoration() {
        up.setTranslateX(65);
        lbCarte.setTranslateX(35);
        Imright.setTranslateX(65);
        down.setTranslateX(65);
        left_right.setTranslateY(5);
    }

    private void configCard() throws Exception{
        configDataBindingCard();
        configActions();
    }

    private void configDataBindingCard() {
        lbCarte.textProperty().bind(new SimpleStringProperty(card.getName()));
=======
    }
    private void configDisabledBindings() {
        Imup.disableProperty ().bind(viewModelCard.imuptCardDisabledProperty());
        Imdown.disableProperty ().bind(viewModelCard.imdowCardDisableProperty());
        Imleft.disableProperty().bind(viewModelCard.imleftCardDisabledProperty());
        Imright.disableProperty().bind(viewModelCard.imRightCardDisabledProperty ());
>>>>>>> recap
    }

    private void configActions() throws Exception {
        configActionUp();
        configActionDown();
        configActionRight();
<<<<<<< HEAD
=======
        configActionLeft();
>>>>>>> recap
        deleteAction();
    }

    private void configActionUp() {
        Imup.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
<<<<<<< HEAD
                try {
                    viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardUp(column);
                    viewColumn.configDataComumn();
                }catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
=======
                this.viewModelCard.swapCardUp();
>>>>>>> recap
            }
        });
    }

    private void configActionDown() {
        Imdown.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
<<<<<<< HEAD
                try {
                    viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardDown(column);
                    viewColumn.configDataComumn();
                } catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
=======
                this.viewModelCard.swapCardDown();
>>>>>>> recap
            }
        });
    }

    private void configActionRight() {
        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
<<<<<<< HEAD
                try {
                    viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardRight(column);
                    viewColumn.configDataComumn();
                    viewBoard.configDataBoard();
                } catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
            }
        });

        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                try {
                    viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardLeft(column);
                    viewColumn.configDataComumn();
                    viewBoard.configDataBoard();
                } catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
=======
                this.viewModelCard.swapCardRight();
            }
        });
    }
    private  void configActionLeft( ){
        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                this.viewModelCard.swapCardLeft();
>>>>>>> recap
            }
        });
    }

    private void deleteAction() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("confirmation d'action ");
                dialogC.setHeaderText(null);
<<<<<<< HEAD
                dialogC.setContentText("can you delete this :" + card.getName());
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.viewModel.deleteCard(card, column);
                    try {
                        viewColumn.configDataComumn();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
=======
                dialogC.setContentText("are you sure you want to delete this card? ");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.viewModelCard.deleteCard();
>>>>>>> recap
                }
            }
        });
    }
}
