package game.map.tile;

import game.display.DisplayGame;

public class TileGrass extends Tile {
	
	private static final Tile instance = new TileGrass();
	
	public static Tile getInstance() {
		return instance;
	}
	
	private TileGrass(){}
	
	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/grass.png", 1, 1);
	}
}
