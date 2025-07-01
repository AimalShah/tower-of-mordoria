package io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection;

import java.sql.*;

public class DBConnection {
   public static  final  String url = "jdbc:sqlite:users.db";

   public static Connection getConnection(){
       try {
           Connection conn = DriverManager.getConnection(url);
        if(conn != null){
            System.out.println("Database Connection Established");

            return  conn;
        }
       } catch (Exception e){
           System.out.println("Database Connection Error :" + e);
       }

       return null;
   }
}
