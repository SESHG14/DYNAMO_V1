package com.example.carone_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen1);

        ImageButton controller = findViewById(R.id.btnControl);

        controller.setOnClickListener(new View.OnClickListener() {   // Onclick for controller button
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Controller.class);
                startActivity(i);
            }
        });

        ImageButton home = findViewById(R.id.btnHome);

        home.setOnClickListener(new View.OnClickListener() {         //Onclick for home button
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Home Page Selected", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton settings = findViewById(R.id.btnSettings);

        settings.setOnClickListener(new View.OnClickListener() {         //Onclick for settings button
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Currently under Construction", Toast.LENGTH_SHORT).show();
            }
        });

        Switch lock = findViewById(R.id.swLock);

        lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {    //Action listener for switch component
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(getApplicationContext(), "It's Locked...I think?", Toast.LENGTH_SHORT).show();
                }
                else if (!b){
                    Toast.makeText(getApplicationContext(), "Vehicle Unlocked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView BatteryPercentage = findViewById(R.id.txtBatt_Pcnt);   //Text View objects to work with

        TextView Range = findViewById(R.id.txtRange);

        TextView Voltage = findViewById(R.id.txtVoltage);

        TextView BatteryState = findViewById(R.id.txtBattState);

        TextView Connection = findViewById(R.id.txtConn);
    }
}