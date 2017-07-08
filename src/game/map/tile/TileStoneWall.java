package game.map.tile;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.Stack;

public class TileStoneWall extends Tile {
	
	private static final Tile instance = new TileStoneWall();
	
	public static Tile getInstance() {
		return instance;
	}

	private TileStoneWall() {
		replaceable = false;
		solid = true;
	}
	
	protected void loadImages() {
		imgs[0] = null;
		imgs[1] = DisplayGame.loadImage("res/tiles/stoneWall.png", 1, 1);
	}
	
	public void hammerAction(int x, int y) {
		game.map.revertTile(x, y);
		game.player.inv.items.add(new Stack(Game.itemList.get("stone"), 2));
	}
}
