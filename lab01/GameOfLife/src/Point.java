import java.util.ArrayList;
import java.util.Arrays;

public class Point {
    private ArrayList<Point> neighbors;
    private int currentState;
    private int nextState;
    private int numStates = 6;

    public Point() {
        currentState = 0;
        nextState = 0;
        neighbors = new ArrayList<Point>();
    }

    public void clicked() {
        currentState = (++currentState) % numStates;
    }

    public int getState() {
        return currentState;
    }

    public void setState(int s) {
        currentState = s;
    }

    public void calculateNewStateWithArguments(ArrayList<Integer> numForDead, ArrayList<Integer> numForAlive) {
        int numberOfActiveNeighbors = countAliveNeighbors();
        if (numForDead.contains(numberOfActiveNeighbors) && this.currentState == 0) {
            this.nextState = 1;
        } else if (numForAlive.contains(countAliveNeighbors()) && this.currentState == 1) {
            this.nextState = 1;
        } else {
            this.nextState = 0;
        }
    }

    public void calculateNewState(String rule) {
        switch (rule) {
            case "default" -> calculateNewStateWithArguments(new ArrayList<>(Arrays.asList(3)), new ArrayList<>(Arrays.asList(2, 3)));
            case "cities" -> calculateNewStateWithArguments(new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8)), new ArrayList<>(Arrays.asList(2, 3, 4, 5)));
            case "coral" -> calculateNewStateWithArguments(new ArrayList<>(Arrays.asList(3)), new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8)));
            case "day and night" -> calculateNewStateWithArguments(new ArrayList<>(Arrays.asList(3, 6, 7, 8)), new ArrayList<>(Arrays.asList(3, 4, 6, 7, 8)));
            case "stains" -> calculateNewStateWithArguments(new ArrayList<>(Arrays.asList(3, 6, 7, 8)), new ArrayList<>(Arrays.asList(2, 3, 5, 6, 7, 8)));
        }
    }

    public void changeState() {
        currentState = nextState;
    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }

    public int countAliveNeighbors() {
        return neighbors.stream()
                .mapToInt(a -> a.currentState)
                .sum();
    }
}
