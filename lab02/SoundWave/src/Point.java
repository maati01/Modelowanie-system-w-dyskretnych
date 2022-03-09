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
	public static Integer []types ={0,1,2};
	int type;
	public static int sinInput;



	public Point() {
		type = 0;
		clear();
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		pressure = nVel = eVel = wVel = sVel = 0;
	}

	public void updateVelocity() {
		if(type == 0)
			nVel = nVel - (nNeighbor.pressure - pressure);
			eVel = eVel - (eNeighbor.pressure - pressure);
			sVel = sVel - (sNeighbor.pressure - pressure);
			wVel = wVel - (wNeighbor.pressure - pressure);
	}

	public void updatePresure() {
		float c_square = 0.5F;
		if(type == 0)
			pressure = pressure - c_square*(nVel + eVel + sVel + wVel);

		if(type == 2) {
			double radians = Math.toRadians(sinInput);
			pressure = (float) (Math.sin(radians));
		}

	}

	public float getPressure() {
		return pressure;
	}
}