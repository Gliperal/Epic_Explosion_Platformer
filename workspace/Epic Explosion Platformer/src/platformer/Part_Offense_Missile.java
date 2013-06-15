package platformer;

import utilities.*;
import java.awt.Color;
import java.awt.Graphics;

public class Part_Offense_Missile extends Part_Offense {
	private final int attackCooldownTime = 50;
	private int attackCooldown = 0;
	
	public Part_Offense_Missile(Vector position) {
		super(position);
		size = new Vector(10, 3);
		offset = new Vector(2, 4);
	}
	
	public void attack() {
		if(attackCooldown == 0) {
			GameControl.instance().addObject(new Projectile_Missile(position.minus(offset), 10.0, 0.0));
			attackCooldown = attackCooldownTime;
		}
	}
	
	public void onUpdate() {
		if(attackCooldown > 0) {
			attackCooldown--;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		drawCollisionMask(g);
	}
	
	public void interactsWith(GameObject args) {}
}