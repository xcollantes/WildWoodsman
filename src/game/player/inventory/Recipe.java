package game.player.inventory;

import game.Game;
import game.player.inventory.items.Item;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Recipe extends Stack {

	private ArrayList<Stack> components;

	public Recipe(Item item, int num) {
		super(item, num);
		components = new ArrayList<Stack>();
	}

	public Recipe(String itemName, int num) {
		super(Item.getItem(itemName), num);
		components = new ArrayList<Stack>();
	}

	public boolean hasItems(HeldItems items) {
		for (Stack component : components) {
			int idx = items.stacks.indexOf(component);
			if (idx < 0)
				return false;
			if (items.stacks.get(idx).getNum() < component.getNum())
				return false;
		}
		return true;
	}

	public void removeItems(HeldItems items) {
		for (Stack component : components)
			items.remove(component);
	}

	protected void addComponent(String itemName, int num) {
		components.add(new Stack(Item.getItem(itemName), num));
	}

	public void draw(int x, int y, Game game) {
		BufferedImage itemImage;
		String text;
		for (int i = 0; i < components.size(); i++) {
			Stack c = components.get(i);
			int x_ = x;
			int y_ = i * Game.ITEM_SIZE + y;
			itemImage = c.getImg();
			text = " " + c.getNum();
			game.gameDisplay.addImage(itemImage, x_, y_);
			game.gameDisplay.addText(text, x_, Game.ITEM_SIZE + y_);
		}
	}
}
