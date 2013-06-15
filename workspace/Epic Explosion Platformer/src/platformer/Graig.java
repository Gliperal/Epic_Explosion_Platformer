package platformer;

import utilities.*;

import java.awt.Color;
import java.awt.Graphics;

import platformer.RenderableImage.ImageName;

public class Graig extends Player implements PauseController {
	private Vector imageOffset = new Vector(5, 10);
	private Part_Offense[] offensiveParts = {null, null};
	private Part[] defensiveParts = {null, null};
	private Part[] auxiliaryParts = {null, null, null, null, null};
	
	public Graig() {
		super(new Vector(-50, -50), new Vector(8, 15));
		// TODO import save location
		super.position = new Vector(500, 240);
	}

	public void onUpdate() {
		super.onUpdate();
		
		if(UserInput.instance().actionKeyPressed(KeyBasedCommand.part)) {
			Part nearbyPart = getNearbyPart();
			if(nearbyPart != null) {
				addPart(nearbyPart);
				GameControl.instance().requestGamePause(this);
			}
		}
		
		for(Part p : offensiveParts) {
			if(p != null) {
				p.onUpdate();
				p.updatePosition(position);
			}
		}
		
		if(UserInput.instance().mouseClicked()) {
			for(Part_Offense p : offensiveParts) {
				if(p != null) p.attack();
			}
		}
		
		Vector halfScreen = new Vector(Frame.SCREEN_WIDTH/2, Frame.SCREEN_HEIGHT/2);
		RenderControl.instance().setCameraPosition(position.minus(halfScreen).toPoint());
	}
	
	private Part getNearbyPart() {
		return new Part_Offense_Missile(new Vector(100, 100));
	}
	
	private void addPart(Part foobar) {
		// Specify to which set the new part is being added...
		Part[] listToEdit;

		// ...based off what kind of part it is.
		switch(foobar.getType()) {
		case offensive:
			listToEdit = offensiveParts;
			break;
		case defensive:
			listToEdit = defensiveParts;
			break;
		case auxillary:
			listToEdit = auxiliaryParts;
			break;
		default:
			return;
		}
		
		boolean successfullyAdded = false;
		for(int index = 0; index < listToEdit.length; index++) {
			// If there is an empty slot, place it there.
			if(listToEdit[index] == null) {
				listToEdit[index] = foobar;
				successfullyAdded = true;
				break;
			}
		}

		// If there is no empty slot...
		if(!successfullyAdded) {
			// ...pause the game, and ask the user for an item to replace.
			GameControl.instance().requestGamePause(this);
		}
	}
	
	public void render(Graphics g) {
		RenderControl.instance().addImageToRenderCue(RenderableImage.getImageOf(ImageName.graig, position.minus(imageOffset)), 0);
		
		for(Part p : offensiveParts) {
			if(p != null) p.render(g);
		}
	}
	
	public void paused_update() {
		if(UserInput.instance().actionKeyPressed(KeyBasedCommand.part)) {
			GameControl.instance().resumeGame();
		}
	}
	
	public void paused_render(Graphics g) {
		Part[][] allParts = {offensiveParts, defensiveParts, auxiliaryParts};
		int yOffset = 100;
		for(Part[] partsList : allParts) {			// For each individual part list
			int xOffset = 100;						// Reset the leftmost box
			for(Part part : partsList) {			// Render the box for the specific part:
				g.setColor(Color.BLACK);
				g.fillRect(xOffset, yOffset, 30, 30);		// Rendering the background box
				if(part == null) {							// For empty parts...
					g.setColor(Color.WHITE);
					g.fillRect(xOffset+3, yOffset+3, 24, 24);	// ...show an empty box.
				} else {
					part.render(g, xOffset+3, yOffset+3);
				}
				
				xOffset += 50;						// Increment the x position for each item in a list
			}
			yOffset += 50;							// Increment the y position for each parts list
		}
	}

	public boolean isA(String s) {
		return super.isA(s);
	}
}