package view;

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
import model.Card;
import model.Column;
import mvvm.ViewModel;

import java.io.FileInputStream;
import java.util.Optional;

public class ViewCard extends VBox {

    private final VBox up = new VBox();
    private final HBox left_right = new HBox();
    private final VBox down = new VBox();
    private  ViewModel viewModel;
    private final Label lbCarte = new Label();
    private final ImageView Imup = new ImageView();
    private final ImageView Imdown = new ImageView();
    private final ImageView ImleftCard = new ImageView();
    private final ImageView ImrightCard = new ImageView();
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
        FileInputStream UP = new FileInputStream("src/images/up.png");
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream DOWN = new FileInputStream("src/images/down.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");

        Imup.setImage(new Image(UP));
        Imdown.setImage(new Image(DOWN));
        ImleftCard.setImage(new Image(LEFT));
        ImrightCard.setImage(new Image(RIGHT));
        up.getChildren().add(Imup);
        left_right.getChildren().addAll(ImleftCard, lbCarte, ImrightCard);
        down.getChildren().add(Imdown);
    }

    private void componentsDecoration() {
        up.setTranslateX(65);
        lbCarte.setTranslateX(35);
        ImrightCard.setTranslateX(65);
        down.setTranslateX(65);
        left_right.setTranslateY(5);
    }

    private void configCard() throws Exception{
        configDataBindingCard();
        configActions();
    }

    private void configDataBindingCard() {
        lbCarte.textProperty().bind(new SimpleStringProperty(card.getName()));
    }

    private void configActions() throws Exception {
        configActionUp();
        configActionDown();
        configActionRight();
        configActionLeft();
        actionDeleteCard();
    }

    private void configActionUp() {
        Imup.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                try {
                    viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardUp(column);
                    viewColumn.configDataComumn();
                }catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
            }
        });
    }

    private void configActionDown() {
        Imdown.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                try {
                    viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardDown(column);
                    viewColumn.configDataComumn();
                } catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
            }
        });
    }

    private void configActionRight() {
        ImrightCard.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                try {
                    //viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardRight(column);
                    viewColumn.configDataComumn();
                    viewBoard.configDataBoard();
                } catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
            }
        });
    }
    private void configActionLeft() {
        ImleftCard.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1) {
                try {
                   /// viewModel.lineSelectedCard(viewColumn.getCardModel().selectedIndexProperty());
                    this.viewModel.swapCardLeft(column);
                    viewColumn.configDataComumn();
                    viewBoard.configDataBoard();
                } catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
            }
        });
    }
    private void actionDeleteCard(){
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()){
                Alert dialogC= new Alert (Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("confirmation d'action ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("can you delete this :"+card.getName () );
                Optional<ButtonType> answer= dialogC.showAndWait();
                if(answer.get() == ButtonType.OK) {
                    this.viewModel.deleteCard (card, column);
                }
            }
        });
    }
}
