package view;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Colonnes;
import mvvm.ViewModel;

import java.io.FileInputStream;

public class ViewBoard extends VBox {

    private final ViewModel viewModel;
    private Stage primaryStage;

    private  ListView<ViewColumn> lvlistColumn= new ListView<>();;
    private VBox vBox = new VBox();
    private  TextField tfBoardName = new TextField();
    private int width = 1000;
    private int heigth = 700;
    private static final double SPACING = 10;
    private String name = "Tableau";
    private ListView<Colonnes> listColumn = new ListView<>();


    public ViewBoard(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        configComponents();
        configBoard();
        Scene scene = new Scene(this,width,heigth);
        primaryStage.setScene(scene);
    }

   private void configComponents() throws Exception {
        configVboxZone();
    }

    private void configVboxZone() throws Exception {
        this.getChildren().add(vBox);
        vBox.getChildren().addAll(tfBoardName,lvlistColumn);
        tfBoardName.setText(name);
         lvlistColumn.itemsProperty().bind(getLsViewColum());
        compornentsDecoration();
    }

    private void compornentsDecoration (){
        vBox.setSpacing(SPACING);
        vBox.setSpacing(SPACING);
        vBox.setPadding(new Insets(SPACING));
        tfBoardName.setMaxWidth(1040);
        listColumn.setOrientation(Orientation.HORIZONTAL);
    }

    private SimpleListProperty<ViewColumn> getLsViewColum() throws Exception {
        SimpleListProperty<ViewColumn> list = new SimpleListProperty<>();

        String v = "Colonne 1";
        StringProperty var = new SimpleStringProperty(v+" "+lvlistColumn.getFixedCellSize());
        ViewColumn vc = new ViewColumn(primaryStage, viewModel);
        ObservableList<ViewColumn> columns = FXCollections.observableArrayList();

        columns.add(vc);
        list.setValue(columns);

        return list;
    }
    private void configBoard(){
        //configDataBoard();
    }
    /*private void configDataBoard(){
        listColumn.itemsProperty().bind(viewModel.columnProperty());
        System.out.println(listColumn.itemsProperty().toString());//test recup
    }*/


}
