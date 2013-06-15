package platformer;

import utilities.*;
public abstract class Part_Offense extends Part {

	public Part_Offense(Vector position) {
		super(position, Part.PartType.offensive);
	}

	public void attack() {}
}
