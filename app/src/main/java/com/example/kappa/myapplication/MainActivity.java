package com.example.kappa.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but1 = (Button)findViewById(R.id.button1);

        but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentsta = new Intent();
                intentsta.setClass(MainActivity.this,StaticActivity.class);
                startActivity(intentsta);
            }
        });


        Button but2 = (Button)findViewById(R.id.button2);

        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentdy = new Intent();
                intentdy.setClass(MainActivity.this,DynamicActivity.class);
                startActivity(intentdy);
            }
        });


    }
}
