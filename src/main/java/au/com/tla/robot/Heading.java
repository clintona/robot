package au.com.tla.robot;

/**
 * Represents a basic compass heading.
 * 
 * @author Clinton
 * 
 */
public enum Heading {
    NORTH, SOUTH, EAST, WEST;

    public Heading turnLeft90() {
        Heading newHeading = null;
        switch (this) {
        case NORTH:
            newHeading = WEST;
            break;
        case SOUTH:
            newHeading = EAST;
            break;
        case EAST:
            newHeading = NORTH;
            break;
        case WEST:
            newHeading = SOUTH;
            break;
        default:
            throw new IllegalArgumentException("Cannot turn from this Heading: " + this);
        }
        return newHeading;
    }

    public Heading turnRight90() {
        Heading newHeading = null;
        switch (this) {
        case NORTH:
            newHeading = EAST;
            break;
        case SOUTH:
            newHeading = WEST;
            break;
        case EAST:
            newHeading = SOUTH;
            break;
        case WEST:
            newHeading = NORTH;
            break;
        default:
            throw new IllegalArgumentException("Cannot turn from this Heading: " + this);
        }
        return newHeading;
    }
}
