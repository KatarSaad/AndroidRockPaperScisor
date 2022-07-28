package com.example.myapplicationfff;

public class Score {
    int id;
    String name;
    public int me;
    public int ai;


    public Score(String name,int me,int ai) {


        this.me =me;
        this.name = name;
        this.ai = ai;
    }

    public Score(String nameE, int me)  {
        this.name = nameE;
        this.me = me;
    }

    public Score() {

    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAi() {
        return ai;
    }

    public int getMe() {
        return me;
    }

    public void setMe(int me) {
        this.me = me;
    }

    public void setAi(int ai) {
        this.ai = ai;
    }

    public void setID(int parseInt) {
      this.id=parseInt;
    }

}