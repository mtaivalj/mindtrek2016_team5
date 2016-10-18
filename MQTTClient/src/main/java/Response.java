
public class Response {

    public final String baddr;
    public final int rssi;
    public final int time_to_go;
    public final int command_id;
    public final int x;
    public final int y;
    public final int z;
    
    public Response( String baddr, int rssi, int ttg, int cid, int x, int y, int z ) {
        this.baddr = baddr;
        this.rssi = rssi;
        this.time_to_go = ttg;
        this.command_id = cid;
        this.x = x;
        this.y = y;
        this.z = z;
    } 
}
