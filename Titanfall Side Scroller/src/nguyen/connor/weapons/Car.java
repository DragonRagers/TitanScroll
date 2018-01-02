package nguyen.connor.weapons;

import java.awt.Graphics;

import nguyen.connor.entities.Entity;
import nguyen.connor.entities.Mob;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class Car extends Weapon {
	public Car(double x, double y, GameState state) {
		super(new Sprite("car"), x, y, state);
		fireRate = 10;
		bulletSpeed = 35;
		tickCount = 0;
		bulletSprite = new Sprite("player bullet");
		this.state = state;
		type = 1;
		dmg = 12;
	}
	
	public Car(Mob holder, GameState state) {
		this(holder.getX(), holder.getY(), state);
		this.holder = holder;
	}
}
