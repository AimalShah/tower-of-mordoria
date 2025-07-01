package io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAL {
    public DAL(){
    createTableIfNotExists();
    };

    ArrayList<User> users = new ArrayList<>();

    private void createTableIfNotExists(){
        String query = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY, username TEXT, avatar TEXT, high_score INTEGER)";

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute(query);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addUser(User user){
        String query = "INSERT INTO user (username , avatar , high_score) VALUES ('" + user.getUsername() + "', '" + user.getAvatar() + " '," + user.getHighScore() +")";
        try(Connection conn = DBConnection.getConnection()){
            if(conn != null){
                Statement stmt = conn.createStatement();
                stmt.execute(query);
                System.out.println("User Added");
            }
        } catch (SQLException e){
            System.out.println("Error Adding User : " + e);
        }
    }

    public ArrayList<User> getAllUsers(){
        String query = "SELECT * FROM user";
        try(Connection conn = DBConnection.getConnection();
        ) {
            assert conn != null;
            try(Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
            ) {
                while (rs.next()){
                    User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("avatar"),
                        rs.getInt("high_score")
                    );
                    users.add(user);
                }

                return users;

            }
        } catch(SQLException e){
            System.out.println("EROOR : " + e);
        }

        return null;
    }


    public void updateHighScore(int id , int score){
        String query = "UPDATE user SET high_score =" + score +" WHERE id = " + id;
        try(Connection conn = DBConnection.getConnection()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(query);
            System.out.println(rows > 0 ? "User Score Updated" : "User Not Found" );
        } catch (SQLException e){
            System.out.println("ERROR : " + e);
        }
    };


}
