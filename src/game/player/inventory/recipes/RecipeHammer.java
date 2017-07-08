package game.player.inventory.recipes;

import game.player.inventory.Recipe;

public class RecipeHammer extends Recipe {
	
	public static final Recipe instance = new RecipeHammer();
	
	public static Recipe getInstance() {
		return instance;
	}

	private RecipeHammer() {
		super("hammer", 1);
		addComponent("wood", 5);
	}
}
