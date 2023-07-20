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

//okhttp libraries
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


import org.w3c.dom.Text;

public class Controller extends AppCompatActivity {

    private OkHttpClient client;
    WebSocket ws;

    int count = 0;
    int currentThrottle, currentTurn;
    String ThrottleDir,TurnDir;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        //Works but needs to be neatened and refined
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("Connected");
            //webSocket.send("F");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving bytes : " + bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing : " + code + " / " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error : " + t.getMessage());
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        client = new OkHttpClient();
        currentThrottle = 0;
        ThrottleDir = "Stop";
        currentTurn = 0;
        TurnDir="Center";
        TextView Throttle_mag = findViewById(R.id.txtThrottleMag);


        ImageButton FWRD = findViewById(R.id.btnFwrd);
        FWRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ThrottleDir.equals("B")){
                    if (currentThrottle != 0) {
                        currentThrottle -= 10;
                        ws.send(ThrottleDir + "#" + Integer.toString(currentThrottle));
                    }
                    else if (currentThrottle==0){
                        ThrottleDir = "Stop";
                        Toast toast = Toast.makeText(getApplicationContext(),"Vehicle Stationary", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else if (ThrottleDir.equals("F") || ThrottleDir.equals("Stop")){
                    if (currentThrottle!=100){
                        currentThrottle+=10;
                        ThrottleDir = "F";
                        ws.send(ThrottleDir + "#" + Integer.toString(currentThrottle));
                    }
                    else if(currentThrottle==100){
                        Toast toast = Toast.makeText(getApplicationContext(),"MAX Throttle", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                Throttle_mag.setText(ThrottleDir + "||" + Integer.toString(currentThrottle));
            }
        });

        ImageButton BWRD = findViewById(R.id.btnRev);

        BWRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ThrottleDir.equals("F")){
                    if (currentThrottle != 0) {
                        currentThrottle -= 10;
                        ws.send(ThrottleDir + "#" + Integer.toString(currentThrottle));
                    }
                    else if (currentThrottle==0){
                        ThrottleDir = "Stop";
                        ws.send("stop");
                        Toast toast = Toast.makeText(getApplicationContext(),"Vehicle Stationary", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else if (ThrottleDir.equals("B") || ThrottleDir.equals("Stop")){
                    if (currentThrottle!=100){
                        currentThrottle+=10;
                        ThrottleDir = "B";
                        ws.send(ThrottleDir + "#" + Integer.toString(currentThrottle));
                    }
                    else if(currentThrottle==100){
                        Toast toast = Toast.makeText(getApplicationContext(),"MAX Throttle", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                Throttle_mag.setText(ThrottleDir + "||" + Integer.toString(currentThrottle));
            }
        });

        ImageButton Throttlez = findViewById(R.id.btnThrottleZ);

        Throttlez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentThrottle=0;
                ThrottleDir = "Stop";
                ws.send("stop");
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
                start();
                ws.send("Init");
                Toast toast = Toast.makeText(getApplicationContext(),"Connected to CarONE", Toast.LENGTH_SHORT);  //Sends message for Power
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

    private void start() {
        //DOMT FORGET THE ENDPOINT!!!!! ==> /ws
        Request request = new Request.Builder().url("ws://192.168.18.188/test").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        ws = client.newWebSocket(request, listener);

        //client.dispatcher().executorService().shutdown();
    }

    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast;
                Toast.makeText(getApplicationContext(),txt, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void send_data(String data){
        ws.send(data);
    }
}