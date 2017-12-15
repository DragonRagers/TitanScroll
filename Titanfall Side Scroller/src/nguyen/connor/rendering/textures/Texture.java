package nguyen.connor.rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import nguyen.connor.utils.managers.TextureManager;

public class Texture {

	private static final Map<String, TextureManager> textMap = new HashMap<String, TextureManager>();
	
	private TextureManager manager;
	private String fileName;
	
	public Texture(String fileName) {
		this.fileName = fileName;
		TextureManager oldTexture = textMap.get(fileName);
		if (oldTexture != null) {
			manager = oldTexture;
			manager.addReference();
		} else {
			try {
				manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
				textMap.put(fileName, manager);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Loading Texture: " + fileName);
		}
	}
	
	protected void finalize() throws Throwable{
		if (manager.removeReference() && !fileName.isEmpty()) {
			textMap.remove(fileName);
		}
		super.finalize();
	}
	
	public void render(Graphics brush, double x, double y) {
		brush.drawImage(manager.getImage(), (int) x, (int) y, null);
	}
	
	public BufferedImage getImage() {
		return manager.getImage();
	}
}
