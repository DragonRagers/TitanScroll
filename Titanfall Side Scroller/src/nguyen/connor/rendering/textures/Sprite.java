package nguyen.connor.rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
	
	private SpriteSheet spriteSheet;
	private BufferedImage image;
	
	public Sprite(SpriteSheet spriteSheet, int x, int y) {
		this.image = spriteSheet.getTexture().getImage().getSubimage(x * spriteSheet.getWidth(),
				y * spriteSheet.getHeight(),
				spriteSheet.getWidth(),
				spriteSheet.getHeight());
	}
	
	public Sprite(String texName) {
		Texture tex = new Texture(texName);
		image = tex.getImage();
	}
	
	public void render(Graphics brush, double x, double y) {
		brush.drawImage(image, (int) x, (int) y, null);
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
}
