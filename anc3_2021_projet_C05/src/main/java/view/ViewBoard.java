package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
<<<<<<< HEAD
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.Column;
import mvvm.ViewModel;


public class ViewBoard extends VBox {

    private  ViewModel viewModel;
    private  ViewColumn viewColumn;
    private Stage primaryStage;
    public  ListView<ViewColumn> listViewColumn= new ListView<>();;
    private VBox vBox = new VBox();
    private  TextField tfBoardName = new TextField();
    private HBox hBoxLb = new HBox();
    private HBox hBoxTf = new HBox();
    private Label lbBoardName = new Label();
    private int width = 1000;
    private int heigth = 700;
    private static final double SPACING = 10;
    private ListView<Column> columns = new ListView<>();
    private final IntegerProperty numLineSelectedColumn = new SimpleIntegerProperty(-1);


    public ViewBoard(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel = viewModel;
        Scene scene = new Scene(this, width, heigth);
        this.primaryStage = primaryStage;
        primaryStage.setScene(scene);
        configComponents();
        configBoard();
    }

    private void configComponents() {
        this.getChildren().addAll(lbBoardName, listViewColumn);
        tfBoardName.setVisible(false);
        componentsDecoration();
    }

    private void componentsDecoration () {
        this.setSpacing(SPACING);
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
        tfBoardName.setMaxWidth(1040);
        listViewColumn.setOrientation(Orientation.HORIZONTAL);
    }

    private void configBoard() throws Exception{
=======
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import model.Column;
import mvvm.ViewModelBoard;


public class ViewBoard extends ListView<Column> {

    private ViewModelBoard viewModelBoard;
    private final IntegerProperty numLineSelectedColumn = new SimpleIntegerProperty(-1);

    ViewBoard(ViewModelBoard viewModelBoard)  {
        this.viewModelBoard = viewModelBoard;
        this.setOrientation(Orientation.HORIZONTAL);
        configBoard();
        updateLvcColon();
    }

    private void configBoard() {
>>>>>>> recap
        configDataBoard();
        configactionAddCol();
    }

<<<<<<< HEAD
    public void configDataBoard() throws Exception {
        numLineSelectedColumn.bind(viewModel.getNumLineSelectedColumnProperty());
        viewModel.lineSelectedColumn(getColumnModel().selectedIndexProperty());
        //viewModel.lineSelectedColumn ().bind (columns.getSelectionModel().selectedIndexProperty());
        lbBoardName.textProperty().bind(viewModel.getBordNameProperty());
        columns.itemsProperty().bind(viewModel.getColumnsProperty());
        listViewColumn.itemsProperty().bind(viewModel.getLsViewColum(this));
    }

    public SelectionModel<ViewColumn> getColumnModel() {
        return listViewColumn.getSelectionModel();
    }

    private void configactionAddCol() {
        listViewColumn.setOnMouseClicked (e ->{
            try {
                viewModel.addColumn();
                configDataBoard();
            } catch (Exception exception) {
                exception.printStackTrace ();
            }
        } );

=======
    public void configDataBoard() {
        numLineSelectedColumn.bind(viewModelBoard.getNumLineSelectedColumnProperty());
        this.itemsProperty().bind(viewModelBoard.getColumnsProperty());
    }

    private void updateLvcColon() {
        this.setCellFactory(view -> new ListCell<>(){
            @Override
            protected void updateItem(Column col, boolean b){
                super.updateItem(col, b);
                ViewColumn viewColumn = null;
                if(col != null){
                    viewColumn = new ViewColumn(col, viewModelBoard);
                }
                setGraphic(viewColumn);
            }
        });
    }

    private void configactionAddCol() {
        this.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 ) {
                viewModelBoard.addColumn();
            }
        });
>>>>>>> recap
    }
}
