package platformer;

import utilities.*;
import java.awt.Graphics;
import java.util.ArrayList;

public class Region extends Collidable {
	private ArrayList<Integer> platforms = new ArrayList<Integer>();

	public Region(Vector pos, Vector size, ArrayList<Integer> platformsInRegion) {
		super(pos, size, true);
		platforms = platformsInRegion;
	}
	
	public void addPlatform(int indexOfPlatformInMasterList) {
		platforms.add(indexOfPlatformInMasterList);
	}
	
	public void render(Graphics g) {
		fillCollisionMask(g);
	}
	
	public boolean containsSomeOf(Collidable that) {
		return doesIntersectWith(that);
	}

	public ArrayList<Integer> getPlatformIndexes() {
		return platforms;
	}

	public void onUpdate() {}
	public void interactsWith(GameObject args) {}
}
