package game.map.tile;

import game.display.DisplayGame;

public class TileTallGrass extends Tile {
	
	private static final Tile instance = new TileTallGrass();
	
	public static Tile getInstance() {
		return instance;
	}

	private TileTallGrass() {
		super();
	}

	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/grass.png", 1, 1);
		imgs[1] = DisplayGame.loadImage("res/tiles/tallGrass.png", 1, 1);
	}
}
