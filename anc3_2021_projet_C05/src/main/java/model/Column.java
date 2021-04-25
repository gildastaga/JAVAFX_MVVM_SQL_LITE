package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mySqlitedao.BoardDao;
import mySqlitedao.CardDao;

import java.util.*;

public class Column {

    private String name;
    private  ObservableList<Card> lsCards;
    private  Board board;
    private  int id;
    private  int boardId;
    BoardDao boardDao = new BoardDao ();
    CardDao cardDao =  new CardDao ();

    public Column(String name,Board board) {
        this.name = name;
        this.board = board;
        lsCards = FXCollections.observableList(new ArrayList<>());
    }

    public Column(int id, String name, int boardId) {
        this.id = id ;
        this.name = name;
        this.boardId = boardId;
        this.board = boardDao.find (boardId);
        lsCards = FXCollections.observableList(new ArrayList<>());
    }

   /* public Column(String name, int id) {
        this.name = name;
        this.boardId = id ;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    /**************************************************  configure column **********************************************************/

    public final String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public int getposition(){
        return  this.getBoard ().getColumns ().indexOf (this);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**************************************************  configure card **********************************************************/

    public boolean addCard(Card card) {
        return  lsCards.add(card);
    }

    public boolean removeCard(Card card) {
        return lsCards.remove(card);
    }

    public ObservableList<Card> getCards() {
        return lsCards;
    }

    /**************************************************  configure swapCard  **********************************************************/

    public void swapCardDown(int index) {
        Collections.swap (lsCards,index,(index+1));
    }

    public void swapCardUp(int index) {
        Collections.swap (lsCards,(index-1),index);
    }

    public void swapCardRight(Column column , Card card) {
        Column that = this.board.getColumn( (column.getposition ()+ 1));
        that.addCard(card);
        column.removeCard(card);
        card.setColumn (that);
    }

    public void swapCardLeft(Column column, Card card ) {
        Column that = this.board.getColumn ((column.getposition () - 1));
        that.addCard(card);
        column.removeCard(card);
        card.setColumn (that);
    }

}