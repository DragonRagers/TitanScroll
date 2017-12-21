package nguyen.connor.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import nguyen.connor.input.KeyInput;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;
import nguyen.connor.weapons.Pistol;
import nguyen.connor.weapons.Weapon;

public class Player extends Mob {

	protected boolean canDoubleJump = true;
	protected Weapon curWeapon;
	
	public Player(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
		curWeapon = new Pistol(this, state);
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
		if (curWeapon.getType() == 0 && MouseInput.wasPressed(MouseEvent.BUTTON1)) {
			curWeapon.shoot();
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
