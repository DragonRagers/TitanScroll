package nguyen.connor.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


import nguyen.connor.Game;
import nguyen.connor.entities.Entity;
import nguyen.connor.entities.Player;
import nguyen.connor.entities.TestDummy;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.rendering.textures.SpriteSheet;
import nguyen.connor.rendering.textures.Texture;
import nguyen.connor.world.Tile;

public class GameState implements State {

	private ArrayList<Entity> entities;
	private ArrayList<Tile> tiles;
	
	public void init() {
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		new Player(100, 100, this);
		new TestDummy(200, Game.HEIGHT - 428, this);
		float x = 0;
		float y = Game.HEIGHT - 300;
		for (int c = 0; c < 20; c++) {
			tiles.add(new Tile(new Sprite(new SpriteSheet(new Texture("terrain"), 64), 0, 0), x, y));
			x += 64;
		}
		x=64*3;
		y = Game.HEIGHT- (300 + 64);
		for (int c = 0; c < 20; c++) {
			tiles.add(new Tile(new Sprite(new SpriteSheet(new Texture("terrain"), 64), 0, 0), x, y));
			x += 128;
		}
		x = 64*5;
		y = Game.HEIGHT - (300 + 64 * 3);
		for (int c = 0; c < 20; c++) {
			tiles.add(new Tile(new Sprite(new SpriteSheet(new Texture("terrain"), 64), 0, 0), x, y));
			x += 64;
		}
	}

	public void enter() {
		
	}
	
	public void killEntity(Entity e) {
		boolean ch = entities.remove(e);
		if (!ch) throw new IllegalArgumentException("Entity was not alive.");
	}

	public void tick(StateManager stateManager) {
		for (int c = 0; c < entities.size(); c++) {
			entities.get(c).tick();
		}
	}

	public void render(Graphics2D brush) {
		
		for (Entity e : entities) {
			e.render(brush);
		}
		for (Tile t : tiles) {
			t.render(brush);
		}
	}

	public void exit() {
		entities.clear();
	}

	public String getName() {
		return "level1";
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public ArrayList<Entity> getEntites() {
		return entities;
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
}
