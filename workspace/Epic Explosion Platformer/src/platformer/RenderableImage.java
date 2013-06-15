package platformer;

import utilities.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RenderableImage {
	private static Image[] imageCache;
	private static ImageName[] listOfImagesStoredInCache;
	public enum ImageName {
		enemy_flyer1
	,	enemy_flyer2
	,	graig
	,	platform
	}
	
	private void loadImages(ImageName[] imagesToLoad) {
		int imagesStoredInCache = imagesToLoad.length;
		listOfImagesStoredInCache = new ImageName[imagesStoredInCache];
		imageCache = new Image[imagesStoredInCache];
		int index = 0;
		for(ImageName imageTag : imagesToLoad) {
			String imageFilename = PublicVariables.getApplicationDirectory() + "Sprites/" + imageTag + ".png";
			File imageFile = new File(imageFilename);
			try {
				listOfImagesStoredInCache[index] = imageTag;
				imageCache[index] = ImageIO.read(imageFile);
			} catch (IOException e) {
				ErrorLog.addError("File error: " + imageFilename);
				e.printStackTrace();
				continue;
			}
			index++;
		}
	}
	
	private int imageID;
	private Point position;
	
	private RenderableImage(ImageName i, Point pos) {
		position = pos;
		
		if(imageCache == null) loadImages(new ImageName[]{
				ImageName.enemy_flyer1
			,	ImageName.enemy_flyer2
			,	ImageName.graig
			,	ImageName.platform
		});
		
		for(int index = 0; index < listOfImagesStoredInCache.length; index++) {
			ImageName imageTag = listOfImagesStoredInCache[index];
			if(imageTag == i) {
				imageID = index;
				return;
			}
		}
		imageID = -1;
	}
	
	public static RenderableImage getImageOf(ImageName image, Vector pos) {
		double	posX = pos.getComponent(CoordinateAxis.x)
			,	posY = pos.getComponent(CoordinateAxis.y);
		return new RenderableImage(image, new Point((int)posX, (int)posY));
	}
	
	public void render(Graphics g, int offsetX, int offsetY) {
		if(imageID == -1) return;
		g.drawImage(imageCache[imageID], position.x - offsetX, position.y - offsetY, null);
	}
}
