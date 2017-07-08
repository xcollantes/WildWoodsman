package game.map.tile;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.Stack;

public class TileWoodFloor extends Tile {
	
	private static final Tile instance = new TileWoodFloor();
	
	public static Tile getInstance() {
		return instance;
	}
	
	private TileWoodFloor() {
		replaceable = false;
	}
	
	protected void loadImages() {
		imgs[0] = DisplayGame.loadImage("res/tiles/woodFloor.png", 1, 1);
	}
	
	public void axeAction(int x, int y) {
		game.map.revertTile(x, y);
		game.player.inv.items.add(new Stack(Game.itemList.get("wood"), 1));
	}
}
