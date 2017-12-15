package nguyen.connor.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import nguyen.connor.Game;

public class Fonts {

	public static void drawString(Graphics brush, Font f, Color c, String text, int x, int y) {
		brush.setColor(c);
		brush.setFont(f);
		brush.drawString(text, x, y);
	}
	
	public static void drawString(Graphics brush, Font f, Color c, String text, double x) {
		FontMetrics fm = brush.getFontMetrics(f);
		int y = (Game.HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
		drawString(brush, f, c, text, (int) x, y);
	}
	
	public static void drawString(Graphics brush, Font f, Color c, String text, int y) {
		FontMetrics fm = brush.getFontMetrics(f);
		int x = (Game.WIDTH - fm.stringWidth(text)) / 2;
		drawString(brush, f, c, text, x, y);
	}
	
}
