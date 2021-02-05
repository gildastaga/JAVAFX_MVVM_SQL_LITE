package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Colonnes;
import mvvm.ColoneViewModel;
import mvvm.ViewModel;
import view.ViewBoard;

public class View extends VBox {

    private  final ViewModel viewModel;
    private ListView<Colonnes> listColumn = new ListView<>();
    private final VBox zone = new VBox();
    private final ViewBoard viewboard;
    private int width = 1025;
    private int heigth = 725;
    public View(Stage primaryStage, ViewModel viewModel) throws Exception {
        this.viewModel=viewModel;
        this.viewboard = new ViewBoard(primaryStage, viewModel);
        configVboxZone();
        Scene scene = new Scene(this,width,heigth);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trello");
    }

    private void configVboxZone()  {
        zone.getChildren().add(viewboard);
         this.getChildren().add(zone);
    }
    private void configBoard(){
        //configDataBoard();
    }
    private void configDataBoard(){
        //listColumn.itemsProperty().bind(viewModel.columnProperty());
        //System.out.println(listColumn.itemsProperty().toString());//test recup
    }
    /*private void compornentsDecoration (){
        this.setSpacing(SPACING);
        this.setSpacing(SPACING);
        this.setPadding(new Insets(SPACING));
        listColumn.setOrientation(Orientation.HORIZONTAL);

    }*/

    /*private void updateLvcColon(){
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
    }*/

    /*private void configActions() {
        tfBoardName.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() == 2 ){
                viewModel.openSelectedFileRight();
            }
        });
    }*/
}
