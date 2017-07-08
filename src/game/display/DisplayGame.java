package game.display;

import game.Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DisplayGame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage screen1;
	private BufferedImage screen2;
	
	public DisplayGame() {
		
		setLayout(null);
		screen1 = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		screen2 = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
	}
	
	public static BufferedImage loadImage(String path, double scalex, double scaley) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
			int width = (int)(img.getWidth() * scalex * Game.TILE_SIZE / 64);
			int height = (int)(img.getHeight() * scaley * Game.TILE_SIZE / 64);
			
			BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			temp.getGraphics().drawImage(img, 0, 0, temp.getWidth(), temp.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
			img = temp;
			
		} catch (IOException e) {
			System.out.println("--ERROR! image at '" + path + "' not found--");
			img = new BufferedImage(Game.TILE_SIZE, Game.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
		}
		return img;
	}
	
	public void addText(String string, int x, int y) {
		Graphics2D g = screen2.createGraphics();
		g.drawString(string, x, y);		
	}
	
	public void addImage(BufferedImage newImg, int x, int y) {
		Graphics2D g = screen2.createGraphics();
		g.drawImage(newImg, x, y, null);
		g.dispose();
	}
	
	public void update() {
		BufferedImage temp = screen1;
		screen1 = screen2;
		screen2 = temp;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(screen1, 0, 0, null);
		g.dispose();
	}
} 
