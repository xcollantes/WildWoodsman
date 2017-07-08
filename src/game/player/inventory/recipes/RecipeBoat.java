package game.player.inventory.recipes;

import game.player.inventory.Recipe;

public class RecipeBoat extends Recipe {

private static final Recipe instance = new RecipeBoat();
	
	public static Recipe getInstance() {
		return instance;
	}

	private RecipeBoat() {
		super("boat", 1);
		addComponent("wood", 10);
	}

}
