package platformer;

import utilities.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import platformer.RenderableImage.ImageName;

public class Enemy_Flyer extends Collidable {
	private Vector imageOffset = new Vector(5, 8);
	private int animationFrame = 0;
	private Vector flySpeed = new Vector(1, 0);
	
	public Enemy_Flyer(Vector position) {
		super(position, new Vector(6, 8), new Vector(3, 4));
	}

	public void onUpdate() {
		animationFrame++;
		if(animationFrame > 9 + (new Random()).nextInt(3)) animationFrame = 0;
		
		Player player = (Player) GameControl.instance().getAll("Player").get(0);
		double playerX = player.position.getComponent(CoordinateAxis.x);
		double thisX = position.getComponent(CoordinateAxis.x);
		if(Math.abs(playerX - thisX) < 10) {
			// TODO SMASH
		} else if(playerX > thisX) {
			position = position.plus(flySpeed);
		} else {
			position = position.minus(flySpeed);
		}
		
		ArrayList<GameObject> projectiles = GameControl.instance().getAll("Friendly Projectile");
		for(GameObject object : projectiles) {
			Projectile projectile = (Projectile)object;
			if(projectile.doesIntersectWith(this)) {
				GameControl.instance().removeObject(this);
			}
		}
	}

	public void render(Graphics g) {
		ImageName currentAnimationFrame = ImageName.enemy_flyer1;
		if(animationFrame > 5) currentAnimationFrame = ImageName.enemy_flyer2;
		RenderControl.instance().addImageToRenderCue(RenderableImage.getImageOf(currentAnimationFrame, position.minus(imageOffset)), 0);
	}

	public void interactsWith(GameObject args) {}
}
