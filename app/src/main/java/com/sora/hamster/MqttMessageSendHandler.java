package com.sora.hamster;

import android.view.View;
import android.widget.Button;

import com.sora.mirroring.R;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MqttMessageSendHandler {

    private Map<Integer, Boolean> taskSet = null;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    final MqttConfig mqttConfig = new MqttConfig();
    final int qos = 0;
    final String topic = "car";

    public MqttMessageSendHandler() {
        this.taskSet = new HashMap<>();
    }

    public Boolean getTask(Integer id) {
        return this.taskSet.get(id);
    }

    public void send(View v) {
        final View vInner = v;
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                taskSet.put(vInner.getId(), Boolean.TRUE);
                while (taskSet.get(vInner.getId())) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MqttClient client = null;
                    try {
                        MqttMessage message = null;
                        if (vInner.getId() == R.id.forward) {
                            message = new MqttMessage("{\"action\": \"forward\"}".getBytes());
                        } else if (vInner.getId() == R.id.backward) {
                            message = new MqttMessage("{\"action\": \"backward\"}".getBytes());
                        } else if (vInner.getId() == R.id.leftward) {
                            message = new MqttMessage("{\"action\": \"leftward\"}".getBytes());
                        } else if (vInner.getId() == R.id.rightward) {
                            message = new MqttMessage("{\"action\": \"rightward\"}".getBytes());
                        }
                        client = mqttConfig.getMqttClient();
                        message.setQos(qos);
                        client.publish(topic, message);
                    } catch (MqttException me) {
                        me.printStackTrace();
                        mqttConfig.disConnect();
                    }
                }
            }
        });
    }

    public void stop(View v) {
        taskSet.put(v.getId(), Boolean.FALSE);
    }
}
