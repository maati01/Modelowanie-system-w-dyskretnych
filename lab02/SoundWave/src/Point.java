public class Point {

	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;


	public Point() {
		clear();
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		pressure = nVel = eVel = wVel = sVel = 0;
	}

	public void updateVelocity() {
		nVel = nVel - (nNeighbor.pressure - pressure);
		eVel = eVel - (eNeighbor.pressure - pressure);
		sVel = sVel - (sNeighbor.pressure - pressure);
		wVel = wVel - (wNeighbor.pressure - pressure);
	}

	public void updatePresure() {
		float c_square = 0.5F;
		pressure = pressure - c_square*(nVel + eVel + sVel + wVel);

	}

	public float getPressure() {
		return pressure;
	}
}