package com.example.carone_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Controller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        ImageButton home  = findViewById(R.id.btnHome); //Object for home button

        //==================== Home Button Action Listener ==========================

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();                            //Closes controller page and moves back to the home screen
            }
        });

        ImageButton settings = findViewById(R.id.btnSettings);

        //====================== Settings Button Action Listener=====================

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Currently Under Construction", Toast.LENGTH_SHORT);  //Sends message that page not ready
                toast.show();
            }
        });

        ImageButton control = findViewById(R.id.btnControl);

        //====================== Controller Button Action Listener===================

        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Controller page already selected", Toast.LENGTH_SHORT);  //Sends message that controller is selected
                toast.show();
            }
        });

        ImageButton horn = findViewById(R.id.btnHorn);

        //====================== Horn Button Action Listener===================

        horn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Beep Beep", Toast.LENGTH_SHORT);  //Sends message for Horn
                toast.show();
            }
        });

        ImageButton power = findViewById(R.id.btnPower);

        //====================== Power Button Action Listener===================

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"It's over 9000!!!", Toast.LENGTH_SHORT);  //Sends message for Power
                toast.show();
            }
        });


        ImageButton lights = findViewById(R.id.btnLights);

        //====================== Light Button Action Listener===================

        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Sadly, Eskom took it away", Toast.LENGTH_SHORT);  //Sends message for Light
                toast.show();
            }
        });

        ImageButton indicatorleft = findViewById(R.id.btnIndLeft);

        //====================== Left Indicator Button Action Listener===================

        indicatorleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Indicate Left", Toast.LENGTH_SHORT);  //Sends message for Left Indicator
                toast.show();
            }
        });

        ImageButton indicatorright = findViewById(R.id.btnIndRight);

        //====================== Right Indicator Button Action Listener===================

        indicatorright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Indicate Right", Toast.LENGTH_SHORT);  //Sends message for Right Indicator
                toast.show();
            }
        });

        TextView speed = findViewById(R.id.txtSpeed);   //Text View Objects to work with

        TextView battPercent = findViewById(R.id.txtBattPercent);

        TextView Range = findViewById(R.id.txtRange);

        TextView Connection = findViewById(R.id.txtConn);

        ImageView connect = findViewById(R.id.imgConn);


    }
}