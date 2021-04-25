package mySqlitedao;

import DAO.Dao;
import model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends Dao<Card> {

    @Override
    public List findAll(int columnId) throws SQLException {
        ArrayList<Card> myList = new ArrayList();
        String sql = "SELECT * FROM cards where columnid = " + columnId ;
        Statement statement = connection.createStatement ();
        ResultSet result = statement.executeQuery(sql); // Obtenir le résultat du SELECT
        while (result.next()) { // Tant qu'il y a encore une ligne dans le résultat
            int id = result.getInt("id"); // Obtenir l'id
            String name = result.getString("name"); // Obtenir le nom
            int idcol = result.getInt("columnId"); // Obtenir l'id
            myList.add (new Card (id, name, idcol));
        }
        return myList;
    }

    @Override
    public Card find(int id) throws SQLException {
        Card card = null;
        try {
            String sql = "select * from cards where id = "+ id + ";" ;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            id = result.getInt("id");
            int idColumn = result.getInt("ColumnId");
            String name = result.getString("name");
            card = new Card(id, name, idColumn);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return card;
    }

    @Override
    public Card create(Card obj)  {
        try {
            String sql = "INSERT INTO cards(name,columnId) VALUES(?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2,obj.getColumnId ());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Card update(Card obj) {
        try {
            String sql = "UPDATE cards SET name = ?, columnId = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getColumnId ());
            preparedStatement.setInt(3, obj.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Card obj) {
        try {
            String sql = "DELETE FROM cards WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
