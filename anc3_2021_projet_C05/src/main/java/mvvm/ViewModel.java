package mvvm;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import view.ViewCard;
import view.ViewColumn;

public class ViewModel {

    private final Board board;

    private final SimpleListProperty columns = new SimpleListProperty<>();
    private final SimpleListProperty cards = new SimpleListProperty<>();

    private final IntegerProperty numLineSelectedColumn = new SimpleIntegerProperty(-1);
    private final IntegerProperty numLineSelectedCard = new SimpleIntegerProperty(-1);

    private final StringProperty bordName = new SimpleStringProperty();

    private final BooleanProperty imleftColumDisabled = new SimpleBooleanProperty(true),
            ImrightColumDisabled = new SimpleBooleanProperty(true);

    public ViewModel(Board board){
        this.board = board;
       // configColumSelection();
        configData();

    }

    private void configData() {
        columns.setValue(board.getColumns());
        bordName.setValue(board.getName());
    }

    public SimpleListProperty<Card> getCardsByColumnProperty() {
        return new SimpleListProperty<Card>(this.board.getColumn(numLineSelectedColumn.intValue()).getCards());
    }

    public SimpleListProperty<Column> getColumnsProperty() {
        return columns;
    }

    public IntegerProperty getNumLineSelectedColumnProperty() {
        return numLineSelectedColumn;
    }

    public IntegerProperty getNumLineSelectedCardProperty() {
        return numLineSelectedCard;
    }

    public void lineSelectedColumn(ReadOnlyIntegerProperty index) {
        numLineSelectedColumn.bind(index);
    }

    public void lineSelectedCard(ReadOnlyIntegerProperty index) {
        numLineSelectedCard.bind(index);
    }

    public StringProperty getBordNameProperty() {
        return bordName;
    }

    public Column getColumn(int index) {
        return index == -1  ? null : board.getColumn(index);
    }

    public SimpleListProperty<Card> getlsCardsByColumnProperty(Column column) {
        return new SimpleListProperty<Card>(column.getCards());
    }

    public void swapCardDown(Card card, Column column, ViewColumn viewColumn) throws Exception{
        board.swapCardDown(card, column.getPosition());
         getLsViewCard(column, viewColumn);
    }

    public SimpleListProperty<ViewCard> getLsViewCard(Column column, ViewColumn viewColumn) throws Exception {
        ObservableList<ViewCard> lscards = FXCollections.observableArrayList();
        for (int i = 0; i < column.getCards().size(); ++i) {
            lscards.add(new ViewCard(this, column.getCards().get(i), column, viewColumn));
        }

        return new SimpleListProperty<>(lscards);
    }

    public SimpleListProperty<ViewColumn> getLsViewColum() throws Exception {
        ObservableList<ViewColumn> lscolumns = FXCollections.observableArrayList();
        for (int i = 0; i < getColumnsProperty().size(); ++i) {
            lscolumns.add(new ViewColumn(this, getColumn(i)));
        }
        configActionnableButtons();
        return new SimpleListProperty<>(lscolumns);
    }
    // column

    public void addColumn()  {
        Column c = getColumn(numLineSelectedColumn.get ());
        if (c == null ){
            board.addColumn(new Column ("Column ",board.getColumns ().size ()+1));
        }else {

            // board.addColumn(new Column ("Column "+listColumn.size ()));
        }
        configData();
    }
    public void swapColleft(Column column) throws Exception{
        System.out.println (numLineSelectedColumn.get ());
        board.swapColleft (column.getPosition());
        getLsViewColum();
    }
    public void swapColright(Column column) throws Exception{
        board.swapColright (column.getPosition());
        getLsViewColum();
    }
    private void configColumSelection() {
        numLineSelectedColumn.addListener((obs, oldval, newval) ->
                configActionnableButtons());
    }
    private void configActionnableButtons() {
        imleftColumDisabled.setValue ( numLineSelectedColumn.get ()==-1);
    }

    public BooleanProperty imleftColumDisabledProperty() {
        return imleftColumDisabled;
    }
    // card

    public  void addCard(Column c){
        Card car = c.getCard (numLineSelectedCard.get ());
        if (car == null ){
            c.addCard(new Card ("Card ",c.getCards ().size ()+1));
        }else {

        }
        configData();
    }
}
