package au.com.tla.robot;

/**
 * GridPosition tracks a single Position on a Grid.
 * 
 * @author Clinton
 * 
 */
public class GridPosition {
    private final Grid grid;
    private Position position;

    public GridPosition(Grid grid) {
        if (grid == null) {
            throw new NullPointerException("Grid cannot be null");
        }
        this.grid = grid;
    }

    public boolean isValidPosition(Position position) {
        return position != null && grid.isValidPosition(position.x, position.y);
    }

    public boolean setPosition(Position position) {
        if (isValidPosition(position)) {
            this.position = position;
            return true;
        }
        return false;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean moveForward(Heading heading) {
        if (this.position == null || heading == null) {
            return false;
        }
        return setPosition(getProjectedPosition(heading));
    }

    protected Position getProjectedPosition(Heading heading) {
        int newX = position.x;
        int newY = position.y;

        switch (heading) {
        case NORTH:
            newY++;
            break;
        case EAST:
            newX++;
            break;
        case SOUTH:
            newY--;
            break;
        case WEST:
            newX--;
            break;
        default:
            throw new IllegalArgumentException("Cannot move towards Heading: " + heading);
        }
        return new Position(newX, newY);
    }

    public boolean isPlaced() {
        return position != null;
    }
}
