package game.player.inventory;

import java.awt.image.BufferedImage;

import game.Game;
import game.display.DisplayGame;
import game.player.inventory.recipes.RecipeBoat;
import game.player.inventory.recipes.RecipeHammer;

public class Craft extends InvObject {
	
	private HeldItems items;

	public Craft(Game game, HeldItems items) {
		super(game);
		this.items = items;
		initRecipes();
	}

	@Override
	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/crafting.png", 1, 1);
	}

	@Override
	public void action(int x, int y) {
		if (((Recipe)(stacks.get(0))).hasItems(items)) {
			items.add(new Stack(stacks.get(0).getItem(), stacks.get(0).getNum()));
			((Recipe)(stacks.get(0))).removeItems(items);
		}
	}
	
	private void initRecipes() {
		stacks.add(RecipeHammer.getInstance());
		stacks.add(RecipeBoat.getInstance());
	}
	
	public void drawItems(int x, int y) {
		BufferedImage itemImage;
		String text;
		Stack s;
		
		((Recipe)stacks.get(0)).draw(Game.ITEM_SIZE * 2, Game.ITEM_SIZE, game);
		
		for (int i = 0; i < stacks.size(); i++) {
			s = stacks.get(i);
			int x_ = i * Game.ITEM_SIZE + x + Game.ITEM_SIZE * 2;
			int y_ = y;
			itemImage = s.getImg();
			text = " " + s.getNum();
			game.gameDisplay.addImage(itemImage, x_, y_);
			game.gameDisplay.addText(text, x_, Game.ITEM_SIZE + y_);
		}
	}
}
