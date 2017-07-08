package game.map.tile;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.Stack;

public class TileStone extends Tile {

	private static final Tile instance = new TileStone();

	public static Tile getInstance() {
		return instance;
	}

	private TileStone() {
		replaceable = false;
		solid = true;
	}

	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/stone.png", 1, 1);
	}

	public void hammerAction(int x, int y) {
		game.map.changeTile("sand", x, y);
		game.player.inv.items.add(new Stack(Game.itemList.get("stone"), 10));
	}
}
