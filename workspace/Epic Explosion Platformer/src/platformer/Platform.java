package platformer;

import java.awt.Graphics;

import platformer.RenderableImage.ImageName;

public class Platform extends Collidable {
	public Platform(double posX, double posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY, true);
	}

	public void onUpdate() {}

	public void render(Graphics g) {
//		g.setColor(Color.BLACK);
//		drawCollisionMask(g);
		RenderControl.instance().addImageToRenderCue(RenderableImage.getImageOf(ImageName.platform, position), 0);
	}

	public void interactsWith(GameObject args) {}
}
