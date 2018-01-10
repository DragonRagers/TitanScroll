package nguyen.connor.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class TestDummy extends Mob {

	public TestDummy(double x, double y, GameState state) {
		super(new Sprite("testDummyR"), x, y, state);
		sprites.add(new Sprite("testDummyL"));
		dx = 0;
		dy = 0;
		maxDy = 7;
		hp = 100;
		enemy = true;
	}

	public void tick() {
		super.tick();
		if (hp <= 0) {
			die();
		}
		if (canJump) {
			jump(5);
		}
		if (Math.random() >= .5) {
			direction = 0;
		} else {
			direction = 1;
		}
	}
	
	@Override
	public void render(Graphics2D brush) {
		if (direction == 0) {
			spriteTo(0);
		} else if (direction == 1) {
			spriteTo(1);;
		}
		super.render((Graphics2D) brush);
		brush.setColor(Color.BLACK);
		brush.drawString("Health: " + hp,(int) x, (int) y - 10);
	}
}
