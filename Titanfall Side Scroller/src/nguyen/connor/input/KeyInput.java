package nguyen.connor.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private static final int NUM_KEYS = 256;
	
	private static final boolean[] keys = new boolean[NUM_KEYS];
	private static final boolean[] lastKeys = new boolean[NUM_KEYS];
	
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		super.keyPressed(e);
		keys[e.getKeyCode()] = false;
	}
	
	public static void update() {
		for (int c = 0; c < NUM_KEYS; c++) {
			lastKeys[c] = keys[c];
		}
	}
	
	public static boolean isDown(int key) {
		return keys[key];
	}
	
	public static boolean wasPressed(int keyCode) {
		return isDown(keyCode) && !lastKeys[keyCode];
	}

	public static boolean wasReleased(int keyCode) {
		return !isDown(keyCode) && lastKeys[keyCode];
	}
}
