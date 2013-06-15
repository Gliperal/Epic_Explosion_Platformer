package platformer;

import utilities.*;
import java.awt.Graphics;

public abstract class Part extends Collidable {
	public enum PartType { offensive, defensive, auxillary };
	private PartType type;

	public Part(Vector position, PartType typeOfPart) {
		super(position, new Vector(10, 10));
		type = typeOfPart;
	}

	public PartType getType() {
		return type;
	}

	public void updatePosition(Vector position) {
		super.position = position;
	}
	public void render(Graphics g, int xOffset, int yOffset) {}
}
