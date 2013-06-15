package platformer;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RenderControl {
	private static RenderControl instance;
	
	private Point cameraPosition = new Point(0, 0);
	@SuppressWarnings("rawtypes")
	private ArrayList[] renderCue = new ArrayList[3];
	
	public static RenderControl instance() {
		if(instance == null) {
			instance = new RenderControl();
		}
		return instance;
	}
	
	private RenderControl() {
		clearRenderCue();
	}
	
	@SuppressWarnings("unchecked")
	public void addImageToRenderCue(RenderableImage image, int layer) {
		renderCue[layer].add(image);
	}
	
	public void clearRenderCue() {
		for(int layer = 0; layer < renderCue.length; layer++) {
			renderCue[layer] = new ArrayList<RenderableImage>();
		}
	}
	
	public void setCameraPosition(Point position) {
		cameraPosition = position;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void render(Graphics g) {
		for(ArrayList layer : renderCue) {
			ArrayList<RenderableImage> rLayer = (ArrayList<RenderableImage>) layer;
			for(RenderableImage image : rLayer) {
				image.render(g, cameraPosition.x, cameraPosition.y);
			}
		}
	}
}
