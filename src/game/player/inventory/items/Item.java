package game.player.inventory.items;

import game.Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Item {
	
	private static final Item instance = new Item();

	protected BufferedImage img;
	
	protected String id;
	protected String name;
	protected static Game game;
	
	public static Item getInstance(Game game) {
		Item.game = game;
		return instance;
	}
	
	protected Item() {
		getImages();
	}

	protected void getImages() {
		img = new BufferedImage(Game.ITEM_SIZE, Game.ITEM_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setPaint(new Color(0));
		g.fillRect (0, 0, img.getWidth(), img.getHeight());
		loadImage();
	}
	
	public void action(int x, int y) {}
	protected void loadImage() {}
	public void setId(int id) {this.id = Integer.toString(id);}
	public void setName(String name) {this.name = name;}
	public String getId() {return id;}
	public String getName() {return name;}
	public BufferedImage getImage() {return img;}
	
	public static Item getItem(String name) {
		return Game.itemList.get(name);
	}
}
