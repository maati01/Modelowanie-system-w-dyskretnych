import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

	public void calculateNewStateDefault() {
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

	public void calculateNewStateCities() {
		int numberOfActiveNeighbors = countAliveNeighbors();
		ArrayList<Integer> numForDead = new ArrayList<Integer>(Arrays.asList(4,5,6,7,8));
		ArrayList<Integer> numForAlive = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5));
		if(numForDead.contains(numberOfActiveNeighbors) && this.currentState == 0){
			this.nextState = 1;
		}
		else if(numForAlive.contains(countAliveNeighbors()) && this.currentState == 1){
			this.nextState = 1;
		}else{
			this.nextState = 0;
		}
	}

	public void calculateNewStateCoral() {
		int numberOfActiveNeighbors = countAliveNeighbors();
		ArrayList<Integer> numForAlive = new ArrayList<Integer>(Arrays.asList(4,5,6,7,8));
		if(numberOfActiveNeighbors == 3 && this.currentState == 0){
			this.nextState = 1;
		}
		else if(numForAlive.contains(countAliveNeighbors()) && this.currentState == 1){
			this.nextState = 1;
		}else{
			this.nextState = 0;
		}
	}

	public void calculateNewStateDayAndNight() {
		int numberOfActiveNeighbors = countAliveNeighbors();
		ArrayList<Integer> numForDead = new ArrayList<Integer>(Arrays.asList(3,6,7,8));
		ArrayList<Integer> numForAlive = new ArrayList<Integer>(Arrays.asList(3,4,6,7,8));
		if(numForDead.contains(numberOfActiveNeighbors) && this.currentState == 0){
			this.nextState = 1;
		}
		else if(numForAlive.contains(countAliveNeighbors()) && this.currentState == 1){
			this.nextState = 1;
		}else{
			this.nextState = 0;
		}
	}

	public void calculateNewStateDayAndStains() {
		int numberOfActiveNeighbors = countAliveNeighbors();
		ArrayList<Integer> numForDead = new ArrayList<Integer>(Arrays.asList(3,6,7,8));
		ArrayList<Integer> numForAlive = new ArrayList<Integer>(Arrays.asList(2,3,5,6,7,8));
		if(numForDead.contains(numberOfActiveNeighbors) && this.currentState == 0){
			this.nextState = 1;
		}
		else if(numForAlive.contains(countAliveNeighbors()) && this.currentState == 1){
			this.nextState = 1;
		}else{
			this.nextState = 0;
		}
	}

	public void calculate(String rule){
		switch (rule) {
			case "default" -> calculateNewStateDefault();
			case "cities" -> calculateNewStateCities();
			case "coral" -> calculateNewStateCoral();
			case "day and night" -> calculateNewStateDayAndNight();
			case "stains" -> calculateNewStateDayAndStains();
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
