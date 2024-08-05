package test;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallback;

public class SubscribeSample {
    public static void main(String[] args) {
        try {
            String broker = "tcp://url:port";
            String clientId = "******";
            String username = "******";
            String password = "******";
            try {
                MqttClient sampleClient = new MqttClient(broker, clientId);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setUserName(username);
                connOpts.setPassword(password.toCharArray());
                connOpts.setConnectionTimeout(30);
                connOpts.setKeepAliveInterval(60);
                connOpts.setAutomaticReconnect(true);
                connOpts.setCleanSession(true);
                connOpts.setCleanSession(true);
                System.out.println("Connecting to broker: " + broker);
                //connect broker
                sampleClient.connect(connOpts);
                System.out.println("Connected");
                //subscribe topic
                String topic = "******";
                int qos = 2;
                sampleClient.subscribe(topic, qos);
                // callback func
                sampleClient.setCallback(new MqttCallback() {
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        System.out.println("Message arrived: " + new String(message.getPayload()));
                    }
                    public void connectionLost(Throwable cause) {
                        System.out.println("Connection lost");
                    }
                    public void deliveryComplete(IMqttDeliveryToken token) {
                        System.out.println("Delivery complete");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
