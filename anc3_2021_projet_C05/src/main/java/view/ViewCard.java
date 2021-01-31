package view;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private static final double SPACING = 10;
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
        configImages();
        componentsDecoration();
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
        Imleft.setImage(new Image(LEFT));
        Imright.setImage(new Image(RIGHT));

        up.getChildren().add(Imup);
        left_right.getChildren().addAll(Imleft, lbCarte1, Imright);
        down.getChildren().add(Imdown);
    }

    private void componentsDecoration() {
        up.setTranslateX(55);
        lbCarte1.setTranslateX(25);
        Imright.setTranslateX(60);
        down.setTranslateX(55);
        //down.setTranslateY(20);
        left_right.setTranslateY(5);
    }
}
