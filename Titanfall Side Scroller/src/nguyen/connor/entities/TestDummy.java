package nguyen.connor.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public class TestDummy extends Mob {

	public TestDummy(double x, double y, GameState state) {
		super(new Sprite("testDummy"), x, y, state);
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
	}

	@Override
	public void render(Graphics2D brush) {
		super.render((Graphics2D) brush);
		brush.setColor(Color.BLACK);
		brush.drawString("Health: " + hp,(int) x, (int) y - 10);
	}
}
