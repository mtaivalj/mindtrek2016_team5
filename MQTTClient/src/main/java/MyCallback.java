import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.Gson;

public class MyCallback implements MqttCallback {
    
    public SlideCoords slide = new SlideCoords( 10 );
    private Gson gson = new Gson();
    
    @Override
    public void connectionLost( Throwable t ) {
        t.printStackTrace( System.err );
    }

    @Override
    public void deliveryComplete( IMqttDeliveryToken token ) {
        System.out.println( token.getResponse() );
    }

    @Override
    public void messageArrived( String topic, MqttMessage mqttMessage ) throws Exception {
        String message = new String( mqttMessage.getPayload() );
        System.out.println( "Message: " +  message );
        Response response = gson.fromJson( message, Response.class );
        slide.set( response.x, response.y, response.z );
    }
}
