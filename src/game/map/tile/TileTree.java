package game.map.tile;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.Stack;

public class TileTree extends Tile {
	
	private static final Tile instance = new TileTree();
	
	public static Tile getInstance() {
		return instance;
	}

	private TileTree() {
		solid = true;
		replaceable = false;
	}
	
	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/grass.png", 1, 1);
		imgs[1] = DisplayGame.loadImage("res/tiles/tree.png", 1, 1);
	}
	
	public void axeAction(int x, int y) {
		game.map.changeTile("stump", x, y);
		game.player.inv.items.add(new Stack(Game.itemList.get("wood"), 2));
	}
}
