package nguyen.connor.entities;

import java.awt.event.KeyEvent;

import nguyen.connor.input.KeyInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class Player extends Mob {

	protected boolean canDoubleJump = true;
	
	public Player(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
	}
	
	public void tick() {
		hasVerticalCollision();
		if (canJump) {
			canDoubleJump = true;
		}
		if (KeyInput.wasPressed(KeyEvent.VK_W) || KeyInput.wasPressed(KeyEvent.VK_SPACE)) {
			doubleJump(8);
			jump(10);
		}
		/**
		if (KeyInput.isDown(KeyEvent.VK_S)) {
			dy = 2;
		}
	    **/
		if (KeyInput.isDown(KeyEvent.VK_D)) {
			dx = 3;
		}
		if (KeyInput.isDown(KeyEvent.VK_A)) {
			dx = -3;
		}
		
		/**
		if (KeyInput.wasReleased(KeyEvent.VK_W) || KeyInput.wasReleased(KeyEvent.VK_S)) {
			dy = 0;
		}
		**/ 
		
		if (KeyInput.wasReleased(KeyEvent.VK_D) || KeyInput.wasReleased(KeyEvent.VK_A)) {
			dx = 0;
		}
		
		super.tick();
	}

	protected void doubleJump(double height) {
		if (!canJump && canDoubleJump && falling) {
			dy = -height;
			canDoubleJump = false;
		}
	}
	
}
