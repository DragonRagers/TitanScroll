package nguyen.connor.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;

import nguyen.connor.entities.Bullet;
import nguyen.connor.entities.Entity;
import nguyen.connor.entities.Mob;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public abstract class Weapon extends Mob {

	public Weapon(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
	}

	protected int fireRate;
	protected boolean canFire;
	protected double bulletSpeed;
	protected int tickCount;
	protected Sprite bulletSprite;
	protected Mob holder;
	protected double aimX, aimY;
	protected GameState state;
	protected int type;
	protected double dmg;
	
	public void render(Graphics brush) {
		super.render((Graphics2D) brush); 
	}
	
	public void tick() {
		if (!canFire) {
			tickCount++;
		} 
		if (tickCount >= fireRate) {
			canFire = true;
			tickCount = 0;
		}
		aimX = MouseInput.getX();
		aimY = MouseInput.getY();
		move();
	}
	
	public void move() {
		if (holder != null) {
			x = holder.getX();
			y = holder.getY();
		}
	}
	
	public boolean shoot() {
		if (canFire) {
			canFire = false;
			//double angle = Math.atan2(x - aimX, y - aimY);
			double angle = Math.atan2(y - aimY, x - aimX);
			Bullet b = new Bullet(bulletSprite, x, y, angle, bulletSpeed, dmg, state);
			holder.addBullet(b);
			return true;
		}
		return false;
	}

	public int getType() {
		return type;
	}
}
