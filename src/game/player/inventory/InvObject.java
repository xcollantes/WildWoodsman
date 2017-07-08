package game.player.inventory;

import game.Game;
import game.player.inventory.items.Item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class InvObject {

	protected Game game;
	protected BufferedImage img;
	protected ArrayList<Stack> stacks;
	
	public InvObject(Game game) {
		this.game = game;
		stacks = new ArrayList<Stack>();
		
		img = new BufferedImage(Game.ITEM_SIZE, Game.ITEM_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setPaint(new Color(0));
		g.fillRect (0, 0, img.getWidth(), img.getHeight());
		loadImage();
	}
	
	public void rotateLeft() {
		if (stacks.size() > 0) {
			Stack stk = stacks.get(0);
			stacks.remove(0);
			stacks.add(stk);
		}
	}
	
	public void rotateRight() {
		if (stacks.size() > 0) {
			Stack stk = stacks.get(stacks.size() - 1);
			stacks.remove(stacks.size() - 1);
			stacks.add(0, stk);
		}
	}
	
	public void drawItems(int x, int y) {
		BufferedImage itemImage;
		String text;
		Stack s;
		for (int i = 0; i < stacks.size(); i++) {
			s = stacks.get(i);
			itemImage = s.getImg();
			text = " " + s.getNum();
			game.gameDisplay.addImage(itemImage, i * Game.ITEM_SIZE + x + Game.ITEM_SIZE * 2, 0 + y);
			game.gameDisplay.addText(text, i * Game.ITEM_SIZE + x + Game.ITEM_SIZE * 2, Game.ITEM_SIZE + y);
		}
	}
	
	public void drawIcon(int x, int y) {
		game.gameDisplay.addImage(img, x, y);
	}
	
	public boolean contains(String itemName) {
		return stacks.contains(new Stack(Item.getItem(itemName), 1));
	}
	
	public ArrayList<Stack> getStacks() {
		return stacks;
		
	}
	
	abstract protected void loadImage();
	abstract public void action(int x, int y);
}
