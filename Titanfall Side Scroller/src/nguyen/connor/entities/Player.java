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
import nguyen.connor.weapons.Car;
import nguyen.connor.weapons.Pistol;
import nguyen.connor.weapons.Weapon;

public class Player extends Mob {

	protected boolean canDoubleJump = true;
	protected Weapon curWeapon, secWeapon;
	
	public Player(double x, double y, GameState state) {
		super(new Sprite("pilot"), x, y, state);
		maxDy = 7;
		curWeapon = new Car(this, state);
		secWeapon = new Pistol(this, state);
		enemy = false;
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
		if (curWeapon.getType() == 0 && MouseInput.wasPressed(MouseEvent.BUTTON1)) { //pew pew
			curWeapon.shoot();
		}
		if (curWeapon.getType() == 1 && MouseInput.isDown(MouseEvent.BUTTON1)) { //automatic
			curWeapon.shoot();
		}
		if (KeyInput.wasPressed(KeyEvent.VK_E)) {
			weapSwitch();
		}
		super.tick();
	}

	protected void weapSwitch() {
		Weapon temp = curWeapon;
		curWeapon = secWeapon;
		secWeapon = temp;
	}
	
	protected void doubleJump(double height) {
		if (!canJump && canDoubleJump && falling) {
			dy = -height;
			canDoubleJump = false;
		}
	}
	
}
