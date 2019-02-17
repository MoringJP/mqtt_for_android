package com.example.administrator.myapplication;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button mSetMessage;
    private Button mSetTop;
    private Button mSetConnection;
    private Button mStopConnection;
    private EditText mGetMessage;
    private BlockingConnection connection;
    private PlayerTimer playTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetMessage = (Button) findViewById(R.id.setmessage);
        mSetTop = (Button) findViewById(R.id.settop);
        mSetConnection = (Button) findViewById(R.id.setconnection);
        mStopConnection = (Button) findViewById(R.id.stopconnection);
        mGetMessage = (EditText) findViewById(R.id.message_editText);
        setContentView(R.layout.activity_main);
    }
    /*
    *初始化Mqtt连接
     */
    private void InitMqttConnection() {
        MQTT mqtt =  new MQTT();
        try {
            mqtt.setHost("47.75.0.141",1883);
        } catch (Exception e) {
            Log.e("mqtt",e.toString());
        }
        connection = mqtt.blockingConnection();
        try {
            connection.connect();
        } catch (Exception e) {
            Log.e("MqttConnection","Error:"+e.toString());
        }
    }
    private void SetTopMessage(String topic,String message) {
        
    }
    /**
     * start Timer
     */
    protected synchronized void startPlayerTimer() {
        stopPlayerTimer();
        if (playTimer == null) {
            playTimer = new PlayerTimer();
            Timer m_musictask = new Timer();
            m_musictask.schedule(playTimer, 5000, 5000);
        }
    }

    /**
     * stop Timer
     */
    protected synchronized void stopPlayerTimer() {
        try {
            if (playTimer != null) {
                playTimer.cancel();
                playTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class PlayerTimer extends TimerTask {

        public PlayerTimer() {
        }

        public void run() {
            //execute task
        }
    }

    private void getTopMessage(String topic) {

    }
}
