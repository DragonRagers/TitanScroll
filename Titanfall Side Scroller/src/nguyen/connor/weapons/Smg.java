package nguyen.connor.weapons;

import java.awt.Graphics;

import nguyen.connor.entities.Entity;
import nguyen.connor.entities.Mob;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class Smg extends Weapon {
	public Smg(double x, double y, GameState state) {
		super(new Sprite("smg"), x, y, state);
		fireRate = 10;
		bulletSpeed = 35;
		tickCount = 0;
		bulletSprite = new Sprite("player bullet");
		this.state = state;
		type = 1;
		dmg = 12;
	}
	
	public Smg(Mob holder, GameState state) {
		this(holder.getX(), holder.getY(), state);
		this.holder = holder;
		equipped = true;
	}
}
