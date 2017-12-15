package nguyen.connor.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

	private static final int NUM_BUTTONS = 10;
	
	private static final boolean[] buttons = new boolean[NUM_BUTTONS];
	private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
	private static int x = -1, y = -1;
	private static int lastX = x, lastY = y;
	private static boolean moving = false;
	
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		buttons[e.getButton()] = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		buttons[e.getButton()] = false;
	}
	
	public static void update() {
		for (int c = 0; c < NUM_BUTTONS; c++) {
			lastButtons[c] = buttons[c];
		}
		if (x == lastX && y == lastY) {
			moving = false;
		}
		lastX = x;
		lastY = y;
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		moving = true;
	}

	public static boolean isDown(int button) {
		return buttons[button];
	}
	
	public static boolean wasPressed(int button) {
		return buttons[button] && !lastButtons[button];
	}
	
	public static boolean wasReleased(int button) {
		return !isDown(button) && lastButtons[button];
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	private boolean isMoving() {
		return moving;
	}
}
