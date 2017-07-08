package game.map.tile;

import game.display.DisplayGame;

public class TileSand extends Tile {
	
	private static final Tile instance = new TileSand();
	
	public static Tile getInstance() {
		return instance;
	}
	
	private TileSand() {}
	
	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/sand.png", 1, 1);
	}
}
