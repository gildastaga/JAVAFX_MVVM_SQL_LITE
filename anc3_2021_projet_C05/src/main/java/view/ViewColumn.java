package view;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvvm.ViewModel;

import java.io.FileInputStream;

public class ViewColumn extends VBox {
    private static final double SPACING = 10;
    private final ViewModel viewModel;
    private HBox hbox =new HBox();
    private TextField tfColoName = new TextField();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
     private final ListView<ViewCard> lvCard = new ListView<>();
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

    private void configComponents() throws Exception {
        configVboxZone();
        configImages();
    }

    private void configVboxZone() throws Exception {
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
       this.setMaxWidth(200);
        this.getChildren().addAll(hbox,lvCard);
        hbox.getChildren().addAll(Imleft,tfColoName,Imright);
        lvCard.itemsProperty().bind(getLsViewCard());
    }

    private SimpleListProperty<ViewCard> getLsViewCard() throws Exception {
        SimpleListProperty<ViewCard> list = new SimpleListProperty<>();

        String v = "carte 1";
        StringProperty var = new SimpleStringProperty(v);
        ViewCard vc = new ViewCard(primaryStage, viewModel, var);
        ObservableList<ViewCard> cards = FXCollections.observableArrayList();
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
