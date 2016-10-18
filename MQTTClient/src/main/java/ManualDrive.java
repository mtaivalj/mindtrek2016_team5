
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ManualDrive {

        private static String read         = "team5_read";
        private static String write        = "team5_write";
        private static int qos             = 2;
        private static String broker       = "tcp://54.93.150.126:1883";
        private static String clientId     = "team5";
        private static MemoryPersistence persistence = new MemoryPersistence();
        
        public static void main( String... args ) {
        	
        	if( args.length != 1 ) {
        		System.out.println( "Wrong parameters!" );
        		System.exit( 1 );
        	}
        	
        	int m1 = args[0].contains( "1" ) ? 1 : 0;
        	int m2 = args[0].contains( "2" ) ? 2 : 0;
        	int mUp = args[0].contains( "UP" ) ? 3 : 0;

        	Message message = new Message( m1, m2, mUp, 255, 1 );
        	
        	try {
	            MqttClient client = new MqttClient( broker, clientId, persistence );
	            MqttConnectOptions connOpts = new MqttConnectOptions();
	            connOpts.setCleanSession( true );
	            client.connect( connOpts );
	            client.publish( write, new MqttMessage( message.get() ) );
	            System.out.println( "Command started!" );
	            client.disconnect();
	            System.exit( 0 );
	        } catch( MqttException me ) {
	            me.printStackTrace( System.out );
	            System.exit( 1 );
	        }
    }
}
