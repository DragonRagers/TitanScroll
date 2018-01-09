package nguyen.connor.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;

import nguyen.connor.entities.Bullet;
import nguyen.connor.entities.Entity;
import nguyen.connor.entities.Mob;
import nguyen.connor.entities.Player;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;
import nguyen.connor.world.Tile;

public abstract class Weapon extends Mob {

	public Weapon(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
		maxDy = 11;
	}

	protected int fireRate;
	protected boolean canFire;
	protected double bulletSpeed;
	protected int tickCount;
	protected Sprite bulletSprite;
	public Mob holder;
	protected double aimX, aimY;
	protected GameState state;
	protected int type;
	protected double dmg;
	public boolean equipped;

	@Override
	public void render(Graphics2D brush) {
		if (equipped) {
			super.render(brush); 
		}	
	}

	public void tick() {
		if (!canFire) {
			tickCount++;
		} 
		if (tickCount >= fireRate) {
			canFire = true;
			tickCount = 0;
		}
		if (holder instanceof Player) {
			aimX = MouseInput.getX();
			aimY = MouseInput.getY();
		}
		move();
	}

	public void move() {
		if (holder != null) {
			x = holder.getX();
			y = holder.getY();
		} else {
			fall();
		}
	}

	@Override
	public void fall () {
		for (int c = 0; c < state.getTiles().size(); c++) {
			Tile t = state.getTiles().get(c);
			if (getBounds().intersects(t.getTop()) && dy >= 0) {
				dy = 0;
			} 
		}
		x += dx;
		y += dy;
		dy += gravity;
		if (dy > maxDy) {
			dy = maxDy;
		}	
	}

	public boolean shoot() {
		if (holder != null && canFire) {
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

	public void drop() {
		holder = null;
		equipped = true;
	}
}
