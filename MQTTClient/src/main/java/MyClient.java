
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyClient {
    
    public static void main( String... args ) {
    
        String read        = "team5_read";
        String write        = "team5_write";
        int qos             = 2;
        String broker       = "tcp://54.93.150.126:1883";
        String clientId     = "team5";
        MemoryPersistence persistence = new MemoryPersistence();
        
        try {
            MqttClient client = new MqttClient( broker, clientId, persistence );
            MyCallback cb = new MyCallback();
            client.setCallback( cb );
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession( true );
            System.out.print( "Connecting to broker: " + broker + " ... " );
            client.connect( connOpts );
            System.out.println( "OK" );
            
            System.out.print( "Subscribing ... " );
            client.subscribe( read, qos );               
            System.out.println( "OK" );

            for( int i = 0; i < 1000; i++ ) {
                sleep( 1 );
                double x = cb.slide.getX();
                if( x >= 0 ) {
                    client.publish( write, new MqttMessage( Library.RFW_1SEC.get() ) );                    
                } else {
                    client.publish( write, new MqttMessage( Library.LFW_1SEC.get() ) );                                
                }
            }
            /*
            try {
                Thread.sleep( 1000 );
            } catch( InterruptedException ie ) {
                // Skip
            }

            int i = 0;
            while( i < 10 ) {
                client.publish( write, new MqttMessage( Library.LFW_1SEC.get() ) );            
                try {
                    Thread.sleep( 1000 );
                } catch( InterruptedException ie ) {
                    // Skip
                }
                client.publish( write, new MqttMessage( Library.RFW_1SEC.get() ) );            
                
                try {
                    Thread.sleep( 1000 );
                } catch( InterruptedException ie ) {
                    // Skip
                }
                i++;
            }
            */
            
            client.disconnect();
            System.out.println( "Disconnected" );
            System.exit( 0 );

        } catch( MqttException me ) {
            System.out.println( "reason " + me.getReasonCode() );
            System.out.println( "msg " + me.getMessage() );
            System.out.println( "loc " + me.getLocalizedMessage() );
            System.out.println( "cause " + me.getCause() );
            System.out.println( "excep " + me );
            me.printStackTrace();
        }
    }
        
    private static void sleep( int seconds ) {
        try {
            Thread.sleep( seconds * 1000 );
        } catch( InterruptedException ie ) {
            // Skip
        }
    }
}
