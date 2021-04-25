package mySqlitedao;

import DAO.Dao;
import model.Board;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BoardDao extends Dao<Board> {
    @Override
    public List<Board> findAll(int id) throws SQLException {
        return null;
    }

    @Override
    public Board find(int id) throws SQLException {
        Board board = null;
        try {
            String sql = "select * from boards where id = "+ id + ";" ;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            System.out.println (result);
            id = result.getInt("id");
            String name = result.getString("name");
            board = new Board (id, name);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public Board create(Board obj) throws SQLException {
        try {
            String sql = "INSERT INTO boards(name) VALUES(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Board update(Board obj) {
        try {
            String sql = "UPDATE boards SET name = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Board obj) {
        try {
            String sql = "DELETE FROM boards WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
