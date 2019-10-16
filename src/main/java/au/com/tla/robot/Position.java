package au.com.tla.robot;

/**
 * This data structure models an immutable {x,y} coordinate pair.
 * Refer "Clean Code" [Martin 2009] Chapter 6 for data structures.
 * 
 * @author Clinton
 * 
 */
public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Position) {
            Position p2 = (Position) obj;
            return p2.x == x && p2.y == y;
        } else {
            return false;
        }
    }
}
