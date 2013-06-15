package platformer;

import java.awt.*;
import utilities.*;
import java.util.ArrayList;

public class GameControl {
	private static GameControl instance;
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<GameObject> updatedGameObjects = new ArrayList<GameObject>();
	private boolean paused = false;
	private int pauseControllerIndex = -1;
	
	public static GameControl instance() {
		if(instance == null) {
			instance = new GameControl();
		}
		return instance;
	}

	private GameControl() {
		SaveSystem.loadMapFile(PublicVariables.getApplicationDirectory() + "Maps/Map1/");
		
		gameObjects.add(new Graig());
		
		/*
		gameObjects.add(new Enemy_Flyer(new Vector(0, 100)));
		gameObjects.add(new Enemy_Flyer(new Vector(-10, 80)));
		gameObjects.add(new Enemy_Flyer(new Vector(-20, 120)));
		gameObjects.add(new Enemy_Flyer(new Vector(-30, 60)));
		gameObjects.add(new Enemy_Flyer(new Vector(-40, 140)));
		*/
	}

	@SuppressWarnings("unchecked")
	public void onUpdate() {
		updatedGameObjects = (ArrayList<GameObject>) gameObjects.clone();
		
		updateObjects();

		UserInput.instance().onUpdate();
		
		gameObjects = (ArrayList<GameObject>) updatedGameObjects.clone();
	}
	
	private void updateObjects() {
		if(!paused) {
			for(GameObject gameObject : gameObjects) {
				gameObject.onUpdate();
			}
		} else {
			((PauseController)gameObjects.get(pauseControllerIndex)).paused_update();
		}
	}
	
	public void render(Graphics g) {
		RenderControl.instance().clearRenderCue();
		
		for(GameObject gameObject : gameObjects) {
			gameObject.render(g);
		}
		Platforms.instance().render(g);
		RenderControl.instance().render(g);
		
		if(paused) {
			((PauseController)gameObjects.get(pauseControllerIndex)).paused_render(g);
		}
	}
	
	/**
	 * Freezes the update and render cycles of all in-game objects, and gives complete control to one object's pause_update() and pause_render() commands. "entity" must be an object in the list of game objects or nothing will happen.
	 * 
	 * @param	entity	The object to take control until the game is unpaused
	 */
	public void requestGamePause(PauseController entity) {
		for(int index = 0; index < gameObjects.size(); index++) {
			if(gameObjects.get(index) == entity) {
				paused = true;
				pauseControllerIndex = index;
				break;
			}
		}
	}

	/**
	 * Unfreezes the game after a call to requestGamePause()
	 */
	public void resumeGame() {
		paused = false;
		pauseControllerIndex = -1;
	}

	public void addObject(GameObject object) {
		updatedGameObjects.add(object);
	}

	public void removeObject(GameObject object) {
		updatedGameObjects.remove(object);
	}
	
	public ArrayList<GameObject> getAll(String type) {
		ArrayList<GameObject> returnList = new ArrayList<GameObject>();
		for(GameObject object : gameObjects) {
			if(object.isA(type)) {
				returnList.add(object);
			}
		}
		return returnList;
	}
}
