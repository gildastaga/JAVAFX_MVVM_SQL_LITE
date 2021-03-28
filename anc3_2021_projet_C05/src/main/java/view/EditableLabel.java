package view;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Type;


public class EditableLabel extends VBox {

    private TextField textfield = new TextField();
    private  Boolean isEditable = false;
    private String title;

    public EditableLabel(String title, Boolean isEditable, Type type) {
        this.isEditable= isEditable;
        this.title = title;
        setTextField(isEditable, title, type);
    }

    public void setEditable(Boolean editable, Type type) {
        isEditable = editable;
        setTextField(isEditable, title, type);
    }

    public TextField getTextField() {
        return textfield;
    }

    public void setTextField(Boolean editable, String title, Type type) {
        this.title = title;
        this.textfield.textProperty().bindBidirectional(new SimpleStringProperty(title));
        this.textfield.editableProperty().bind(new SimpleBooleanProperty(editable));
        if (type == Type.BOARD)
            if (editable)
                this.textfield.setStyle("-fx-border-color: #f5f5f5;-fx-background-color: white; -fx-padding: 10px;");
            else
                this.textfield.setStyle("-fx-border-color: #f5f5f5;-fx-background-color: #f5f5f5; -fx-padding: 10px;");
        else if (type == Type.CARD)
            this.textfield.setStyle("-fx-border-color: skyblue;-fx-background-color: skyblue; -fx-padding: 10px;");
        else
            System.out.println("");

    }
}
