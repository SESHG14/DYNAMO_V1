package com.example.carone_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Controller extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        float throttleOrigin, turnOrigin;
        final float[] x = new float[1];
        final float[] y = new float[1];

        final float[] throttleMagnitude = new float[1];
        final float[] turnMagnitude = new float[1];

        final String[] throttleDirection = new String[1];
        final String[] TurnDirection = new String[1];
        FrameLayout leftStick = findViewById(R.id.flLeftStick);
        //throttleOrigin = leftStick.getY()/2 - leftStick.getHeight()/2;
        throttleOrigin = leftStick.getY() - leftStick.getHeight() + 310; //had to include an offset factor. Its a rough estimate but works fine.
        //==================== Left Stick (fwrd/bckwrd) on-touch Listener ==========================

        leftStick.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Log.i("LeftJS", "Touch detected");

                int eventType = motionEvent.getActionMasked();

                switch (eventType){

                    case MotionEvent.ACTION_DOWN:
                        Log.i("LeftJS", "Action Down");
                        break;
                    case MotionEvent.ACTION_UP:
                        throttleMagnitude[0] = 0;
                        Log.i("LeftJS", "Not Moving " + " == Magnitude: " + throttleMagnitude[0]);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        y[0] = motionEvent.getY();
                        throttleMagnitude[0] = throttleOrigin - y[0];
                        if (throttleMagnitude[0]<0){
                            throttleDirection[0] = "Reverse";
                        }
                        else{
                            throttleDirection[0] = "Forward";
                        }
                        Log.i("LeftJS", "Moving " + throttleDirection[0] + " == Magnitude: " + throttleMagnitude[0]);
                        break;

                }
                return true;
            }
        });

        FrameLayout rightStick = findViewById(R.id.flRightStick);
        turnOrigin = rightStick.getX() - rightStick.getWidth()+310;
        //==================== Right Stick (left/right) on-touch Listener ==========================

        rightStick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                //Log.i("RightJS", "Touch detected");
                int eventType = motionEvent.getActionMasked();

                switch (eventType){

                    case MotionEvent.ACTION_DOWN:
                        Log.i("RightJS", "Action Down");
                        break;
                    case MotionEvent.ACTION_UP:
                        turnMagnitude[0] = 0;
                        Log.i("RightJS", "Not Turning " + " == Magnitude: " + turnMagnitude[0]);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x[0] = motionEvent.getX();
                        turnMagnitude[0] = turnOrigin - x[0];
                        if (turnMagnitude[0]<0){
                            TurnDirection[0] = "Right";
                        }
                        else{
                            TurnDirection[0] = "Left";
                        }
                        Log.i("RightJS", "Turning " + TurnDirection[0] + " == Magnitude: " + turnMagnitude[0]);
                        break;

                }
                return true;
            }
        });

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