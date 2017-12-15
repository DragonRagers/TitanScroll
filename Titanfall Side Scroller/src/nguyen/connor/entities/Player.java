package nguyen.connor.entities;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import nguyen.connor.input.KeyInput;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class Player extends Mob {

	protected boolean canDoubleJump = true;
	protected ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
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
		if (KeyInput.isDown(KeyEvent.VK_D)) {
			dx = 3;
		}
		if (KeyInput.isDown(KeyEvent.VK_A)) {
			dx = -3;
		} 
		
		if (KeyInput.wasReleased(KeyEvent.VK_D) || KeyInput.wasReleased(KeyEvent.VK_A)) {
			dx = 0;
		}
		if (MouseInput.wasPressed(MouseEvent.BUTTON1)) {
			shoot();
		}
		super.tick();
	}

	protected void doubleJump(double height) {
		if (!canJump && canDoubleJump && falling) {
			dy = -height;
			canDoubleJump = false;
		}
	}
	
	protected void shoot() {
		MouseInput.update();
		double angle = Math.atan2(y - MouseInput.getY(), x - MouseInput.getX());
		bullets.add(new Bullet(new Sprite("player bullet"), x + sprite.getWidth(), y + sprite.getHeight()/4, angle, 40, state));
	
	}
	
}
