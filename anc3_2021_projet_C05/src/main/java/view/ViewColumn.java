package view;

import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.ViewModel;

import java.io.FileInputStream;

public class ViewColumn extends VBox {
    private static final double SPACING = 10;
    private ViewModel viewModel;
    private HBox hbox =new HBox();
    private TextField tfColoName = new TextField();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    public final ListView<ViewCard> listViewCards = new ListView<>();
    private final ListView<Card> cards = new ListView<>();
    private final Label name = new Label();
    private Stage primaryStage;
    private Column column;
    private final IntegerProperty numLineSelectedCard = new SimpleIntegerProperty(-1);

    private int width = 200;
    private int heigth = 100;

    public ViewColumn(ViewModel viewModel, Column column) throws Exception {
        this.viewModel = viewModel;
        this.column = column;
        configComponents();
        configColumn();
    }

    private void configComponents() throws Exception {
        configVboxZone();
        configImages();
    }

    private void configVboxZone() throws Exception {
        this.setSpacing(SPACING);
        this.setMaxWidth(200);
        this.getChildren().addAll(hbox,listViewCards);
        hbox.getChildren().addAll(Imleft,tfColoName,Imright);
    }

    private void configImages() throws Exception {
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));
    }

    private void configColumn() throws Exception{
        configDataComumn();
        configaction();
    }

    public void configDataComumn() throws Exception {
        listViewCards.itemsProperty().bindBidirectional(viewModel.getLsViewCard(column, this));
        numLineSelectedCard.bind(viewModel.getNumLineSelectedCardProperty ());
        viewModel.lineSelectedCard(getCardModel().selectedIndexProperty());
        cards.itemsProperty().bind(viewModel.getlsCardsByColumnProperty(column));
        name.textProperty().bind(new SimpleStringProperty(column.getName()));
        tfColoName.textProperty().bind(new SimpleStringProperty(column.getName()));
    }



    private void configaction() {
        listViewCards.setOnMouseClicked(e -> {
            viewModel.addCard(column);
            try {
                configDataComumn();
            } catch (Exception exception) {
                exception.printStackTrace ();
            }
        });

        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1 ) {
                try {
                    this.viewModel.swapColleft ( column);
                    configDataComumn();

                }catch (Exception ed) {
                    throw new Error(ed.getMessage());
                }

            }
        });
        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1 ) {
                try {
                    this.viewModel.swapColright ( column);
                    configDataComumn();

                }catch (Exception ed) {
                    throw new Error(ed.getMessage());
                }

            }
        });
    }


    public SelectionModel<ViewCard> getCardModel() {
        return listViewCards.getSelectionModel();
    }
}
