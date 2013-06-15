package platformer;

import utilities.*;
import java.awt.Color;
import java.awt.Graphics;

public class Part_Offense_EnergyBow extends Part_Offense {
	public Part_Offense_EnergyBow(Vector position) {
		super(position);
	}
	
	public void attack() {
		GameControl.instance().addObject(new Projectile_EnergyBow(position, new Vector(5, 3)));
	}
	
	public void onUpdate() {}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		drawCollisionMask(g);
	}
	
	public void interactsWith(GameObject args) {}
}