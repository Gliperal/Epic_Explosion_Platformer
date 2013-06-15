package utilities;

import java.awt.Point;

public class Vector {
	public static final CoordinateAxis[] axisOrder = {CoordinateAxis.x, CoordinateAxis.y, CoordinateAxis.z};
	private static final int maxDimension = axisOrder.length;
	private Double[] coordinates;
	
	public Vector(double c1, double c2)				{	this(new Double[]{c1, c2, null});	}
	public Vector(double c1, double c2, double c3)	{	this(new Double[]{c1, c2, c3});		}
	
	public Vector(Double[] coords) {
		coordinates = coords;
	}

	public Vector(Point point) {
		this(point.x, point.y);
	}
	
	public Double getComponent(CoordinateAxis axis) {
		for(int i = 0; i < maxDimension; i++) {
			if(axisOrder[i] == axis) {
				return coordinates[i];
			}
		}
		return null;
	}

	public void setComponent(CoordinateAxis axis, Double d) {
		for(int i = 0; i < maxDimension; i++) {
			if(axisOrder[i] == axis) {
				coordinates[i] = d;
			}
		}
	}
	
	public Vector plus(Vector that) {
		int minDimension = Math.min(this.dimension(), that.dimension());
		Double[] resultVector = new Double[minDimension];
		for(int i = 0; i < minDimension; i++) {
			Double thisCoord;
			Double thatCoord;
			
			try {
				thisCoord = coordinates[i];
				thatCoord = that.coordinates[i];
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
			
			if(thisCoord != null && thatCoord != null) {
				resultVector[i] = thisCoord + thatCoord;
			} else {
				continue;
			}
		}
		return new Vector(resultVector);
	}
	
	public Vector plusStrict(Vector that) throws VectorException {
		Double[] resultVector = new Double[maxDimension];
		for(int i = 0; i < maxDimension; i++) {
			Double thisCoord = coordinates[i];
			Double thatCoord = that.coordinates[i];
			if(thisCoord != null && thatCoord != null) {
				resultVector[i] = thisCoord + thatCoord;
			} else if(thisCoord == null && thatCoord == null) {
				continue;
			} else {
				throw new VectorException("Conflicting " + axisOrder[i] + " coordinates");
			}
		}
		return new Vector(resultVector);
	}
	
	public Vector times(double scale) {
		Double[] resultVector = new Double[maxDimension];
		for(int i = 0; i < maxDimension; i++) {
			if(coordinates[i] != null) {
				resultVector[i] = coordinates[i]*scale;
			}
		}
		return new Vector(resultVector);
	}
	
	public Vector minus(Vector that) {
		return this.plus(that.times(-1));
	}
	
	public Vector minusStrict(Vector that) throws VectorException {
		return this.plusStrict(that.times(-1));
	}
	
	public double dot(Vector that) {
		int minDimension = Math.min(this.dimension(), that.dimension());
		double result = 0;
		for(int i = 0; i < minDimension; i++) {
			Double thisCoord;
			Double thatCoord;
			
			try {
				thisCoord = coordinates[i];
				thatCoord = that.coordinates[i];
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
			
			if(thisCoord != null && thatCoord != null) {
				result += thisCoord*thatCoord;
			} else {
				continue;
			}
		}
		return result;
	}
	
	public double dotStrict(Vector that) throws VectorException {
		double result = 0;
		for(int i = 0; i < maxDimension; i++) {
			Double thisCoord = coordinates[i];
			Double thatCoord = that.coordinates[i];
			if(thisCoord != null && thatCoord != null) {
				result += thisCoord*thatCoord;
			} else if(thisCoord == null && thatCoord == null) {
				continue;
			} else {
				throw new VectorException("Conflicting " + axisOrder[i] + " coordinates");
			}
		}
		return result;
	}
	
	public Vector cross(Vector that) {
		double a0 = coordinates[0];
		double a1 = coordinates[1];
		double a2 = coordinates[2];
		double b0 = that.coordinates[0];
		double b1 = that.coordinates[1];
		double b2 = that.coordinates[2];
		return new Vector(a1*b2 - a2*b1, a2*b0 - a0*b2, a0*b1 - a1*b0);
	}
	
	private int dimension() {
		return coordinates.length;
	}

	public double magnitude() {
		int i = 0;
		for(Double d : coordinates) {
			if(d != null) {
				i += Math.pow(d, 2);
			}
		}
		return Math.sqrt(i);
	}
	
	public String toString() {
		return "<" + Util.listToString(coordinates) + ">";
	}
	
	public static Vector createVectorOfSize(int size) {
		Double[] coords = new Double[Vector.maxDimension];
		for(int index = 0; index < size; index++) {
			coords[index] = 0.0;
		}
		return new Vector(coords);
	}
	
	public Point toPoint() {
		int x = (int)(double) getComponent(CoordinateAxis.x);
		int y = (int)(double) getComponent(CoordinateAxis.y);
		return new Point(x, y);
	}
}
