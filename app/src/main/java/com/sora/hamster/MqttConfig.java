package com.sora.hamster;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttConfig {

    private MqttClient sampleClient;
    private String emqxIp = "172.16.178.13";
    private String emqxPort = "1883";

    public MqttConfig(){
        this.createConnect();
    }

    private void createConnect(){
        String broker = "tcp://"+this.emqxIp+":"+this.emqxPort;
        String clientId = "Android";
        MemoryPersistence persistence = new MemoryPersistence();
        try{
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
        } catch(MqttException e){
            e.printStackTrace();
        }
    }

    public void disConnect(){
        try {
            sampleClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected");
    }

    public MqttClient getMqttClient(){
        return sampleClient;
    }

    public void refresh(String ip,String port){
        this.emqxIp = ip;
        this.emqxPort = port;
        this.createConnect();
    }
}
