package platformer;

import java.awt.Graphics;
import utilities.*;

public abstract class Collidable implements GameObject {
	protected Vector position;
	protected Vector size;
	protected Vector offset;

		// Position is at predefined offset.
	
	public Collidable(Vector pos, Vector dimensions, Vector off) {
		position = pos;
		size = dimensions;
		offset = off;
	}
		
	public Collidable(double posX, double posY, int sizeX, int sizeY, int xOff, int yOff) {
		position = new Vector(posX, posY);
		size = new Vector(sizeX, sizeY);
		offset = new Vector(xOff, yOff);
	}

	public Collidable(double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int xOff, int yOff, int zOff) {
		position = new Vector(posX, posY, posZ);
		size = new Vector(sizeX, sizeY, sizeZ);
		offset = new Vector(xOff, yOff, zOff);
	}
	
	
		// Position is at the center of the object.
	
	public Collidable(Vector pos, Vector dimensions) {
		this(pos, dimensions, dimensions.times(0.5));
	}
	
	public Collidable(double posX, double posY, int sizeX, int sizeY) {
		this(posX, posY, sizeX, sizeY, sizeX/2, sizeY/2);
	}
	
	public Collidable(double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ) {		// Position is at the center of the object.
		this(posX, posY, posZ, sizeX, sizeY, sizeZ, sizeX/2, sizeY/2, sizeZ/2);
	}

		// Position is at top left [front] corner
	

	public Collidable(double posX, double posY, int sizeX, int sizeY, boolean absolute) {
		this(posX, posY, sizeX, sizeY, 0, 0);
	}
	
	public Collidable(double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, boolean absolute) {
		this(posX, posY, posZ, sizeX, sizeY, sizeZ, 0, 0, 0);
	}
	
	public Collidable(Vector pos, Vector dimensions, boolean absolute) {
		this(pos, dimensions);
	}

	
	
	
	public boolean doesIntersectWith(Vector thatPos, Vector thatSize) {
		for(CoordinateAxis axis : Vector.axisOrder) {
			Double specificPos = getCollisionPosition().getComponent(axis);
			Double thatSpecificPos = thatPos.getComponent(axis);
			if(specificPos == null && thatSpecificPos == null) {
				continue;
			}
			
			double specificSize = size.getComponent(axis);
			double thatSpecificSize = thatSize.getComponent(axis);
			
			if(
					specificPos >= thatSpecificPos + thatSpecificSize	// Left edge is right of object's right edge.
				||	specificPos + specificSize <= thatSpecificPos	// Right edge is left of object's left edge.
			) {
				return false;
			}
		}
		return true;
	}
	
	public boolean doesIntersectWith(Collidable that) {
		return doesIntersectWith(that.getCollisionPosition(), that.size);
	}

	public Vector getDirectionTo(Collidable that) {
		return that.position.minus(position);
	}
	
	private Vector getCollisionPosition() {
		return position.minus(offset);
	}
	
	public void drawCollisionMask(Graphics g) {
		int xPos = (int)(double) getCollisionPosition().getComponent(CoordinateAxis.x);
		int yPos = (int)(double) getCollisionPosition().getComponent(CoordinateAxis.y);
		int xSize = (int)(double) size.getComponent(CoordinateAxis.x);
		int ySize = (int)(double) size.getComponent(CoordinateAxis.y);
		g.drawRect(xPos, yPos, xSize - 1, ySize - 1); // -1 to counteract the +1 from Graphics.drawRect
	}
	
	public void fillCollisionMask(Graphics g) {
		int xPos = (int)(double) getCollisionPosition().getComponent(CoordinateAxis.x);
		int yPos = (int)(double) getCollisionPosition().getComponent(CoordinateAxis.y);
		int xSize = (int)(double) size.getComponent(CoordinateAxis.x);
		int ySize = (int)(double) size.getComponent(CoordinateAxis.y);
		g.fillRect(xPos, yPos, xSize, ySize);
	}
	
	public String toString() {
		double xPos = getCollisionPosition().getComponent(CoordinateAxis.x);
		double yPos = getCollisionPosition().getComponent(CoordinateAxis.y);
		double xSize = size.getComponent(CoordinateAxis.x);
		double ySize = size.getComponent(CoordinateAxis.y);
		return "[" + xPos + ", " + yPos + ", " + xSize + ", " + ySize + "]";
	}

	public boolean isA(String s) {
		return s == "Collidable";
	}
}
