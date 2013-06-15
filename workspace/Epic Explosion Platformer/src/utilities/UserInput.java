package utilities;

import static utilities.KeyCommandMapping.getCommandFromKey;
import static utilities.KeyCommandMapping.getKeyCommandIndex;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserInput {
	private static UserInput instance;
	private ArrayList<KeyState> keyStates = new ArrayList<KeyState>();
	private KeyState mouseButtonState;
	private int mouseAbsoluteX = 0, mouseAbsoluteY = 0;
	private int mouseMovedX = 0, mouseMovedY = 0;
	
	private UserInput() {
		keyStates = new ArrayList<KeyState>();
		for(int index = 0; index < 10; index++) {
			keyStates.add(index, KeyState.released);
		}
		
		mouseButtonState = KeyState.released;
	}
	
	public static UserInput instance() {
		if(instance == null) {
			instance = new UserInput();
		}
		
		return instance;
	}
	
//	public void setPnelCooridnates(Point p) {
//		screenPosition = p;
//	}
	
	public void onUpdate() {
		mouseMovedX = 0;
		mouseMovedY = 0;

		for(int index = 0; index < keyStates.size(); index++) {
			KeyState key = keyStates.get(index);
			switch(key) {
				case justPressed:	keyStates.set(index, KeyState.pressed); break;
				case justReleased:	keyStates.set(index, KeyState.released); break;
				default:break;
			}
		}

		switch(mouseButtonState) {
			case justPressed:	mouseButtonState = KeyState.pressed; break;
			case justReleased:	mouseButtonState = KeyState.released; break;
			default:break;
		}
	}

	@SuppressWarnings("unused")
	public void updateMouse(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		mouseMovedX += mouseX - mouseAbsoluteX;
		if(true) {
			mouseMovedY -= mouseY - mouseAbsoluteY;
		} else {
			mouseMovedY += mouseY - mouseAbsoluteY;
		}
		
		mouseAbsoluteX = mouseX;
		mouseAbsoluteY = mouseY;
	}
	
	public void moveMouse(int moveToX, int moveToY) {
		mouseAbsoluteX = moveToX;// - screenPosition.x;
		mouseAbsoluteY = moveToY;// - screenPosition.y;
	}
	
	public void userMouseClicked() {
		if(mouseButtonState == KeyState.released) {
			mouseButtonState = KeyState.justPressed;
		}
	}
	
	public void userMouseReleased() {
		if(mouseButtonState == KeyState.pressed) {
			mouseButtonState = KeyState.justReleased;
		}
	}
	
	public void userPressed(int keyCode) {
		int index = getKeyCommandIndex(getCommandFromKey(new Key(keyCode)));
		KeyState key = keyStates.get(index);
		switch(key) {
			case released:	keyStates.set(index, KeyState.justPressed); break;
			default: break;
		}
	}
	
	public void userReleased(int keyCode) {
		int index = getKeyCommandIndex(getCommandFromKey(new Key(keyCode)));
		KeyState key = keyStates.get(index);
		switch(key) {
			case pressed:	keyStates.set(index, KeyState.justReleased); break;
			default: break;
		}
	}
	
	public boolean actionKeyPressed(KeyBasedCommand command) {
		KeyState state = keyStates.get(KeyCommandMapping.getKeyCommandIndex(command));
		return state == KeyState.justPressed;
	}
	
	public boolean actionKeyDown(KeyBasedCommand command) {
		KeyState state = keyStates.get(KeyCommandMapping.getKeyCommandIndex(command));
		switch(state) {
		case justPressed:
		case pressed:
			return true;
		default:
			return false;
		}
	}
	
	public boolean mouseClicked() {
		return mouseButtonState == KeyState.justPressed;
	}
	
	public boolean mouseDown() {
		switch(mouseButtonState) {
		case justPressed:
		case pressed:
			return true;
		default:
			return false;
		}
	}
	
	public Vector getMousePosition() {
		return new Vector(mouseAbsoluteX, mouseAbsoluteY);
	}
	
	public Vector getMouseMovement() {
		return new Vector(mouseMovedX, mouseMovedY);
	}
}
