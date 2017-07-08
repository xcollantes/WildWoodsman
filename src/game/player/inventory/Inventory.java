package game.player.inventory;

import game.Game;

import java.util.ArrayList;

public class Inventory {
	
	public HeldItems items;
	public Craft craftables;
	
	ArrayList<InvObject> objects;

	public Inventory(Game game) {
		items = new HeldItems(game);
		craftables = new Craft(game, items);
		
		objects = new ArrayList<InvObject>();
		objects.add(items);
		objects.add(craftables);
		
	}

	public void rotateUp() {
		if (objects.size() > 0) {
			InvObject stk = objects.get(0);
			objects.remove(0);
			objects.add(stk);
		}
	}
	
	public void rotateDown() {
		if (objects.size() > 0) {
			InvObject stk = objects.get(objects.size() - 1);
			objects.remove(objects.size() - 1);
			objects.add(0, stk);
		}
	}
	
	public void rotateLeft() {
		objects.get(0).rotateLeft();
	}
	
	public void rotateRight() {
		objects.get(0).rotateRight();
	}
	
	public void draw() {
		InvObject o;
		for (int i = 0; i < objects.size(); i++) {
			o = objects.get(i);
			o.drawIcon(0, i * Game.ITEM_SIZE);
		}
		objects.get(0).drawItems(0,0);
	}

	public void action(int x, int y) {
		objects.get(0).action(x, y);
	}
}
