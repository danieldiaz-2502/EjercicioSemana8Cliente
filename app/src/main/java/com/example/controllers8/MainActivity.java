package com.example.controllers8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button upBtn,downBtn,leftBtn,rightBtn, fireBtn;
    private BufferedWriter bwriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        fireBtn = findViewById(R.id.fireBtn);


        //Metodo de suscripcion
        upBtn.setOnTouchListener(this);
        downBtn.setOnTouchListener(this);
        leftBtn.setOnTouchListener(this);
        rightBtn.setOnTouchListener(this);
        fireBtn.setOnTouchListener(this);


        new Thread(

                () -> {

                    try {
                        Socket socket = new Socket("192.168.1.10", 5000);
                        //Socket socket = new Socket("10.0.2.2", 6000);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bwriter = new BufferedWriter(osw);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();


    }

    public void onClickAlterado(View v) {
        Gson gson = new Gson();


        switch (v.getId()){
            case R.id.upBtn:
                Coordenada up = new Coordenada("UP");
                String jsonup = gson.toJson(up);
                enviar(jsonup);
                break;
            case R.id.downBtn:
                Coordenada down = new Coordenada("DOWN");
                String jsondown = gson.toJson(down);
                enviar(jsondown);
                break;
            case R.id.leftBtn:
                Coordenada left = new Coordenada("LEFT");
                String jsonleft = gson.toJson(left);
                enviar(jsonleft);
                break;
            case R.id.rightBtn:
                Coordenada right = new Coordenada("RIGHT");
                String jsonright = gson.toJson(right);
                enviar(jsonright);
                break;
        }
    }

    public void enviar(String msg){
        new Thread(() -> {
            try {
                bwriter.write(msg + "\n");
                bwriter.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Gson gson = new Gson();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                switch (v.getId()){
                    case R.id.upBtn:
                        Coordenada up = new Coordenada("UP");
                        String jsonup = gson.toJson(up);
                        enviar(jsonup);
                        break;
                    case R.id.downBtn:
                        Coordenada down = new Coordenada("DOWN");
                        String jsondown = gson.toJson(down);
                        enviar(jsondown);
                        break;
                    case R.id.leftBtn:
                        Coordenada left = new Coordenada("LEFT");
                        String jsonleft = gson.toJson(left);
                        enviar(jsonleft);
                        break;
                    case R.id.rightBtn:
                        Coordenada right = new Coordenada("RIGHT");
                        String jsonright = gson.toJson(right);
                        enviar(jsonright);
                        break;
                    case R.id.fireBtn:
                        Disparo fire = new Disparo("FIRE");
                        String jsonfire = gson.toJson(fire);
                        enviar(jsonfire);
                        break;
                }

            break;

            case MotionEvent.ACTION_UP:

                switch (v.getId()){
                    case R.id.upBtn:
                        Coordenada upstop = new Coordenada("UPSTOP");
                        String upstopjsonstop = gson.toJson(upstop);
                        enviar(upstopjsonstop);
                        break;
                    case R.id.downBtn:
                        Coordenada downstop = new Coordenada("DOWNSTOP");
                        String jsondownstop = gson.toJson(downstop);
                        enviar(jsondownstop);
                        break;
                    case R.id.leftBtn:
                        Coordenada leftstop = new Coordenada("LEFTSTOP");
                        String jsonleftstop = gson.toJson(leftstop);
                        enviar(jsonleftstop);
                        break;
                    case R.id.rightBtn:
                        Coordenada rightstop = new Coordenada("RIGHTSTOP");
                        String jsonrightstop = gson.toJson(rightstop);
                        enviar(jsonrightstop);
                        break;
                    case R.id.fireBtn:
                        Disparo firestop = new Disparo("FIRESTOP");
                        String jsonfirestop = gson.toJson(firestop);
                        enviar(jsonfirestop);
                        break;
                }

                break;
        }
        return true;
    }
}