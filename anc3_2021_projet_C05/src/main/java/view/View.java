package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Colonnes;
import mvvm.ColoneViewModel;
import mvvm.ViewModel;
import view.ViewBoard;

public class View extends VBox {

    private  final ViewModel viewModel;
    private static final double SPACING = 10;
    private final VBox vprincipal = new VBox(),option = new VBox(), zone =new VBox() ;
    private TextField tfBoardName = new TextField();
    private String name ="tableau";
    private ListView<Colonnes> listColumn = new ListView<>();
    private int weight = 1025;
    private int heigth = 725;
    public View(Stage primaryStage, ViewModel viewModel){
        this.viewModel=viewModel;
        configVboxZone();
        updateLvcColon();
        configBoard();
        Scene scene = new Scene(vprincipal,weight,heigth);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trello");
    }

    private void configVboxZone()  {
        vprincipal.getChildren().addAll(option,zone);
        option.getChildren().add(tfBoardName);
        tfBoardName.setText(name);
        zone.getChildren().add(listColumn);
        //listColumn.itemsProperty().bind(getLsViewColum());
        compornentsDecoration();
    }
    private void configBoard(){
        configDataBoard();
    }
    private void configDataBoard(){
        listColumn.itemsProperty().bind(viewModel.columnProperty());
        System.out.println(listColumn.itemsProperty().toString());//test recup
    }
    private void compornentsDecoration (){
        this.setSpacing(SPACING);
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
        tfBoardName.setMaxWidth(300);
        listColumn.setOrientation(Orientation.HORIZONTAL);

    }

    private void updateLvcColon(){
        listColumn.setCellFactory(view -> new ListCell<>(){
            @Override
            protected void updateItem(Colonnes col, boolean b){
                super.updateItem(col, b);
               ViewColumn  viewColum = null;
                if(col != null){
                    try {
                        viewColum = new ViewColumn(col);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    viewColum.setMaxHeight(400);
                }
                setGraphic(viewColum);
            }
        });
    }
}
