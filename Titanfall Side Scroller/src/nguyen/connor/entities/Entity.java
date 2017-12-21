package nguyen.connor.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.states.GameState;

public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	protected GameState state;
	
	public Entity(Sprite sprite,double x, double y, GameState state) {
		super();
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.state = state;
		state.addEntity(this);
	}
	
	public abstract void tick();
	
	public void render(Graphics2D brush) {
		sprite.render(brush, x, y);
		//drawHitBox(brush);
	}
 
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
	}
	
	public void drawHitBox(Graphics2D brush) {
		brush.setColor(Color.RED);
		brush.draw(getTop());
		brush.setColor(Color.BLUE);
		brush.draw(getBottom());
		brush.setColor(Color.MAGENTA);
		brush.draw(getRight());
		brush.setColor(Color.GREEN);
		brush.draw(getLeft());
	}
	
	//sides
	public Rectangle getTop() {
		return new Rectangle((int) x  + sprite.getWidth() / 16 - 1, (int) y, sprite.getWidth() * 14 / 16, sprite.getHeight() / 16);
	}
	
	public Rectangle getBottom() {
		return new Rectangle((int) x  + sprite.getWidth() / 16 - 1, (int) y + sprite.getHeight() * 15 / 16, sprite.getWidth()* 14 / 16, sprite.getHeight() / 16);
	}
	
	public Rectangle getRight() {
		return new Rectangle((int)x + sprite.getWidth() * 15 / 16, (int) y + sprite.getHeight() / 16, sprite.getWidth() / 16, sprite.getHeight() * 14 / 16);
	}
	
	public Rectangle getLeft() {
		return new Rectangle((int)x, (int) y + sprite.getHeight() / 16, sprite.getWidth() / 16, sprite.getHeight() * 14 / 16);
	}
	
	protected void die() {
		state.killEntity(this);
	}
	
}
