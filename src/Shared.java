import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Shared
{
    public static final int SPEED = 20;
    public static final int RADIUS = 25;
    public static final int DIAMETER = RADIUS*2;
    public static final int ROWS = 14;
    public static final int COLS = 8;
    public static final char EMPTY = '-';
    public static final char[] COLORS = {'R', 'G', 'B', 'O', 'Y', 'P'};
    public static final int[][] EVEN_NEIGHBORS = {{-1,-1},{-1,0},{0,-1},{0,1},{1,-1},{1,0}};
    public static final int[][] ODD_NEIGHBORS = {{-1,0},{-1,1},{0,-1},{0,1},{1,0},{1,1}};
    public static final Map<Character, Color> colorMap = new HashMap<>(){{
        put('R',Color.RED);
        put('G',Color.GREEN);
        put('B',Color.BLUE);
        put('O',Color.ORANGE);
        put('Y',Color.YELLOW);
        put('P',Color.PINK);
        put(EMPTY,Color.WHITE);
    }};
}
