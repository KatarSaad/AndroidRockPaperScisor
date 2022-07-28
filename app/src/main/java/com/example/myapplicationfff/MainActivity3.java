package com.example.myapplicationfff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView=(ListView) findViewById(R.id.scores);
        ArrayList<String>list=new ArrayList<>();
        Database db= new Database(this);
        List<Score> scoresList=db.getAllScores(); for (Score score:scoresList){

            String log ="Player name "+score.getName()+" ,Player score:"+score.getMe()+" :: ,AI score:"+score.getAi();

            list.add(log);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);



    }
}