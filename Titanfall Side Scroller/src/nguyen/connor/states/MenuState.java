package nguyen.connor.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import nguyen.connor.Game;
import nguyen.connor.input.KeyInput;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.UI.Button;
import nguyen.connor.utils.Fonts;

public class MenuState implements State {

	private Button[] options;
	private int currentSelection;

	public void init() {
		options = new Button[3];
		options[0] = new Button("Play", 300 + 0 * 120, Color.BLACK, new Font("Arial", Font.PLAIN, 72), Color.RED, new Font("Arial", Font.BOLD, 72));
		options[1] = new Button("Options", 300 + 1 * 120, Color.BLACK, new Font("Arial", Font.PLAIN, 72), Color.RED, new Font("Arial", Font.BOLD, 72));
		options[2] = new Button("Exit", 300 + 2 * 120, Color.BLACK, new Font("Arial", Font.PLAIN, 72), Color.RED, new Font("Arial", Font.BOLD, 72));		
	}


	public void enter() {
		
	}

	public void tick(StateManager stateManager) {
		if (KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)) {
			currentSelection--;
			if (currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}
		if (KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)) {
			currentSelection++;
			if (currentSelection > options.length - 1) {
				currentSelection = 0;
			}
		}

		boolean clicked = false;
		for (int c = 0; c < options.length; c++) {
			if (options[c].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
				currentSelection = c;
				clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
			}
		}

		if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
			select(stateManager);
		}
	}

	private void select(StateManager stateManager) {
		switch (currentSelection) {
		case 0: stateManager.setState("level1");
		break;
		case 1: System.out.println("Options");
		break;
		case 2: System.out.println("Exit");
		Game.INSTANCE.stop();
		break;
		}
	}

	public void render(Graphics2D brush) {
		
		Fonts.drawString(brush, new Font("Arial", Font.BOLD, 72), Color.RED, Game.TITLE, 120);

		for (int c = 0; c < options.length; c++) {
			if (c == currentSelection) {
				options[c].setSelected(true);
			} else {
				options[c].setSelected(false);
			}
			options[c].render(brush);
		}
	}

	public void exit() {
		
	}

	public String getName() {
		return "menu";
	}
	
}
