package view;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvvm.ViewModel;

import java.io.FileInputStream;

public class ViewCard extends VBox {

    private final VBox up = new VBox();
    private final HBox left_right = new HBox();
    private final VBox down = new VBox();
    private final ViewModel viewModel;
    private final Label lbCarte1 = new Label();
    private final ImageView Imup = new ImageView();
    private final ImageView Imdown = new ImageView();
    private final ImageView Imleft = new ImageView();
    private final ImageView Imright = new ImageView();
    private int weight = 200;
    private int heigth = 150;

    public ViewCard(Stage primaryStage, ViewModel viewModel, StringProperty nom) throws Exception {
        this.viewModel = viewModel;
        lbCarte1.textProperty().bind(nom);
        configComponents();
        Scene scene = new Scene(this, weight, heigth);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
    }

    private void configComponents() throws Exception {
        configVboxZone();
        componentsDecoration();
        configImages();
    }

    private void configVboxZone() {
        this.getChildren().addAll(up, left_right, down);
        this.setStyle("-fx-background-color: skyblue; -fx-border-radius: 25%;");
    }

    private void configImages() throws Exception{
        FileInputStream UP = new FileInputStream("src/images/up.png");
        FileInputStream LEFT = new FileInputStream("src/images/left.png");
        FileInputStream DOWN = new FileInputStream("src/images/down.png");
        FileInputStream RIGHT = new FileInputStream("src/images/right.png");

        Imup.setImage(new Image(UP));
        Imdown.setImage(new Image(DOWN));
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));

        up.getChildren().add(Imup);
        left_right.getChildren().addAll(Imleft, lbCarte1, Imright);
        down.getChildren().add(Imdown);
    }

    private void componentsDecoration() {
        up.setTranslateX(85);
        lbCarte1.setTranslateX(55);
        Imright.setTranslateX(140);
        down.setTranslateX(85);
        down.setTranslateY(40);
        left_right.setTranslateY(20);
    }
}
