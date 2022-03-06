import java.util.ArrayList;
import java.util.Random;

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
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and
		//number of active neighbors
		if(currentState > 0){
			nextState = currentState - 1;
		}
		if(!neighbors.isEmpty() & currentState == 0){
			if(neighbors.get(0).currentState > 0)
			nextState = 6;
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point

	public int countAliveNeighbors(){
		return neighbors.stream()
				.mapToInt(a -> a.currentState)
				.sum();
	}

	public boolean drop(){
		Random rand = new Random();
		return rand.nextInt(20)==0;
	}
}
