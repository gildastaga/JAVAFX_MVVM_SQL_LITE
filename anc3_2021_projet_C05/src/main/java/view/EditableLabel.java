package view;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Board;
import model.Type;
import mvvm.ViewModelBoard;
import mvvm.ViewModelCard;
import mvvm.ViewModelColumn;


public class EditableLabel extends Label {

    private TextField textfield = new TextField();
    private  Boolean isEditable = false;
    private String title;

    private ViewModelBoard viewModelBoard;
    private ViewModelColumn viewModelColumn;
    private ViewModelCard viewModelCard;

    public EditableLabel(String title, ViewModelBoard viewModelBoard, ViewModelColumn viewModelColumn, ViewModelCard viewModelCard ) {
        super(title);
        this.viewModelBoard = viewModelBoard;
        this.viewModelColumn = viewModelColumn;
        this.viewModelCard = viewModelCard;
        configAction();
    }

    public void toLabel(){
        this.setGraphic(null);
        if(viewModelBoard != null){
            viewModelBoard.updateBordName(textfield.getText());
        }else if(viewModelColumn != null){
            viewModelColumn.updateColumnName(textfield.getText());
        }else if(viewModelCard != null){
            viewModelCard.updateCardName(textfield.getText());
        }

        this.setText(textfield.getText());
    }

    public void configAction(){
        this.setOnMouseClicked(e -> {
            if(e.getClickCount() == 1){
                textfield.setText(this.getText());
                this.setGraphic(textfield);
                textfield.requestFocus();
            }
        });
        textfield.setOnKeyReleased(e -> {
            if(e.getCode().equals(KeyCode.ENTER)){
                toLabel();
            }
        });
    }
}
