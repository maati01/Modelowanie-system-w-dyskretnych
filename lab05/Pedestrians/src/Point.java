import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Point {

    public ArrayList<Point> neighbors;
    public static Integer[] types = {0, 1, 2, 3};
    public int type;
    public int staticField;
    public boolean isPedestrian;
    boolean blocked = false;

    public Point() {
        type = 0;
        staticField = 100000;
        neighbors = new ArrayList<Point>();
    }

    public void clear() {
        staticField = 100000;

    }

    public int getStaticField() {
        return staticField;
    }

    public boolean calcStaticField() {
        int val = neighbors.stream()
                .mapToInt(Point::getStaticField)
                .min()
                .orElse(10000) + 1;
        if (staticField > val) {
            staticField = val;
            return true;
        }
        return false;
    }

    public void move() {
        if (isPedestrian && !blocked) {
            Point neighbor = findNeighbor(this);
            if (neighbor != null){
                type = 0;
                isPedestrian = false;
                if(neighbor.type == 2) {
                    neighbor.isPedestrian = false;
                }else{
                    neighbor.type = 3;
                    neighbor.isPedestrian = true;
                    neighbor.blocked = true;
                }
            }
        }
    }

    public Point findNeighbor(Point curr) {
        int min = Integer.MAX_VALUE;
        Point point = null;
        for (int i = 0; i < curr.neighbors.size(); i++) {
            if (neighbors.get(i).staticField < min && (neighbors.get(i).type == 0 || neighbors.get(i).type == 2)) {
                min = neighbors.get(i).staticField;
                point = neighbors.get(i);
            }
        }
        return point;
    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }
}