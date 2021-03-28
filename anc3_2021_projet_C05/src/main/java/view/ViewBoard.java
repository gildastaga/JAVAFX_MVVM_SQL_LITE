package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
        configDataBoard();
        configactionAddCol();
    }

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
                    viewColumn = new ViewColumn(col);
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
    }
}
