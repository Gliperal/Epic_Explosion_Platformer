package platformer;

import utilities.*;

import java.awt.Graphics;
import java.util.ArrayList;

public class Platforms {
	private static Platforms instance = null;
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private Platform[][] platformsGrid;
	private ArrayList<Region> screenRegions;
	
	public static Platforms instance() {
		if(instance == null) {
			instance = new Platforms();
		}
		return instance;
	}
	
	private Platforms() {
		/*
		screenRegions = new ArrayList<Region>();
		int regionWidth = 20;
		int regionHeight = 20;
		for(int x = 0; x < Frame.SCREEN_WIDTH; x += regionWidth) {
			for(int y = 0; y < Frame.SCREEN_HEIGHT; y += regionWidth) {
				screenRegions.add(new Region(new Vector(x, y), new Vector(regionWidth, regionHeight), new ArrayList<Integer>()));
			}
		}
		*/
		platformsGrid = new Platform[PublicVariables.mapHeight][PublicVariables.mapWidth];
	}
	
	@Deprecated
	public void addPlatform(Platform p) {
		platforms.add(p);
		int indexOfP = platforms.indexOf(p);
		for(Region r : screenRegions) {
			if(r.containsSomeOf(p)) {
				r.addPlatform(indexOfP);
			}
		}
	}
	
	public void addPlatform(Platform p, int xGrid, int yGrid) {
		Printer.println(platformsGrid.length + ", " + platformsGrid[0].length);
		platformsGrid[yGrid][xGrid] = p;
	}
	
	public void clearPlatforms() {
		platforms = new ArrayList<Platform>();
	}
	
	public void render(Graphics g) {
		/*
		for(Platform p : platforms) {
			p.render(g);
		}
		*/
		for(Platform[] plats : platformsGrid) {
			for(Platform plat : plats) {
				if(plat != null) plat.render(g);
			}
		}
	}
	
	public boolean doesIntersectWith(Collidable that) {
		/*
		for(Region objectRegion : screenRegions) {
			if(objectRegion.containsSomeOf(that)) {
				ArrayList<Integer> listOfPlatformsNearThat = objectRegion.getPlatformIndexes();
				for(int platformIndex : listOfPlatformsNearThat) {
					if(platforms.get(platformIndex).doesIntersectWith(that)) {
						return true;
					}
				}
			}
		}
		*/
		/*
		for(Platform platform : platforms) {
			if(platform.doesIntersectWith(that)) {
				return true;
			}
		}
		*/
		ArrayList<Platform> platsToTest = platformsCloseToCollidble(that);
		for(Platform platform : platsToTest) {
			if(platform.doesIntersectWith(that)) {
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Platform> platformsCloseToCollidble(Collidable that) {
		int mapScale = PublicVariables.mapScale;
		ArrayList<Platform> returnList = new ArrayList<Platform>();
		
		Vector thatCollisionPosition = that.position.minus(that.offset);
		Vector thatOtherCorner = thatCollisionPosition.plus(that.size);

		double thatLeft = thatCollisionPosition.getComponent(CoordinateAxis.x);
		double thatTop = thatCollisionPosition.getComponent(CoordinateAxis.y);
		double thatRight = thatOtherCorner.getComponent(CoordinateAxis.x);
		double thatBottom = thatOtherCorner.getComponent(CoordinateAxis.y);

		int left = (int) (thatLeft/mapScale);
		int top = (int) (thatTop/mapScale);
		int right = (int) (thatRight/mapScale);
		int bottom = (int) (thatBottom/mapScale);
		
		for(int x = left; x <= right; x++) {
			for(int y = top; y <= bottom; y++) {
				Platform gridObjectCloseToCollidable = platformsGrid[y][x];
				if(gridObjectCloseToCollidable != null) {
					returnList.add(gridObjectCloseToCollidable);
				}
			}
		}
		
		return returnList;
	}
}
