
public class Message {
    
    int m1;
    int m2;
    int mUp;
    int time;
    int command_id;
    
    public Message( int m1, int m2, int mUp, int time, int command_id ) {
        this.m1 = m1;
        this.m2 = m2;
        this.mUp = mUp;
        this.time = time;
        this.command_id = command_id;
    }
    
    public byte[] get() {
        String str = String.format( "{\"m1\":\"%d\",\"m2\":\"%d\",\"m_up\":\"%d\",\"time\":\"%d\",\"command_id\":\"%d\"}", m1, m2, mUp, time, command_id );
        return str.getBytes();
    }
}
