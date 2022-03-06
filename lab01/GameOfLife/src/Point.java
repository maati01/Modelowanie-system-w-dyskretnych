import java.util.ArrayList;

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
		int numberOfActiveNeighbors = countAliveNeighbors();
		if(numberOfActiveNeighbors == 3 && this.currentState == 0){
			this.nextState = 1;
		}
		else if((numberOfActiveNeighbors == 2 || numberOfActiveNeighbors == 3) && this.currentState == 1){
			this.nextState = 1;
		}else{
			this.nextState = 0;
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
}
