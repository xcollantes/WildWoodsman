package game.map.tile;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.Stack;

public class TileWoodWall extends Tile {
	
	private static final Tile instance = new TileWoodWall();
	
	public static Tile getInstance() {
		return instance;
	}
	
	private TileWoodWall() {
		replaceable = false;
		solid = true;
	}
	
	protected void loadImages() {
		imgs[0] = null;
		imgs[1] = DisplayGame.loadImage("res/tiles/woodWall.png", 1, 1);
	}
	
	public void axeAction(int x, int y) {
		if (game.map.revertTile(x, y))
			game.player.inv.items.add(new Stack(Game.itemList.get("wood"), 2));
	}
}
