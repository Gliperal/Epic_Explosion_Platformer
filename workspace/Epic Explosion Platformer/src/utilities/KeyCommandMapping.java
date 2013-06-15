package utilities;

public class KeyCommandMapping {
	//* walkLeft */		private static Key left = new Key(37);
	//* walkRight */		private static Key right = new Key(39);
	//* jump */			private static Key up = new Key(38);
	//* 3 */				private static Key down = new Key(40);
	//* 4 */				private static Key space = new Key(32);
	/* walkForwards */	private static Key w = new Key(87);
	/* strafeLeft */	private static Key a = new Key(65);
	/* 7 */				private static Key s = new Key(83);
	/* strafeRight */	private static Key d = new Key(68);
	//* 9 */				private static Key backspace = new Key(8);
	
	/**
	 * 
	 * @param action Which action the key performs
	 * @return The key that is bound to a specific action
	 */
	public static Key getInputKey(KeyBasedCommand action) {
		switch(action) {
		case walkLeft:
			return a;
		case walkRight:
			return d;
		case jump:
			return w;
		case part:
			return s;
		default:
			return null;
		}
	}
	
	/**
	 * 
	 * @param key A key on the keyboard
	 * @return Which action the key performs
	 */
	public static KeyBasedCommand getCommandFromKey(Key key) {
		if(key.equals(a)) {
			return KeyBasedCommand.walkLeft;
		} else if(key.equals(d)) {
			return KeyBasedCommand.walkRight;
		} else if(key.equals(w)) {
			return KeyBasedCommand.jump;
		} else if(key.equals(s)) {
			return KeyBasedCommand.part;
		} else {
			return null;
		}
	}
		
	/**
	 * 
	 * @param action A key action
	 * @return The index of the action in the list of all actions
	 */
	public static int getKeyCommandIndex(KeyBasedCommand action) {
		switch(action) {
		case walkLeft:
			return 0;
		case walkRight:
			return 1;
		case jump:
			return 2;
		case part:
			return 3;
		default:
			return -1;
		}
	}
}
