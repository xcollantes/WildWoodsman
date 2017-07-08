package game.map.tile;

import game.display.DisplayGame;

public class TileStump extends Tile {
	
	private static final Tile instance = new TileStump();
	
	public static Tile getInstance() {
		return instance;
	}
	
	private TileStump() {}

	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/grass.png", 1, 1);
		imgs[1] = DisplayGame.loadImage("res/tiles/stump.png", 1, 1);
	}
}
