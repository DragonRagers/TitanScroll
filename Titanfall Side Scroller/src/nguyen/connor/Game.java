package nguyen.connor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import nguyen.connor.input.KeyInput;
import nguyen.connor.input.MouseInput;
import nguyen.connor.rendering.textures.Sprite;
import nguyen.connor.rendering.textures.SpriteSheet;
import nguyen.connor.rendering.textures.Texture;
import nguyen.connor.states.GameState;
import nguyen.connor.states.MenuState;
import nguyen.connor.states.StateManager;

public class Game extends Canvas implements Runnable {

	public static final String TITLE = "TitanScroll";
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH / 4 * 3;
	
	private boolean running;
	
	private StateManager stateManager;
	
	public static Game INSTANCE;
	
	public Game() {
		addKeyListener(new KeyInput());
		MouseInput mi = new MouseInput();
		addMouseListener(mi);
		addMouseMotionListener(mi);
		stateManager = new StateManager();

		stateManager.addState(new MenuState());
		stateManager.addState(new GameState());
		
		INSTANCE = this;
	}
	
	
	
	private void tick () {
		stateManager.tick();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics brush = bs.getDrawGraphics();
		Graphics2D brush2D = (Graphics2D) brush;
		brush2D.translate(-6, -28);
		
		//draw things
		brush.setColor(Color.WHITE);
		brush.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		stateManager.render(brush2D);
		
		brush.dispose();
		bs.show();
	}
	
	public void run() {
		requestFocus();
		double tickSpeed = 60;
		double nsPerTick = 1000000000 / tickSpeed;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false;
		
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if (unprocessed >= 1) {
				tick();
				MouseInput.update();
				KeyInput.update();
				unprocessed--;
				tps++;
				canRender = true;
			} else {
				canRender = false;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (canRender) {
				render();
				fps++;
			}
			
			if (System.currentTimeMillis() - 1000 > timer) {
				timer += 1000;
				System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
				fps = 0;
				tps = 0;
			}
			
		}
		System.exit(0);
	}
	
	protected void start() {
		if (running) {
			return;
		}
		running = true;
		new Thread(this, "TitanScroll-mainThread").start();
	}

	public void stop() {
		if (!running) {
			return;
		}
		running = false;
	}
}
