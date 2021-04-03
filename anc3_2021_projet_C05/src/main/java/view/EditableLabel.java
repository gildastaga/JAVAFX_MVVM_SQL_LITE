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


public class EditableLabel extends Label {

    private TextField textfield = new TextField();
    private  Boolean isEditable = false;
    private String title;

    public EditableLabel(String title) {
        super(title);
        configAction();
    }

    public void toLabel(){
        this.setGraphic(null);
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
