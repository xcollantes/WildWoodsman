package game.player.inventory;

import game.Game;
import game.display.DisplayGame;

public class HeldItems extends InvObject{
	
	public HeldItems(Game game) {
		super(game);
	}
	
	public void add(Stack stack) {
		int idx = stacks.indexOf(stack);
		if (idx < 0) stacks.add(stack);
		else stacks.get(idx).add(stack.getNum());
	}
	
	public boolean remove(Stack stack) {
		int idx = stacks.indexOf(stack);
		if (idx < 0) return false;
		boolean b = stacks.get(idx).remove(stack.getNum());
		if (stacks.get(idx).getNum() == 0)
			stacks.remove(idx);
		return b;
	}
	
	public Stack get(int i) {
		if (i >= stacks.size())return null;
		return stacks.get(i);
	}

	public int size() {
		return stacks.size();
	}

	@Override
	protected void loadImage() {
		img = DisplayGame.loadImage("res/items/bag.png", 1, 1);
	}

	@Override
	public void action(int x, int y) {
		if (stacks.size() > 0)
			stacks.get(0).action(x, y);
	}
}
