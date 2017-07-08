package game.map.tile;

import game.display.DisplayGame;

import java.awt.image.BufferedImage;

public class TileWater extends Tile {

	protected int animCount;
	
	private static final Tile instance = new TileWater();
	
	public static Tile getInstance() {
		return instance;
	}
	
	private TileWater() {
		liquid = true;
	}
	
	protected void loadImages() {
		imgs = new BufferedImage[3];
		imgs[0] = DisplayGame.loadImage("res/tiles/water1.png", 1, 1);
		imgs[1] = null;
		imgs[2] = DisplayGame.loadImage("res/tiles/water2.png", 1, 1);
	}
	
	public void update() {
		animCount = (animCount + 1) % 64;
		
		if (animCount == 0) {
			BufferedImage temp = imgs[0];
			imgs[0] = imgs[2];
			imgs[2] = temp;
		}
	}
	
	public boolean isSolid() {
		return !game.player.inv.items.contains("boat");
	}
}
