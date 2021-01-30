package view;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cartes;
import mvvm.ViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ViewColumn extends VBox {

    private final ViewModel viewModel;
    private final ListView<ViewCard> lvCard = new ListView<>();
    private Stage primaryStage;

    private int weight = 250;
    private int heigth = 700;

    public ViewColumn(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;

        configComponents();
        Scene scene = new Scene(this, weight, heigth);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
    }

    private void configComponents() throws Exception {
        configVboxZone();
    }

    private void configVboxZone() throws Exception {
        this.getChildren().add(lvCard);
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
}
