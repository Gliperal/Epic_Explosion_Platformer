package platformer;

import java.awt.Graphics;

public class Part_Offense_Melee_Sword extends Part_Offense_Melee {
	public Part_Offense_Melee_Sword() {
		super(
			new CollidableArea[]{
				new CollidableArea(null, null, null),
				new CollidableArea(null, null, null),
				new CollidableArea(null, null, null)
			}
			,
			new CollidableArea(null, null, null)
		);
	}

	public void onUpdate() {
		super.onUpdate();
	}

	public void render(Graphics g) {
		// TODO
	}
}