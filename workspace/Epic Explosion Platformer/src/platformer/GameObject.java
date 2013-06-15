package platformer;

import java.awt.Graphics;

public interface GameObject {
	public void onUpdate();
	public void render(Graphics g);
	public void interactsWith(GameObject args);
	public boolean isA(String identifier);
}
