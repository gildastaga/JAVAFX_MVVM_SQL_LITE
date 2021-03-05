package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;
import model.Column;
import mvvm.BoardViewModel;
import mvvm.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class ViewBoard extends ListView<Column> {

    private   BoardViewModel boardViewModel;
    public List<ViewColumn> listViewColumn= new ArrayList ();
    private final IntegerProperty numLineSelectedColumn = new SimpleIntegerProperty(-1);



    ViewBoard(BoardViewModel boardViewModel)  {
        this.boardViewModel= boardViewModel;
        this.setOrientation(Orientation.HORIZONTAL);
        configBoard();
        updateLvcColon();
    }

    private void configBoard() {
        configDataBoard();
        configactionAddCol();
    }

    public void configDataBoard()  {
        numLineSelectedColumn.bind(boardViewModel.getNumLineSelectedColumnProperty());
        this.itemsProperty().bind(boardViewModel.getColumnsProperty());
    }

    private void updateLvcColon(){
        this.setCellFactory(view -> new ListCell<>(){
            @Override
            protected void updateItem(Column col, boolean b){
                super.updateItem(col, b);
                ViewColumn viewColumn = null;
                if(col != null){
                    viewColumn = new ViewColumn(col);
                }
                setGraphic(viewColumn);
            }
        });
    }

    private void configactionAddCol() {
        this.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 ) {
                boardViewModel.addColumn();
            }
        });
    }
}
