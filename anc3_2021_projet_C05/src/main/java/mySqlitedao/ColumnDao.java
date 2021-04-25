package mySqlitedao;

import DAO.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ColumnDao  extends Dao<Column> {
    @Override
    public ObservableList<Column> findAll(int BoardId) throws SQLException {
        ObservableList<Column> myList =  FXCollections.observableList(new ArrayList());
        String sql = "SELECT * FROM colonnes where BoardId = " + BoardId ;
        Statement statement = connection.createStatement ();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            int IdBoard = result.getInt("BoardId");
            myList.add (new Column (id, name, IdBoard));
        }
        return myList;
    }

    @Override
    public Column find(int id)  {
        Column column = null;
        try {
            String sql = "select * from colonnes where id = "+ id + ";" ;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int ids = result.getInt("id");
            String name = result.getString("name");
            int boardId = result.getInt("boardId");
            column = new Column (ids, name,boardId);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return column;
    }

    @Override
    public Column create(Column obj) {
        try {
            String sql = "INSERT INTO colonnes(name , BoardId) VALUES(? , ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2,obj.getBoardId ());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Column update(Column obj) {
        try {
            String sql = "UPDATE colonnes SET name = ?, BoardId = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getBoardId ());
            preparedStatement.setInt(3, obj.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Column obj) {
        try {
            String sql = "DELETE FROM colonnes WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
