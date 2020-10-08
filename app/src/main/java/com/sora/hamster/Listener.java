package com.sora.hamster;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sora.mirroring.R;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class Listener {

    private Button btforward;
    private Button btbackward;
    private Button btleftward;
    private Button btrightward;
    private EditText textemqxIp;
    private EditText textemqxPort;

    final MqttConfig mqttConfig = new MqttConfig();
    final MqttMessageSendHandler mqttMessageSendHandler = new MqttMessageSendHandler();
    final int qos = 0;
    final String topic = "car";

    public Listener() {
        super();
    }

    public Listener(Button forward, Button backward, Button leftward, Button rightward, EditText textemqxIp, EditText textemqxPort) {
        this.btforward = forward;
        this.btbackward = backward;
        this.btleftward = leftward;
        this.btrightward = rightward;
        this.textemqxIp = textemqxIp;
        this.textemqxPort = textemqxPort;
    }

    public void listenerBound() {

        List<Button> list = new ArrayList<>();
        list.add(btforward);
        list.add(btbackward);
        list.add(btleftward);
        list.add(btrightward);
        for (Button b :list) {
            b.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        mqttMessageSendHandler.send(view);
                    }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        mqttMessageSendHandler.stop(view);
                    }
                    return true;
                }
            });
        }
        textemqxIp.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    String ip = textemqxIp.getText().toString();
                    String port = textemqxPort.getText().toString();
                    mqttConfig.refresh(ip, port);
                }
            }
        });
        textemqxPort.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    String ip = textemqxIp.getText().toString();
                    String port = textemqxPort.getText().toString();
                    mqttConfig.refresh(ip, port);
                }
            }
        });
    }
}
