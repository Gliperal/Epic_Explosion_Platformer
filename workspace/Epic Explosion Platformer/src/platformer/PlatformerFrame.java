package platformer;

import java.awt.Graphics;

public class PlatformerFrame implements utilities.PackageControlObject {
	public void init() {}

	public void onUpdate() {
		GameControl.instance().onUpdate();
	}

	public void render(Graphics g) {
		GameControl.instance().render(g);
	}
}
