package nguyen.connor.weapons;

import java.awt.Graphics;

import nguyen.connor.entities.Entity;
import nguyen.connor.entities.Mob;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class Pistol extends Weapon {
	public Pistol(double x, double y, GameState state) {
		super(new Sprite("player bullet"), x, y, state);
		fireRate = 45;
		bulletSpeed = 40;
		tickCount = 0;
		bulletSprite = new Sprite("player bullet");
		this.state = state;
		type = 0;
	}
	
	public Pistol(Mob holder, GameState state) {
		this(holder.getX(), holder.getY(), state);
		this.holder = holder;
	}
}
