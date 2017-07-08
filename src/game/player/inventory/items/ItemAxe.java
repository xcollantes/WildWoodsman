package game.player.inventory.items;

import game.Game;
import game.display.DisplayGame;
import game.map.Map;

public class ItemAxe extends Item{

	private static final Item instance = new ItemAxe();

	public static Item getInstance() {
		return instance;
	}

	private ItemAxe() {
	}
	
	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/axe.png", 1, 1);
	}
	
	public void action(int x, int y) {
		Game.tileIds.get(Map.mapTiles[x][y]).axeAction(x, y);
	}
}
