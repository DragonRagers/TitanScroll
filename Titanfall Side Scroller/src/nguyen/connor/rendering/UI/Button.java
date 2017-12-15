package nguyen.connor.rendering.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import nguyen.connor.Game;
import nguyen.connor.utils.Fonts;

public class Button extends Rectangle {

	private Font font, selectedFont;
	private Color color, selectedColor;
	private boolean selected;
	private String text;
	private int textY;
	
	public Button(String text, int textY, Color color, Font font, Color selectedColor, Font selectedFont) {
		super();
		this.text = text;
		this.textY = textY;
		this.color = color;
		this.font = font;
		this.selectedColor = selectedColor;
		this.selectedFont = selectedFont;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void render(Graphics brush) {
		if (selected) {
			Fonts.drawString(brush, selectedFont, selectedColor, text, textY);
		} else {
			Fonts.drawString(brush, font, color, text, textY);
		}
		
		FontMetrics fm = brush.getFontMetrics();
		this.x = (Game.WIDTH - fm.stringWidth(text)) / 2;
		this.y = textY - fm.getHeight();
		this.width = fm.stringWidth(text);
		this.height = fm.getHeight();
		brush.drawRect(x, y, width, height);
	}
	
	
}
