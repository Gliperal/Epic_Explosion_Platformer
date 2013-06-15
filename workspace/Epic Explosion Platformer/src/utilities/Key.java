package utilities;

public class Key {
	private int keyIndexValue;
	
	public Key(int keyValue) {
		keyIndexValue = keyValue;
	}
	
	public boolean equals(Key k) {
		return keyIndexValue == k.keyIndexValue;
	}
}
