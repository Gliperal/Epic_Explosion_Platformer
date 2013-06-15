package platformer;

import utilities.*;
import java.awt.Color;
import java.awt.Graphics;

public class Projectile_Missile extends Projectile {
	private double speed;
	private double angle;
	private double turningSpeed = 0.3;
	
	public Projectile_Missile(Vector position, double speed, double startingAngle) {
		super(position, new Vector(10, 10), new Vector(5, 5), new Vector(speed, 0), new Vector(0, 0));
		this.speed = speed;
		angle = startingAngle;
	}
	
	public void onUpdate() {
		super.onUpdate();
		Vector directionToTarget = getHomingPosition().minus(position);
		
		if(directionToTarget.magnitude() < 10) explode();
		
		double
			dTT_x = directionToTarget.getComponent(CoordinateAxis.x),
			dTT_y = directionToTarget.getComponent(CoordinateAxis.y);
		double desiredAngle = Math.atan2(dTT_y, dTT_x);
		double TwoPI = (2*Math.PI);
		double angleDiff = (TwoPI + desiredAngle - angle)%TwoPI;
		if(angleDiff <= turningSpeed) {
			angle = desiredAngle;
		} else {
			if(angleDiff < Math.PI) {
				angle += turningSpeed;
			} else {
				angle -= turningSpeed;
			}
		}
		super.speed = new Vector(speed*Math.cos(angle), speed*Math.sin(angle));

		if(Platforms.instance().doesIntersectWith(this)) explode();
	}
	
	private Vector getHomingPosition() {
		return UserInput.instance().getMousePosition();
	}

	public void render(Graphics g) {
		g.setColor(Color.blue.darker());
		super.drawCollisionMask(g);
	}

	public void interactsWith(GameObject args) {}
	
	public void explode() {
		GameControl.instance().removeObject(this);
	}
	
	public boolean isA(String s) {
		if(super.isA(s)) {
			return true;
		}
		return s == "Friendly Projectile";
	}
}
