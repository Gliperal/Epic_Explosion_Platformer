package platformer;

import java.awt.Graphics;
import utilities.*;

public class CollidableArea extends Collidable {
	public CollidableArea(Vector position, Vector dimensions, Vector offset) {
		super(position, dimensions, offset);
	}

	public void onUpdate() {}
	public void render(Graphics g) {}
	public void interactsWith(GameObject args) {}
}
