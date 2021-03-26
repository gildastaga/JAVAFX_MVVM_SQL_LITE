package view;

import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Column;
import model.Type;
import mvvm.ViewModelColumn;

import java.io.FileInputStream;
import java.util.Optional;

public class ViewColumn extends VBox {
    private ViewModelColumn viewModelColumn;
    private HBox hboxUp =new HBox(),
                hBoxDown = new HBox ();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    private final ListView<Card> listCards = new ListView<>();
    private final EditableLabel nameColumn ;
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty () ;
    private  final  Column column;


    ViewColumn(Column column) {
        this.column = column;
        this.viewModelColumn = new ViewModelColumn(column);
        this.nameColumn = new EditableLabel (column.getName (),false, Type.COLUMN);
        try {
            configBindings();
            configComponents();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        configActionColumn ();
    }

/******************************************************config data ******************************************************************/

    private void configBindings() throws Exception {
        configDataComumn();
        configDisabledBindings();
        configEditableLabel();
    }

    public void configDataComumn() throws Exception {
        listCards.itemsProperty().bind(viewModelColumn.getCardsProperty ());
        numSelectedColumn.bind(viewModelColumn.getNumSelectedColumnProperty ());
    }

    private void configDisabledBindings() {
        Imleft.disableProperty().bind(viewModelColumn.imleftColumDisabledProperty());
        Imright.disableProperty().bind(viewModelColumn.imRightColumDisabledProperty ());
    }
    /************************************************************ congif disable ********************************************/

    private void configComponents() throws Exception {
        configImages();
        configVboxZone();
        updateLvCart ();
    }

    private void configVboxZone() throws Exception {
        hboxUp.getChildren().addAll(Imleft,nameColumn.getTextField (),Imright);
        hBoxDown.getChildren ().add (listCards);
        this.getChildren().addAll(hboxUp,hBoxDown);
        this.setPrefWidth (220);
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
                this.viewModelColumn.swapColleft ();
            }
        });
    }

    private void configactionColumunRight(){
        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            System.out.println ("je suis entre ");
            if (e.getClickCount() == 1 ) {
                this.viewModelColumn.swapColright();
            }
        });
    }

    private void configEditableLabel() {
        nameColumn.getTextField().setOnMouseClicked((e) -> {
            if (e.getClickCount() == 2 ) {
                this.nameColumn.setEditable(true, Type.BOARD);
            }
        });

        nameColumn.getTextField().setOnKeyPressed((e) -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                this.viewModelColumn.updateColumnName(nameColumn.getTextField().getText());
                nameColumn.setTextField(false, nameColumn.getTextField().getText(), Type.BOARD);
            }
        });
    }

    private void addcard(){
        listCards.setOnMouseClicked(e -> {
            if( e.getClickCount () == 2) {
                viewModelColumn.addCard ();
            }
        });
    }

    private void deleteAction() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle(" action confirmation ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("can you delete this column :"+nameColumn.getTextField () );
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.viewModelColumn.deleteColumn();
                }
            }
        });
    }
}
