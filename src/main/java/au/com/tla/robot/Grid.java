package au.com.tla.robot;

/**
 * Represents a square grid with (0,0) as the origin. A grid size of 1 is valid. Grid coordinates
 * cannot be negative.
 * 
 * @author Clinton
 * 
 */
public class Grid {

    private int size;

    public Grid(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Grid size must be > 0");
        }
        this.size = size;
    }

    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < size && y >= 0 && y < size);
    }

    public int getSize() {
        return size;
    }
}
