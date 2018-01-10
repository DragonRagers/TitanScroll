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
import nguyen.connor.weapons.Smg;
import nguyen.connor.weapons.Pistol;
import nguyen.connor.weapons.Weapon;

public class Player extends Mob {

	protected boolean canDoubleJump = true;
	protected Weapon curWeapon, secWeapon;
	
	public Player(double x, double y, GameState state) {
		super(new Sprite("pilotR"), x, y, state);
		direction = 0;
		sprites.add(new Sprite("pilotL"));
		maxDy = 7;
		curWeapon = new Smg(this, state);
		secWeapon = new Pistol(this, state);
		secWeapon.equipped = false;
		enemy = false;
	}
	
	@Override
	public void render(Graphics2D brush) {
		if (direction == 0) {
			spriteTo(0);
		} else if (direction == 1) {
			spriteTo(1);;
		}
		super.render(brush);
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
			direction = 0;
		}
		if (KeyInput.isDown(KeyEvent.VK_A)) {
			dx = -3;
			direction = 1;
		} 

		if (KeyInput.wasReleased(KeyEvent.VK_D) || KeyInput.wasReleased(KeyEvent.VK_A)) {
			dx = 0;
		}
		if (curWeapon != null && curWeapon.getType() == 0 && MouseInput.wasPressed(MouseEvent.BUTTON1)) { //pew pew
			curWeapon.shoot();
		}
		if (curWeapon != null && curWeapon.getType() == 1 && MouseInput.isDown(MouseEvent.BUTTON1)) { //automatic
			curWeapon.shoot();
		}
		if (KeyInput.wasPressed(KeyEvent.VK_Q)) {
			weapSwitch();
		}
		if (KeyInput.wasPressed(KeyEvent.VK_E)) {
			for (Entity e : state.getEntites()) {
				if (e instanceof Weapon && !e.equals(curWeapon) && !e.equals(secWeapon) && getBounds().intersects(e.getBounds())) {
					if (curWeapon == null) {
						curWeapon = (Weapon) e;
						curWeapon.holder = this;
						break;
					} else if (secWeapon == null) {
						secWeapon = (Weapon) e;
						weapSwitch();
						curWeapon.holder = this;
						break;
					} else {
						curWeapon.drop();
						curWeapon = (Weapon) e;
						curWeapon.holder = this;
						break;
					}
				}
			}
		}

		if (KeyInput.wasPressed(KeyEvent.VK_F)) {
			if (curWeapon != null) {
				curWeapon.drop();
				if (secWeapon != null) {
					curWeapon = secWeapon;
					curWeapon.equipped = true;
					secWeapon = null;
				} else {
					curWeapon = null;
				}
			}
		}
		super.tick();
	}

	protected void weapSwitch() {
		if (secWeapon == null) {
			return;
		}
		Weapon temp = curWeapon;
		curWeapon = secWeapon;
		secWeapon = temp;
		curWeapon.equipped = true;
		secWeapon.equipped = false;
	}

	protected void doubleJump(double height) {
		if (!canJump && canDoubleJump && falling) {
			dy = -height;
			canDoubleJump = false;
		}
	}
}
