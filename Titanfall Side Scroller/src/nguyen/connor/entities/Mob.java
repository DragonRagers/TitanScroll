package nguyen.connor.entities;

import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;
import nguyen.connor.world.Tile;

public abstract class Mob extends Entity {

	protected double dx, dy;
	protected double maxDy;
	protected double gravity;
	protected boolean falling = true;
	protected boolean canJump = false;
	
	public Mob(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
		gravity = 0.5;
		maxDy = 7;
	}

	public void tick() {
		move();
		fall();
	}

	public void move() {
		if (!hasHorizontalCollision()) {
			x += dx;
		}
		if (!hasVerticalCollision()) {
			y += dy;
		}

	}

	protected boolean hasVerticalCollision() {
		for (int c = 0; c < state.getTiles().size(); c++) {
			Tile t = state.getTiles().get(c);
			if (getBottom().intersects(t.getTop()) && dy > 0) {
				dy = 0;
				falling = false;
				canJump = true;
				return true;
			} else {
				falling = true;
				canJump = false;
			}
			if (getTop().intersects(t.getBottom()) && dy < 0) {
				dy = 0;
				return true;
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

}
