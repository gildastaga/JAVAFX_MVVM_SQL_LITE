package view;

import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
<<<<<<< HEAD
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.ViewModel;
=======
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Column;
import model.Type;
import mvvm.ViewModelBoard;
import mvvm.ViewModelColumn;
>>>>>>> recap

import java.io.FileInputStream;
import java.util.Optional;

public class ViewColumn extends VBox {
<<<<<<< HEAD
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
    private ViewBoard viewBoard;
    private ViewColumn viewColumn;
    private final IntegerProperty numLineSelectedCard = new SimpleIntegerProperty(-1);

    private int width = 200;
    private int heigth = 100;

    public ViewColumn(ViewModel viewModel, Column column, ViewBoard viewBoard) throws Exception {
        this.viewModel = viewModel;
        this.column = column;
        this.viewBoard = viewBoard;
        configComponents();
        configDisabledBindings();
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
=======
    private ViewModelColumn viewModelColumn;
    private HBox hboxUp =new HBox(),
                hBoxDown = new HBox ();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    private final ListView<Card> listCards = new ListView<>();
    private final EditableLabel nameColumn ;
    private final IntegerProperty numSelectedColumn = new SimpleIntegerProperty () ;
    private  final  Column column;
    private final ViewModelBoard viewModelBoard;


    ViewColumn(Column column, ViewModelBoard viewModelBoard) {
        this.viewModelBoard = viewModelBoard;
        this.column = column;
        this.viewModelColumn = new ViewModelColumn(column, viewModelBoard);
        this.nameColumn = new EditableLabel (column.getName());
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
        hboxUp.getChildren().addAll(Imleft,nameColumn,Imright);
        hBoxDown.getChildren ().add (listCards);
        this.getChildren().addAll(hboxUp,hBoxDown);
        this.setPrefWidth (220);
        Imright.setTranslateX(130);
        nameColumn.setTranslateX(65);
>>>>>>> recap
    }

    private void configImages() throws Exception {
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));
    }

<<<<<<< HEAD
    private void configDisabledBindings() {
        //Imleft.disableProperty().bind(viewModel.imleftColumDisabledProperty());
    }

    private void configColumn() throws Exception {
        configDataComumn();
        configaction();
        deleteAction();
    }

    public void configDataComumn() throws Exception {
        listViewCards.itemsProperty().bindBidirectional(viewModel.getLsViewCard(column, this, viewBoard));
        cards.itemsProperty().bind(viewModel.getlsCardsByColumnProperty(column));
        numLineSelectedCard.bind(viewModel.getNumLineSelectedCardProperty ());
        viewModel.lineSelectedCard(getCardModel().selectedIndexProperty());
        name.textProperty().bind(new SimpleStringProperty(column.getName()));
        tfColoName.textProperty().bind(new SimpleStringProperty(column.getName()));
    }

    private void configaction() {
        /*cards.setOnMouseClicked(e -> {
            viewModel.addCard(column.getCard(numLineSelectedCard.intValue()), column);
            try {
                configDataComumn();
            } catch (Exception exception) {
                exception.printStackTrace ();
            }
        });*/

        Imleft.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1 ) {
                try {
                    this.viewModel.swapColleft (column);

                }catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }

            }
        });

        Imright.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 1 ) {
                try {
                    this.viewModel.swapColright();
                }catch (Exception ed) {
                    System.out.println(ed.getMessage());
                }
=======
    public void updateLvCart (){
        listCards.setCellFactory(view -> new ListCell<> (){
            @Override
            protected void updateItem(Card card, boolean b){
                super.updateItem(card, b);
                ViewCard  viewCard = null;
                if(card != null){
                    viewCard = new ViewCard(card, viewModelBoard);
                }
                setGraphic(viewCard);
            }
        });
    }

    /************************************************************ action column ********************************************/

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
>>>>>>> recap
            }
        });
    }

<<<<<<< HEAD
    public SelectionModel<ViewCard> getCardModel() {
        return listViewCards.getSelectionModel();
=======
    private void addcard(){
        listCards.setOnMouseClicked(e -> {
            if( e.getClickCount () == 2) {
                viewModelColumn.addCard ();
            }
        });
>>>>>>> recap
    }

    private void deleteAction() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.isPopupTrigger()) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
<<<<<<< HEAD
                dialogC.setTitle("confirmation d'action ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("can you delete this column :" + column.getName());
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.viewModel.deleteColumn(column);
=======
                dialogC.setTitle("action confirmation ");
                dialogC.setHeaderText(null);
                dialogC.setContentText("are you sure you want to delete this column?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    this.viewModelColumn.deleteColumn();
>>>>>>> recap
                }
            }
        });
    }
}
