package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoFactory {
    private static String urldb = "jdbc:sqlite:test.db";

//    private static DaoFactory uniqueInstance = new DaoFactory ();
//
//    public static DaoFactory getInstance() {
//        return uniqueInstance;
//    }

    private static void configDB(Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();
        String sql;

        // Activation of checks FK
        sql = "PRAGMA foreign_keys = ON;";
        stmt.execute(sql);

    }

    private static void createTables(Connection conn) throws SQLException {

        String sql;
        Statement stmt = conn.createStatement();

        // SQL statement for boards table
        sql = "CREATE TABLE IF NOT EXISTS boards ("
                + "	id integer PRIMARY KEY  AUTOINCREMENT,"
                + "	name text NOT NULL);";
        stmt.execute(sql);

        // SQL statement for colonnes table
        sql = "CREATE TABLE IF NOT EXISTS colonnes ("
                + "	id integer PRIMARY KEY  AUTOINCREMENT,"
                + "	name text NOT NULL,"
                + "	boardId integer NOT NULL,"
                + " CONSTRAINT fk_colonnes FOREIGN KEY (boardId) "
                + " REFERENCES boards(id));";
        stmt.execute(sql);

        // SQL statement for boards table
        sql = "CREATE TABLE IF NOT EXISTS cards ("
                + "	id integer PRIMARY KEY  AUTOINCREMENT,"
                + "	name text NOT NULL,"
                + "	columnId integer NOT NULL,"
                + " CONSTRAINT fk_cards FOREIGN KEY (columnId) "
                + " REFERENCES colonnes(id));";
        stmt.execute(sql);

    }

    private static void clearDB(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        String sql;

        sql = "DELETE FROM cards;";
        statement.execute(sql);

        sql = "DELETE FROM colonnes;";
        statement.execute(sql);

        sql = "DELETE FROM boards;";
        statement.execute(sql);
    }

    public void main (){
        try {
            Connection connection = DriverManager.getConnection (urldb);
           // clearDB (connection);
            configDB (connection);
            createTables (connection);
        }catch (SQLException e){
            e.printStackTrace ();
        }
    }


//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(urldb);//, username, password
//    }
//    public Carddao getDAOCard() {
//        try {
//            Constructor[] constr = Carddao.class.getConstructors();
//            return (Carddao) constr[0].newInstance(this);
//        } catch (Exception e) {
//            throw new RuntimeException ( e.getMessage ());
//        }
//    }
//    public Columndao getDAOColumn() {
//        try {
//            Constructor[] constr = Columndaomysqlite.class.getConstructors();
//            return (Columndao) constr[0].newInstance(this);
//        } catch (Exception e) {
//            throw new RuntimeException ( e.getMessage ());
//        }
//    }
//    public Boarddao getDAOBoard() {
//        try {
//            Constructor[] constr = Boarddaomysqlite.class.getConstructors();
//            return (Boarddao) constr[0].newInstance(this);
//        } catch (Exception e) {
//            throw new RuntimeException ( e.getMessage ());
//        }
//    }


}
