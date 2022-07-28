package com.example.myapplicationfff;

public class Player {
    int id;
    String name;
    
    String score;

    public Player(int idE, String nameE, String adressE){
        this.id = idE;
        this.name = nameE;
        this.score =adressE;
    }

    public Player(String nameE, String adressE){
        this.name = nameE;
        this.score =adressE;
    }
    public Player() {

    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getScore(){
        return this.score;
    }

    public void setScore(String adress){
        this.score = adress;
    }
}
