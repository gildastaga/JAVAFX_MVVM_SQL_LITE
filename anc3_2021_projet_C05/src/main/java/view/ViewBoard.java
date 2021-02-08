package view;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.ViewModel;


public class ViewBoard extends VBox {

    private  ViewModel viewModel;
    private BoardViewModel boardViewModel;
    private  ViewColumn viewColumn;
    private Stage primaryStage;
    private  ListView<ViewColumn> lvlistColumn= new ListView<>();;
    private VBox vBox = new VBox();
    private  TextField tfBoardName = new TextField();
    private int width = 1000;
    private int heigth = 700;
    private static final double SPACING = 10;
    private String name = "Tableau";
    private ListView<Column> listColumn = new ListView<>();

    ViewBoard(Board b)throws Exception{
        this( new BoardViewModel (b));

    }
    public ViewBoard( BoardViewModel viewBoard)throws Exception{
        this.boardViewModel = viewBoard;
        this.setAlignment(Pos.CENTER);
        tfBoardName = new TextField();
        listColumn =new ListView<>();
        this.getChildren ().addAll (tfBoardName,listColumn);
        tfBoardName.textProperty().bind(viewModel.boardNameProperty());
        listColumn.itemsProperty().bind(viewModel.columnProperty ());

        configaction();
    }


    public ViewBoard(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel = viewModel;
        this.viewColumn = new ViewColumn(primaryStage,viewModel);
        Scene scene = new Scene(this,width,heigth);
        this.primaryStage = primaryStage;
        primaryStage.setScene(scene);
        configComponents();
        updateLvcColon();
        configBoard();
        configaction();
    }

   private void configComponents() throws Exception {
        configVboxZone();
    }

    private void configVboxZone() throws Exception {
        this.getChildren().add(vBox);
        this.getChildren().addAll(tfBoardName,listColumn);
        tfBoardName.setText(name);
        lvlistColumn.itemsProperty().bind(getLsViewColum());
        compornentsDecoration();
    }

    private void compornentsDecoration (){
        this.setSpacing(SPACING);
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
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
        configDataBoard();
    }
    private void configDataBoard(){
        listColumn.itemsProperty().bind(viewModel.columnProperty());
        System.out.println(listColumn.itemsProperty().toString()+" test recup Vboard");//test recup
    }

    private void updateLvcColon(){
        listColumn.setCellFactory(view -> new ListCell<>(){
            @Override
            protected void updateItem(Column col, boolean b){
                super.updateItem(col, b);
                ViewColumn  viewColum = null;
                if(col != null){
                    try {
                        viewColum = new ViewColumn(col);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setGraphic(viewColum);
            }
        });
    }

    private void configaction(){
        listColumn.setOnMouseClicked (e ->{
            viewModel.addColumn();
        } );
    }
}
