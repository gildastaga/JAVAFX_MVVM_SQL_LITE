package view;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class ViewBoard extends VBox {

    private final ViewModel viewModel;
    private Stage primaryStage;

    private static ListView<ViewColumn> lvlistColumn;
    private final TextField tfBoardName = new TextField();
    private int weight = 1000;
    private int heigth = 700;

    public ViewBoard(Stage primaryStage, ViewModel viewModel) throws Exception {
        lvlistColumn = new ListView<>();
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;

        configComponents();
        Scene scene = new Scene(this, weight, heigth);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
    }

    private void configComponents() throws Exception {
        //configVboxZone();
    }

    /*private void configVboxZone() throws Exception {
        this.getChildren().addAll(tfBoardName,lvlistColumn);
        lvlistColumn.itemsProperty().bind(getLsViewColum());
        this.setSpacing(20);
    }*/

    /*private SimpleListProperty<ViewColumn> getLsViewColum() throws Exception {
        SimpleListProperty<ViewColumn> list = new SimpleListProperty<>();

        String v = "Colonne 1";
        StringProperty var = new SimpleStringProperty(v);
        ViewColumn vc = new ViewColumn(primaryStage, viewModel, var);
        ObservableList<ViewColumn> columns = FXCollections.observableArrayList();
        columns.add(vc);
        list.setValue(columns);

        return list;
    }*/
}
