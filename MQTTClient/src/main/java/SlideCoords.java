
public class SlideCoords {
    
    private final int size;
    private final int[] x_arr;
    private final int[] y_arr;
    private final int[] z_arr;
    
    private int c = 0;
    
    public SlideCoords( int size ) {
        this.size = size;
        x_arr = new int[ size ];
        y_arr = new int[ size ];
        z_arr = new int[ size ];        
    }
    
    public synchronized void set( int x, int y, int z ) {
        x_arr[ c % size ] = x;
        y_arr[ c % size ] = y;
        z_arr[ c % size ] = z;
        c++;
    }

    public synchronized double getX() {
        int s = size;
        if( c < size ) {
            s = c;
        }
        int sum = 0;
        for( int i : x_arr ) {
            sum += i;
        }
        return sum / s;
    }
    
    public synchronized double getY() {
        int s = size;
        if( c < size ) {
            s = c;
        }
        int sum = 0;
        for( int i : y_arr ) {
            sum += i;
        }
        return sum / s;
    }
    
    public synchronized double getZ() {
        int s = size;
        if( c < size ) {
            s = c;
        }
        int sum = 0;
        for( int i : z_arr ) {
            sum += i;
        }
        return sum / s;
    }    
}
