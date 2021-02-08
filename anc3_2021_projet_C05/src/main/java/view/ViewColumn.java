package view;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.ColumnViewModel;
import mvvm.ViewModel;

import java.io.FileInputStream;

public class ViewColumn extends VBox {
    private static final double SPACING = 10;
    private   ViewModel viewModel;
    private ViewCard viewCard;
    private ColumnViewModel viewColumn ;
    private HBox hbox =new HBox();
    private TextField tfColoName = new TextField();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    private final ListView<ViewCard> lvCard = new ListView<>();
    private final ListView<Card> listCard = new ListView<>();
    private Stage primaryStage;

    private int width = 200;
    private int heigth = 100;

    public ViewColumn(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel = viewModel;
        this.viewCard= new ViewCard (primaryStage,viewModel);
        Scene scene = new Scene(this,width,heigth);
        this.primaryStage = primaryStage;
        primaryStage.setScene(scene);
        configComponents();
       // updateLvCart ();
    }

    ViewColumn(Column c)throws Exception {
        this(new ColumnViewModel (c));
    }

    public ViewColumn(ColumnViewModel viewColumn)throws Exception {
        this.viewColumn= viewColumn;
        tfColoName = new TextField();
        configImages();
        hbox.getChildren().addAll(Imleft,tfColoName,Imright);
        this.getChildren ().addAll (hbox,listCard);
        tfColoName.textProperty().bind(viewColumn.colNameProperty());
        listCard.itemsProperty().bind(viewColumn.cardProperty ());
        updateLvCart ();


    }

    private void configComponents() throws Exception {
        configVboxZone();
        configImages();
    }

    private void configVboxZone() throws Exception {
        this.setSpacing(SPACING);
        this.setMaxWidth(200);
        this.getChildren().addAll(hbox,lvCard);
        hbox.getChildren().addAll(Imleft,tfColoName,Imright);
        tfColoName.setText(viewModel.columnProperty().getName());
        lvCard.itemsProperty().bind(getLsViewCard());
    }

    private void configCol() {
        configDecoration();
    }

    private void configDecoration() {
        hbox.setSpacing(60);
        hbox.setTranslateY(55);
    }

    private SimpleListProperty<ViewCard> getLsViewCard() throws Exception {
        SimpleListProperty<ViewCard> list = new SimpleListProperty<>();

        String v = "carte 1";
        StringProperty var = new SimpleStringProperty(v);
        ViewCard vc = new ViewCard(primaryStage, viewModel);
        ObservableList<ViewCard> cards = FXCollections.observableArrayList();
        cards.add(vc);
        list.setValue(cards);

        return list;
    }

    private void configImages() throws Exception {
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));
    }

    public void updateLvCart (){
        System.out.println ("updatecart vcolumn");
        listCard.setCellFactory(viewColum -> new ListCell<> (){
            @Override
            protected void updateItem(Card cat, boolean b){
                super.updateItem(cat, b);
                ViewCard  viewCard = null;
                if(cat != null){
                    try {
                        viewCard = new ViewCard(cat);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println ("gffffffffffff  updateLvCart ");
                   // viewCard.setMaxHeight(200);
                }
                setGraphic(viewCard);
            }

        });
    }
}
