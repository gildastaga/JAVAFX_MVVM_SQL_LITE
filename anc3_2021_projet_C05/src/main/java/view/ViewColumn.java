package view;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cartes;
import model.Colonnes;
import mvvm.ColoneViewModel;
import mvvm.ViewModel;

import java.io.FileInputStream;

public class ViewColumn extends VBox {
    private static final double SPACING = 10;
    private  ViewModel viewModel;

    private ColoneViewModel viewColumn ;
    private HBox hbox =new HBox();
    private TextField tfColoName = new TextField();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
     private final ListView<Cartes> lvCard = new ListView<>();
    private Stage primaryStage;

    private int weight = 250;
    private int heigth = 600;

    public ViewColumn(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;

        configComponents();
        Scene scene = new Scene(this,weight,heigth);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
    }

    ViewColumn(Colonnes c)throws Exception{
        this(new ColoneViewModel (c));

    }
    public ViewColumn(ColoneViewModel viewColumn)throws Exception{
        this.viewColumn= viewColumn;
       // this.setAlignment(Pos.CENTER);
        configComponents();
        configCol();
        HBox optionCol =new HBox();
        TextField tfColoName = new TextField();
        ListView<Cartes> listcol =new ListView<>();
        ImageView Imleft = new ImageView();
        ImageView Imright = new ImageView();
        optionCol.getChildren().addAll(Imleft,tfColoName,Imright);
       // tfColoName.textProperty().bind(viewColumn.colNameProperty());
       // listcol.itemsProperty().bind(viewColumn.colNameProperty());
    }
    private void configComponents() throws Exception {
        configVboxZone();
        configImages();
    }

    private void configVboxZone() throws Exception {
        this.setSpacing(SPACING);
        //this.setPadding(new Insets(SPACING));
       this.setMaxWidth(200);
        this.getChildren().addAll(hbox,lvCard);
        hbox.getChildren().addAll(Imleft,tfColoName,Imright);
       // tfColoName.setText(viewModel.columnProperty().getName());
       // lvCard.itemsProperty().bind(getLsViewCard());
    }
    private void configCol(){
        configDataCol();
    }
    private void configDataCol(){
        //lvCard.itemsProperty().bind(viewModel.getListCardByColumn());
        System.out.println(lvCard.itemsProperty().toString());//test recup
    }
    private SimpleListProperty<Cartes> getLsViewCard() throws Exception {
        SimpleListProperty<Cartes> list = new SimpleListProperty<>();

        String v = "carte 1";
        StringProperty var = new SimpleStringProperty(v);
        //Cartes vc = new Cartes(primaryStage, viewModel, var);
        Cartes vc = new Cartes(v);
        ObservableList<Cartes> cards = FXCollections.observableArrayList();
        cards.add(vc);
        list.setValue(cards);

        return list;
    }
    private void configImages() throws Exception{
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");

        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));
    }


}
