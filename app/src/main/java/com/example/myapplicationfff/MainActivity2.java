package com.example.myapplicationfff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    Button button ;
    int p;
    int player;
    int ai;
    int c;
    ImageView image;
    ImageView image2;
    Integer[]images= {R.drawable.rock,R.drawable.paper,R.drawable.cisor};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Database db=new Database(this);
        Random random= new Random();
        ArrayList<String> images=new ArrayList<>();
        //images=[];
        player=ai=0;


        Intent intent= getIntent();
        String text= intent.getStringExtra(MainActivity.EXTRA_TEXT);
        TextView textView=(TextView)findViewById(R.id.textView2);
        TextView textView2=(TextView)findViewById(R.id.textView);
        textView.setText(text);
        int win=db.getWinCount(text);
        int loss=db.getLossCount(text);
        textView.setText(text);

        textView.setText(text+" Your total wins and losses are:"+Integer.toString(win)+" Wins:::"+Integer.toString(loss)+"losses");


        configuringBack();
    }



    private void configuringBack(){
        Button playButton=(Button) findViewById(R.id.previous);
        image=(ImageView) findViewById(R.id.imageView);

         image2=(ImageView) findViewById(R.id.imageView2);



        Random random= new Random();
       // File[] images={new  File("src/main/res/drawable-v24/cisor.png"),new File("src/main/res/drawable-v24/paper.png"),new File("src/main/res/drawable-v24/rock.png")};
       // Glide.with(MainActivity2.this).load("https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fview&psig=AOvVaw2jNKJU_DmeoU6exK3Jpxm4&ust=1652612376272000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJiO0f7q3vcCFQAAAAAdAAAAABAD").centerCrop().into(image);
       // Bitmap myBitmap = BitmapFactory.decodeFile(images[1].getAbsolutePath());
        //
        // image.setImageBitmap(myBitmap);
        //InputStream is = getClass().getResourceAsStream("src/main/res/drawable/" + "paper.png");
       // image.setImageDrawable(Drawable.createFromStream(is, ""));
       Database db= new Database(this);
        ArrayList<String>list=new ArrayList<>();
        List<Score> scoresList=db.getAllScores(); for (Score score:scoresList){

            String log ="Player name "+score.getName()+" ,Player score:"+score.getMe()+" :: ,AI score:"+score.getAi();

            list.add(log);
        }
        
        Intent intent= getIntent();
        String text= intent.getStringExtra(MainActivity.EXTRA_TEXT);
        //startActivity(new Intent(MainActivity2.this,MainActivity.class));





        TextView textView2=(TextView)findViewById(R.id.textView);
        textView2.setText(Integer.toString(player)+"xxxxx"+Integer.toString(ai));



        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                p=random.nextInt(3);
                c=random.nextInt(3);
                image2.setBackgroundResource(images[c]);
                image.setBackgroundResource(images[p]);


                if (c==0 && p==2 || c==1 && p==0 ||c==2 && p==1) {
                    ai++;

                }
                else if (p==0 && c==2 || p==1 && c==0 ||p==2 && c==1){
                    player++;

                }
                textView2.setText(Integer.toString(player)+"xxxxx"+Integer.toString(ai));

                if (player>2 ||ai>2){
                    db.addScore(new Score(text,player,ai));
                    player=ai=0;

                }












            }
        });
    }
}