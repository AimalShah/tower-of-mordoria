package io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection;

public class User {
    private int id;
    private String username;
    private  String avatar;
    private int highScore;

    public User(int id, String username, String avatar, int highScore){
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.highScore = highScore;
    }

    public User(String username, String avatar , int highScore){

        this.username = username;
        this.avatar = avatar;
        this.highScore = highScore;
    }

    public User(String username, String avatar){
        this.username = username;
        this.avatar = avatar;
    }

    public User(String username){
        this.username = username;
        this.avatar = "avatar1";
    }


    public int getId(){return this.id;}

    public String getUsername(){return this.username;}

    public  String getAvatar(){return  this.avatar;}

    public int getHighScore(){return this.highScore;}

    public void setId(int id){this.id = id;}
    public  void setUsername(String username){this.username = username;}
    public  void setAvatar(String avatar){this.avatar = avatar;}
    public  void  setHighScore(int score){this.highScore = score;}


}
