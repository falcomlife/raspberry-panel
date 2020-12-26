package com.sora.hamster;

import android.graphics.Color;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sora.mirroring.R;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class Listener {

    private View layoutSetting;
    private View layoutControl;
    private Button btforward;
    private Button btbackward;
    private Button btleftward;
    private Button btrightward;
    private ImageButton setting;
    private ImageButton close;
    private ImageButton clockwise;
    private ImageButton anticlockwise;
    private EditText textemqxIp;
    private EditText textemqxPort;

    final MqttConfig mqttConfig = new MqttConfig();
    final MqttMessageSendHandler mqttMessageSendHandler = new MqttMessageSendHandler();
    final int qos = 0;
    final String topic = "car";

    public Listener() {
        super();
    }

    public Listener(Button forward, Button backward, Button leftward, Button rightward, ImageButton close, ImageButton setting,  ImageButton clockwise, ImageButton anticlockwise,View layoutSetting, View layoutControl, EditText textemqxIp, EditText textemqxPort) {
        this.btforward = forward;
        this.btbackward = backward;
        this.btleftward = leftward;
        this.btrightward = rightward;
        this.clockwise = clockwise;
        this.anticlockwise = anticlockwise;
        this.close = close;
        this.textemqxIp = textemqxIp;
        this.textemqxPort = textemqxPort;
        this.layoutSetting = layoutSetting;
        this.layoutControl = layoutControl;
        this.setting = setting;
    }

    public void animationTouchDown(final Button b){
        Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(300);
        b.startAnimation(animation);
    }

    public void listenerBound() {
        List<Button> list = new ArrayList<>();
        list.add(btforward);
        list.add(btbackward);
        list.add(btleftward);
        list.add(btrightward);
        for (final Button b :list) {
            b.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        mqttMessageSendHandler.send(view);
                        animationTouchDown(b);
                    }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        mqttMessageSendHandler.stop(view);
                    }
                    return true;
                }
            });
        }
        clockwise.setOnTouchListener(new View.OnTouchListener() {
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
        anticlockwise.setOnTouchListener(new View.OnTouchListener() {
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
        setting.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                layoutSetting.setVisibility(View.VISIBLE);
                layoutControl.setVisibility(View.GONE);
                return true;
            }
        });
        close.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                layoutControl.setVisibility(View.VISIBLE);
                layoutSetting.setVisibility(View.GONE);
                return true;
            }
        });
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
