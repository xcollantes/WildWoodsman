package game.player.inventory.items;

import game.display.DisplayGame;
import game.map.Map;
import game.map.tile.Tile;
import game.player.inventory.Stack;

public class ItemWood extends Item {
	
private static final Item instance = new ItemWood();
	
	public static Item getInstance() {
		return instance;
	}
	
	private ItemWood() {}

	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/wood.png", 1, 1);
	}
	
	public void action(int x, int y) {
		if (Tile.getTile(x, y).isReplaceable()) {
			game.map.replaceTile("wood floor", x, y);
			game.player.inv.items.remove(new Stack(Item.getItem("wood"), 1));
		}	
		else if (Map.getTile(x, y).getName().equals("wood floor")) {
			game.map.changeTile("wood wall", x, y);
			game.player.inv.items.remove(new Stack(Item.getItem("wood"), 1));
		}
	}
}
