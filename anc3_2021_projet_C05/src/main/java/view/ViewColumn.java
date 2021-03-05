package view;

import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.ColumnViewModel;
import mvvm.ViewModel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewColumn extends VBox {
    private  ColumnViewModel columnViewModel;
    private HBox hboxUp =new HBox(),
                hBoxDown = new HBox ();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    public final List<ViewCard> listViewCards = new ArrayList<> ();
    private final ListView<Card> listCards = new ListView<>();
    private final EditableLabel nameColumn ;
    private final IntegerProperty numLineSelectedCard = new SimpleIntegerProperty () ;


    ViewColumn(Column column) {
        this.columnViewModel = new ColumnViewModel (column);
        this.nameColumn = new EditableLabel (column.getName (),false);
        try {
            configBindings();
            configComponents();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        configActionColumn ();
    }

/////////////////////config data//////////////////////
    private void configBindings() throws Exception {
        configDataComumn();
        configDisabledBindings();
    }
    public void configDataComumn() throws Exception {
        listCards.itemsProperty().bind(columnViewModel.getCardsProperty ());
        numLineSelectedCard.bind(columnViewModel.getNumLineSelectedCardProperty ());

    }

    private void configDisabledBindings() {
        Imleft.disableProperty().bind(columnViewModel.imleftColumDisabledProperty());
        Imright.disableProperty().bind(columnViewModel.imRightColumDisabledProperty ());
    }
            //////////////////////// congif disable //////////////////
    private void configComponents() throws Exception {
        configImages();
        configVboxZone();
        updateLvCart ();

    }

    private void configVboxZone() throws Exception {
        hboxUp.getChildren().addAll(Imleft,nameColumn.getName (),Imright);
        hBoxDown.getChildren ().add (listCards);
        this.getChildren().addAll(hboxUp,hBoxDown);
        this.setPrefWidth (200);
    }

    private void configImages() throws Exception {
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));

    }
    public void updateLvCart (){
        listCards.setCellFactory(view -> new ListCell<> (){
            @Override
            protected void updateItem(Card card, boolean b){
                super.updateItem(card, b);
                ViewCard  viewCard = null;
                if(card != null){
                    viewCard = new ViewCard(card);
                }
                setGraphic(viewCard);
            }
        });
    }
/////////////action column //////////////////
    private void configActionColumn() {
        configactionColumunRight();
        configactionColumnLeft();
        addcard();
        deleteAction();
    }
    private void configactionColumnLeft() {
        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1 ) {
                this.columnViewModel.swapColleft ();
            }
        });
    }
    private void configactionColumunRight(){
        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            System.out.println ("je suis entre ");
            if (e.getClickCount() == 1 ) {
                this.columnViewModel.swapColright();
            }
        });
    }
    private void addcard(){
        listCards.setOnMouseClicked(e -> {
            if( e.getClickCount () == 2) {
                columnViewModel.addCard ();
            }
        });
    }

    private void deleteAction() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle(" action confirmation ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("can you delete this column :"+nameColumn.getName () );
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.columnViewModel.deleteColumn();
                }
            }
        });
    }
}
