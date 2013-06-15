package platformer;

import utilities.*;

public abstract class PhysicsObject extends Collidable {
	private int dimension = 2;
	private final double movementStepSize = .1;
	protected Vector speed = new Vector(0, 0);
	private boolean standingOnGround = false;
	
	public PhysicsObject(Vector pos, Vector dimensions, Vector off) {
		super(pos, dimensions, off);
	}
	
	public void onUpdate() {
		if(speed.getComponent(CoordinateAxis.y) > speedCap_vert) {
			speed.setComponent(CoordinateAxis.y, speedCap_vert);
		}
		
		for(CoordinateAxis axis : new CoordinateAxis[]{CoordinateAxis.x}) {
			boolean hitAWall = move(speed.getComponent(axis), axis);
			if(hitAWall) {
				speed.setComponent(axis, 0.0);
			}
		}

		standingOnGround = false;
		double ySpeed = speed.getComponent(CoordinateAxis.y);
		boolean hitAWall = move(ySpeed, CoordinateAxis.y);
		if(hitAWall) {
			if(ySpeed >= 0) {
				standingOnGround = true;
			}
			speed.setComponent(CoordinateAxis.y, 0.0);
		}
	}
	
	private boolean move(double distance, CoordinateAxis axis) {
		int movingDirection = distance < 0 ? -1 : 1;

		Vector movementStep = Vector.createVectorOfSize(dimension);
		movementStep.setComponent(axis, movingDirection*movementStepSize);

		double targetPosition = position.getComponent(axis) + distance;
		while( (targetPosition - position.getComponent(axis)) * movingDirection > movementStepSize ) {
			try {
				moveCollide(movementStep);
			} catch(CollisionException e) {
				return true;
			}
		}
		return false;
	}

	private void moveCollide(Vector displacement) throws CollisionException {
		Vector formerPosition = position;
		position = position.plus(displacement);
		
		if(Platforms.instance().doesIntersectWith(this)) {
			position = formerPosition;
			throw new CollisionException();
		}
	}
	
	public boolean isStandingOnGround() {
		return standingOnGround;
	}
}
