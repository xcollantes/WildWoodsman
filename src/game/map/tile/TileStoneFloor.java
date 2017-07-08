package game.map.tile;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.Stack;

public class TileStoneFloor extends Tile {

	private static final Tile instance = new TileStoneFloor();

	public static Tile getInstance() {
		return instance;
	}
	
	private TileStoneFloor() {
		replaceable = false;
	}

	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/stoneFloor.png", 1, 1);
	}

	public void hammerAction(int x, int y) {
		game.map.revertTile(x, y);
		game.player.inv.items.add(new Stack(Game.itemList.get("stone"), 1));
	}
}
