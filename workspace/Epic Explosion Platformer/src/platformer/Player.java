package platformer;

import utilities.*;

public abstract class Player extends PhysicsObject {
	private final double jumpSpeed = 7.;
	private final double gravityMagnitude = .4;
	private final Vector walkAcceleration = new Vector(1, 0)
						, gravity = new Vector(0, gravityMagnitude);;
	private final double walkDeacceleration = .3;
	private final double speedCap_horiz = 1.5, speedCap_vert = 5.0;
	
	private final int maxJumps = 3;
	private int jumpsLeft = maxJumps - 1;	// If the Player spawns in the air, let them have their remaining jumps.
	
	public Player(Vector pos, Vector hitBox) {
		super(pos, hitBox, hitBox.times(0.5));
	}

	public void onUpdate() {
		speed = speed.plus(gravity);

		boolean leftDown = UserInput.instance().actionKeyDown(KeyBasedCommand.walkLeft);
		boolean rightDown = UserInput.instance().actionKeyDown(KeyBasedCommand.walkRight);
		if(leftDown) {
			if(speed.getComponent(CoordinateAxis.x) > -speedCap_horiz) {
				speed = speed.minus(walkAcceleration);
			}
		}
		if(rightDown) {
			if(speed.getComponent(CoordinateAxis.x) < speedCap_horiz) {
				speed = speed.plus(walkAcceleration);
			}
		}
		if(!leftDown && !rightDown) {
			double xSpeed = speed.getComponent(CoordinateAxis.x);
			xSpeed = Util.bringTowardsValue(xSpeed, walkDeacceleration, 0);
			speed.setComponent(CoordinateAxis.x, xSpeed);
		}
		
		if(isStandingOnGround()) jumpsLeft = maxJumps;
		
		if(UserInput.instance().actionKeyPressed(KeyBasedCommand.jump)) {
			if(jumpsLeft > 0) {
				speed.setComponent(CoordinateAxis.y, -jumpSpeed);
				jumpsLeft--;
			}
		}
		
		super.onUpdate();
	}

	public void interactsWith(GameObject args) {}

	public boolean isA(String s) {
		return s == "Player";
	}
}
