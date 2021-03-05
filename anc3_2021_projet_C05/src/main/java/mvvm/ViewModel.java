package mvvm;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import view.ViewBoard;
import view.ViewCard;
import view.ViewColumn;

public class ViewModel {

    private final Board board;

    private final SimpleListProperty columns = new SimpleListProperty<>();
    private final SimpleListProperty cards = new SimpleListProperty<>();

    private final IntegerProperty numLineSelectedColumn = new SimpleIntegerProperty(-1);
    private final IntegerProperty numLineSelectedCard = new SimpleIntegerProperty(-1);

    private final StringProperty bordName = new SimpleStringProperty();

    private BooleanProperty imleftColumDisabled= new SimpleBooleanProperty(false),
            imRightColumDisabled = new SimpleBooleanProperty(false),
            newNameColumnDisabled= new SimpleBooleanProperty (true);

    public ViewModel(Board board){
        this.board = board;
       // configColumnSelection();
        //configData();
        //configActionnableImages ();
    }

    public void configData() {
       // columns.setValue(board.getColumns());
      //  bordName.setValue(board.getName());
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


    public void lineSelectedColumn(ReadOnlyIntegerProperty index) {
        numLineSelectedColumn.bind(index);
    }
    public IntegerProperty getNumLineSelectedCardProperty() {
        return numLineSelectedCard;
    }

    //public void lineSelectedCard(ReadOnlyIntegerProperty index) {
     //   numLineSelectedCard.bind(index);
  //  }

    public StringProperty getBordNameProperty() {
        return bordName;
    }

    private Column getColumn(int index) {
        return index == -1  ? null : board.getColumn(index);
    }

    public SimpleListProperty<Card> getlsCardsByColumnProperty(Column column) {
        return new SimpleListProperty<Card>(column.getCards());
    }

    /**************************************************  configure card **********************************************************/

    public void swapCardDown(Column column) {
        board.swapCardDown(numLineSelectedCard.intValue(), column.getPosition());
    }

    public void swapCardUp(Column column) {
        board.swapCardUp(numLineSelectedCard.intValue(), column.getPosition());
    }

    public void swapCardRight(Column column) {
        board.swapCardRight(numLineSelectedCard.intValue(), column.getPosition());
    }

    public void swapCardLeft(Column column) {
        board.swapCardLeft(numLineSelectedCard.intValue(), column.getPosition());
    }

   /* public SimpleListProperty<ViewCard> getLsViewCard(Column column, ViewColumn viewColumn, ViewBoard viewBoard) throws Exception {
        ObservableList<ViewCard> lscards = FXCollections.observableArrayList();
        for (int i = 0; i < column.getCards().size(); ++i) {
         //   lscards.add(new ViewCard(this, column.getCards().get(i), column, viewColumn, viewBoard));
        }

        return new SimpleListProperty<>(lscards);
    }*/

    /**************************************************  configure column **********************************************************/

    public void swapColleft(Column column) throws Exception {
        board.swapColumnLeft (numLineSelectedColumn.intValue());
    }

    public void swapColright() throws Exception {
        board.swapColumnRight (numLineSelectedColumn.intValue());
    }

    /*public SimpleListProperty<ViewColumn> getLsViewColum(ViewBoard viewBoard) throws Exception {
        ObservableList<ViewColumn> lscolumns = FXCollections.observableArrayList();
        for (int i = 0; i < getColumnsProperty().size(); ++i) {
            lscolumns.add(new ViewColumn(this, getColumn(i), viewBoard));
        }
        imRightColumDisabledProperty ();
        return new SimpleListProperty<>(lscolumns);
    }*/

    public void addColumn() {
        Column c = getColumn(numLineSelectedColumn.get ());
        if (c == null ){
            board.addColumn(new Column ("Column "+board.getColumns ().size (),board.getColumns ().size ()+1,board));
        } else {

            //TODO
         }
        configData();
    }

    public  void addCard( Column c) {
      //  c.addCard(new Card ("Card "+c.getCards ().size ()+1,c.getCards ().size ()));
     /*   if (card == null ){

        }else {
            c.addCard(card);
        }*/
        configData();
    }

    public void deleteCard(Card card, Column column) {
        column.removeCard (card);
    }

    public void deleteColumn(Column column) {
        board.removeColumn (column);
    }

    /////////////////////////////////configdisable////////////////////
    private void configColumnSelection() {
        numLineSelectedColumn.addListener((obs, oldval, newval) ->
               configActionnableImages ());
    }
    private void configActionnableImages() {
        imleftColumDisabled.setValue(board.getposition ()==0);
       // imRightColumDisabled.setValue( board.getColumn (numLineSelectedColumn.getValue ()).getPosition ()>=(board.getColumns ().size ()-1));

    }

    public BooleanProperty imleftColumDisabledProperty() {
        return imleftColumDisabled;
    }
    public  BooleanProperty imRightColumDisabledProperty(){
        return imRightColumDisabled;
    }

    public BooleanProperty newNameColumnDisabledProperty() {
        return newNameColumnDisabled;
    }
}
