package platformer;

import utilities.*;
import java.awt.Color;
import java.awt.Graphics;

public class Projectile_EnergyBow extends Projectile {
	public Projectile_EnergyBow(Vector position, Vector speed) {
		super(position, new Vector(10, 10), new Vector(5, 5), speed, new Vector(0, 0));
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		super.drawCollisionMask(g);
	}

	public void interactsWith(GameObject args) {}
	
	public boolean isA(String s) {
		if(super.isA(s)) {
			return true;
		}
		return s == "Friendly Projectile";
	}
}
