package nguyen.connor.entities;

import nguyen.connor.Game;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class Bullet extends Mob {
	
	protected Mob shooter;
	protected double angle;
	protected double speed;
	protected Sprite sprite;	
	
	public Bullet(Sprite sprite, double x, double y, double angle, double speed, GameState state) {
		super(sprite, x, y, state);
		dy = Math.sin(angle) * -speed;
		dx = Math.cos(angle) * -speed;
		maxDy = 50;
	}
	
	public void tick() {
		//System.out.println(x + " " + y);
		super.move();
		if (hasVerticalCollision() || hasHorizontalCollision()
				|| x < 0 || x > Game.WIDTH
				|| y < 0 || y > Game.HEIGHT
				) {
			die();
		}
	}
	

	
	
	
}
