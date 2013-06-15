package platformer;

import utilities.*;

public abstract class Projectile extends Collidable {
	protected Vector speed, acceleration;

	public Projectile(Vector position, Vector size, Vector offset, Vector velocity, Vector acc) {
		super(position, size, offset);
		speed = velocity;
		acceleration = acc;
	}

	public void onUpdate() {
		speed = speed.plus(acceleration);
		position = position.plus(speed);
	}
	
	public boolean isA(String s) {
		return s == "Projectile";
	}
}