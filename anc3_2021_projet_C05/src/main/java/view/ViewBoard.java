package view;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

    private  ListView<ViewColumn> lvlistColumn;
    private VBox vBox=new VBox();
    private  TextField tfBoardName = new TextField();
    private int weight = 1000;
    private int heigth = 700;
    private static final double SPACING = 10;
    private String name ="tableau";

    public ViewBoard(Stage primaryStage, ViewModel viewModel) throws Exception {

        this.viewModel = viewModel;
        lvlistColumn = new ListView<>();
        this.primaryStage = primaryStage;

        configComponents();
        Scene scene = new Scene(this,weight,heigth);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
    }

   private void configComponents() throws Exception {
        configVboxZone();
    }

    private void configVboxZone() throws Exception {
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
        tfBoardName.setMaxWidth(300);
        this.getChildren().addAll(tfBoardName,lvlistColumn);
        tfBoardName.setText(name+" ");
       lvlistColumn.itemsProperty().bind(getLsViewColum());
        this.setSpacing(SPACING);
    }

    private SimpleListProperty<ViewColumn> getLsViewColum() throws Exception {
        SimpleListProperty<ViewColumn> list = new SimpleListProperty<>();

        String v = "Colonne 1";
        StringProperty var = new SimpleStringProperty(v);
        ViewColumn vc = new ViewColumn(primaryStage, viewModel);
        ObservableList<ViewColumn> columns = FXCollections.observableArrayList();
        columns.add(vc);
        list.setValue(columns);

        return list;
    }



}
