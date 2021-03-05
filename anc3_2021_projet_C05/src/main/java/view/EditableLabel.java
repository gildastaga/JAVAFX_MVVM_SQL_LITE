package view;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EditableLabel extends VBox {

    private final Label name = new Label ();
    private  Boolean isEditable = false;
    private static  Color openLabelColor = Color.GREEN;
    private static Color closeLabelColor = Color.RED;

    public EditableLabel(String titre , Boolean isEditable){
        this.name.textProperty ().bind (new SimpleStringProperty (titre));
        this.isEditable= isEditable;
        this.name.textFillProperty ().bind (Bindings.when (
                new SimpleBooleanProperty (isEditable)).then (openLabelColor).otherwise (closeLabelColor));
    }

    public Label getName() {
        return name;
    }

    public Boolean getEditable() {
        return isEditable;
    }
}
