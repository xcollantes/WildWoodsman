package game.player.inventory.items;

import game.display.DisplayGame;

public class ItemBoat extends Item {

	private static final Item instance = new ItemBoat();

	public static Item getInstance() {
		return instance;
	}

	private ItemBoat() {
	}
	
	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/boat.png", 1, 1);
	}
}
