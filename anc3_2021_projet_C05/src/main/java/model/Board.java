package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mySqlitedao.BoardDao;
import mySqlitedao.CardDao;
import mySqlitedao.ColumnDao;

import java.sql.SQLException;
import java.util.*;

public class Board {

    private  int id;
    private String name ;
    private final ObservableList<Column> columns = FXCollections.observableList( new ArrayList<>());

    public Board(String  name) {
        this.name = name;
        initData();
    }

    public Board(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**************************************************  configure board **********************************************************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public final String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Column> getColumns() {
        return columns;
    }

    /**************************************************  configure column **********************************************************/

    public boolean addColumn (Column col) {
        return columns.add(col);
    }

    public boolean removeColumn (Column col) {
        return columns.remove(col);
    }

    public Column getColumn(int index) {
        return   index == -1 ? null :this.getColumns().get(index);
    }

    public int getposition() {
        return  this.columns.indexOf (this);
    }

    public void swapColumnRight(int index) {
        Collections.swap (columns, index, index + 1);
    }

    public void swapColumnLeft(int index) {
        Collections.swap (columns, index, index - 1);
    }

    /**************************************************  init data *******************************************************************************/
    private void initData() {
        BoardDao boardDao = new BoardDao ();
        Board board = boardDao.find (1);
        ColumnDao columnDao = new ColumnDao ();
            columnDao.create (new Column(-1,"pipipipi",board.getId ()));
            columnDao.create (new Column(-1,"pupupupu",board.getId ()));

        try {

            columns.addAll (columnDao.findAll (board.getId ()));
            System.out.println (columns+"  board configdata");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

        Column à_faire = new Column("à faire",this),
                à_tester = new Column("à tester",this),
                en_cours = new Column("en cours",this),
                en_attente_de_paiement = new Column("en attente",this);
        Card Carte_1 = new Card("Carte 1",à_faire),
                Carte_2 = new Card("Carte 2",à_tester),
                Carte_3 = new Card("Carte 3",à_tester),
                Carte_4 = new Card("Carte 4",en_cours),
                Carte_5 = new Card("Carte 5",en_cours),
                Carte_6 = new Card("Carte 6",en_cours);

        columns.add(à_faire);
        columns.add(à_tester);
        columns.add(en_cours);
        columns.add(en_attente_de_paiement);
        try {
            columns.addAll (columnDao.findAll (1));
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        CardDao cardDao = new CardDao ();

        for (Column col :columns ) {
            cardDao.create (new Card( "Carte 1",col.getId ()));
            try {
                for ( Card card : cardDao.findAll (col.getId ()) ) {
                    if (col.getId () == card.getColumnId ());
                    col.addCard (card);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }

        à_tester.addCard(Carte_2);
        à_tester.addCard(Carte_3);

        en_cours.addCard(Carte_4);
        en_cours.addCard(Carte_5);
        en_cours.addCard(Carte_6);

    }
}
