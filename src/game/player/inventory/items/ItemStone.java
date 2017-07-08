package game.player.inventory.items;

import game.display.DisplayGame;
import game.map.Map;
import game.map.tile.Tile;
import game.player.inventory.Stack;

public class ItemStone extends Item {

	private static final Item instance = new ItemStone();

	public static Item getInstance() {
		return instance;
	}

	private ItemStone() {
	}

	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/stone.png", 1, 1);
	}

	public void action(int x, int y) {
		if (Tile.getTile(x, y).isReplaceable()) {
			game.map.replaceTile("stone floor", x, y);
			game.player.inv.items.remove(new Stack(Item.getItem("stone"), 1));
		}
		else if (Map.getTile(x, y).getName().equals("stone floor")) {
			game.map.changeTile("stone wall", x, y);
			game.player.inv.items.remove(new Stack(Item.getItem("stone"), 1));
		}
	}
}
