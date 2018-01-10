package nguyen.connor.entities;

import java.util.ArrayList;

import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;
import nguyen.connor.world.Tile;

public abstract class Mob extends Entity {

	protected double dx, dy;
	protected double maxDy;
	protected static double gravity = .5;
	protected boolean falling = true;
	protected boolean canJump = false;
	protected ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	protected int direction;
	
	public Mob(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
		sprites.add(sprite);
	}

	public void spriteTo(int c) {
		sprite = sprites.get(c);
	}
	
	public void tick() {
		move();
	}

	public void move() {
		if (!hasHorizontalCollision()) {
			x += dx;
		}
		if (!hasVerticalCollision()) {
			y += dy;
		}
		fall();

	}

	protected boolean hasVerticalCollision() {
		for (int c = 0; c < state.getTiles().size(); c++) {
			Tile t = state.getTiles().get(c);
			if (getBottom().intersects(t.getTop()) && dy > 0) {
				dy = 0;
				falling = false;
				canJump = true;
				return true;
			} else if (getTop().intersects(t.getBottom()) && dy < 0) {
				dy = 0;
				y -= 4;
				return true;
			} else {
				falling = true;
				canJump = false;
			}
		}
		return false;
	}
	
	protected boolean hasHorizontalCollision() {
		for (int c = 0; c < state.getTiles().size(); c++) {
			Tile t = state.getTiles().get(c);
			if (getLeft().intersects(t.getRight()) && dx < 0) {
				dx = 0;
				return true;
			}
			if (getRight().intersects(t.getLeft()) && dx > 0) {
				dx = 0;
				return true;
			}
		}
		return false;
	}
	
	protected void fall() {
		if (falling) {
			dy += gravity;
			if (dy > maxDy) {
				dy = maxDy;
			}
		}
	}
	
	protected void jump(double jumpHeight) {
		if (canJump) {
			dy -= jumpHeight;
			canJump = false;
		}
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}

}
