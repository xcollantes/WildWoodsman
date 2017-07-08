package game.player.inventory.items;

import game.Game;
import game.display.DisplayGame;
import game.map.Map;

public class ItemHammer extends Item {

	private static final Item instance = new ItemHammer();

	public static Item getInstance() {
		return instance;
	}

	private ItemHammer() {
	}
	
	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/hammer.png", 1, 1);
	}
	
	public void action(int x, int y) {
		Game.tileIds.get(Map.mapTiles[x][y]).hammerAction(x, y);
	}

}
