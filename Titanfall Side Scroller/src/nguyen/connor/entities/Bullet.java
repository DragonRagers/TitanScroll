package nguyen.connor.entities;

import java.awt.Rectangle;
import java.util.ArrayList;

import nguyen.connor.Game;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;
import nguyen.connor.world.Tile;

public class Bullet extends Mob {

	protected Mob shooter;
	protected double angle;
	protected double speed;
	protected Sprite sprite;
	protected double dmg;
	
	public Bullet(Sprite sprite, double x, double y, double angle, double speed, double dmg, GameState state) {
		super(sprite, x, y, state);
		this.angle = angle;
		dy = Math.sin(angle) * -speed;
		dx = Math.cos(angle) * -speed;
		maxDy = 50;
		enemy = false;
		this.dmg = dmg;
	}

	public void tick() {
		super.move();
		if (hasCollision()
				|| x < 0 || x > Game.WIDTH
				|| y < 0 || y > Game.HEIGHT
				) {
			die();
		}
		ArrayList<Entity> entities = state.getEntites();
		for (int c = 0; c < entities.size(); c++) {
			if (entities.get(c).enemy && getBounds().intersects(entities.get(c).getBounds())) {
				entities.get(c).hit(dmg);
				die();
			}
		}
	}

	protected boolean hasCollision() {
		Rectangle thisHitBox = getBounds();
		for (int c = 0; c < state.getTiles().size(); c++) {
			Tile t = state.getTiles().get(c);
			if (thisHitBox.intersects(t.getBounds())) {
				return true;
			}
		}
		return false;
	}
}
