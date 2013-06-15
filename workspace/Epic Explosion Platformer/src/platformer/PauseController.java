package platformer;

import java.awt.Graphics;

public interface PauseController {
	public void paused_update();
	public void paused_render(Graphics g);
}
