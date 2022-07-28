package com.example.myapplicationfff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
     public static  final  String EXTRA_TEXT="com.example.myapplicationfff.EXTRA_TEXT";

     TextView textView;
     EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configuringNext();
        scorePage();

        Database db = new Database(this);

        //Lire tous les étudiants
        Log.d("Lecture: ", "Lire tous les étudiants..");
        List<Player> players = db.getAllPlayers();
       List<Score> scoresl=db.getPlayerScores("saad");
        List<Score> scores=db.getAllScores();


      /*
        for (Player ED : players) {
            String log = "Id: " + ED.getID() + " ,Name: " + ED.getName() + " ,ADDRESS: " +
                    ED.getScore();
            // Ecrire tous les étuiants dans le log
            Log.d("Name: ", log);
        }

        for (Score score:scoresl){

            String log ="Player name "+score.getName()+" ,Player score:"+score.getMe()+" :: ,AI score:"+score.getAi();

            Log.d("Name: ", log);
        }

*/
    }
    private void configuringNext(){
        Button nextButton=(Button) findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent= new Intent(MainActivity.this,MainActivity2.class);

                textView=(TextView) findViewById(R.id.text) ;

                inputText=(EditText) findViewById(R.id.editText);
                String text=inputText.getText().toString();
                intent.putExtra(EXTRA_TEXT,text);
                startActivity(intent);
                 //textView.setText("hi"+inputText.getText());

            }
        });
    }

    private void scorePage(){
        Button scoreButton=(Button) findViewById(R.id.scoresPage);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);

            }
        });
    }

    public void updateText(View view){
        textView.setText("hi"+inputText.getText());
    }

}