package Base;

public class Vector2 {

    public int x, y;

    public static final Vector2 up = new Vector2(-1, 0);
    public static final Vector2 right = new Vector2(0, 1);
    public static final Vector2 down = new Vector2(1, 0);
    public static final Vector2 left = new Vector2(0, -1);

    public Vector2(int x, int y) {
        this.x = x; this.y = y;
    }

    public Vector2() { }

    public Vector2(Vector2 vec) {
        x = vec.x;
        y = vec.y;
    }

    public Vector2 AddVector(Vector2 toAdd) {
        this.x += toAdd.x;
        this.y += toAdd.y;
        return this;
    }

    public String toString() {
        return "("+x+", "+y+")";
    }
    
    public enum TravelDirection {
        North,
        East,
        South,
        West
    }
}
