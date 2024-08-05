package test;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import com.google.gson.Gson;

public class PublishSample {

    public static void main(String[] args) throws Exception {
        //mqtt broker url
        String broker = "tcp://url:port";
        String clientid = "********";
        String username = "********";
        String password = "********";;
        String topic = "********";;
        int qos = 1;
        while (true) {
            Thread.sleep(1000);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String formattedDate = formatter.format(date);

            try {
                MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
                MqttConnectOptions options = new MqttConnectOptions();
                options.setUserName(username);
                options.setPassword(password.toCharArray());
                options.setConnectionTimeout(60);
                options.setKeepAliveInterval(60);
                // connect broker
                client.connect(options);
                // build message QoS
                HashMap<String, String> content = new HashMap<>();
                content.put("name","Client");
                content.put("address","DiQiu");
                content.put("job","SOHO");
                content.put("time",formattedDate);
                Gson gson = new Gson();
                String payload = gson.toJson(content);
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(qos);
                // push message
                client.publish(topic, message);
                System.out.println("Message published");
                System.out.println("topic: " + topic);
                System.out.println(payload);
                // close client
                client.disconnect();
                client.close();
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
