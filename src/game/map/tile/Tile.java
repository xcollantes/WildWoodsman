package game.map.tile;

import game.Game;
import game.map.Map;
import game.player.Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {

	protected boolean solid, liquid, replaceable;
	protected BufferedImage[] imgs;
	
	protected String id;
	protected String name;
	protected static Game game;
	
	private static final Tile instance = new Tile();
	
	public static Tile getInstance(Game game) {
		Tile.game = game;
		return instance;
	}
	
	protected Tile() {
		getImages();
		solid = false;
		liquid = false;
		replaceable = true;
	}
	
	protected void getImages() {
		imgs = new BufferedImage[2];
		imgs[0] = new BufferedImage(Game.TILE_SIZE, Game.TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = imgs[0].createGraphics();
		g.setPaint(new Color(0));
		g.fillRect (0, 0, imgs[0].getWidth(), imgs[0].getHeight());
		imgs[1] = null;
		loadImages();
	}
	
	public void drawFloor(int x, int y) {
		if (imgs[0] != null) {
			int width = imgs[0].getWidth();
			int height = imgs[0].getHeight();
			game.gameDisplay.addImage(imgs[0], x * Game.TILE_SIZE + Player.mapx + (Game.TILE_SIZE-width)/2, y * Game.TILE_SIZE + Player.mapy - height + Game.TILE_SIZE);
		}
	}
	
	public void drawWall(int x, int y) {
		if (imgs[1] != null) {
			int width = imgs[1].getWidth();
			int height = imgs[1].getHeight();
			game.gameDisplay.addImage(imgs[1], x * Game.TILE_SIZE + Player.mapx + (Game.TILE_SIZE-width)/2, y * Game.TILE_SIZE + Player.mapy - height + Game.TILE_SIZE);
		}
	}
	
	public void setId(int id) {this.id = Integer.toString(id);}
	public void setName(String name) {this.name = name;}
	
	protected void loadImages() {}
	public void update() {}
	public void axeAction(int x, int y) {}
	public void hammerAction(int x, int y) {}

	public String getId() {return id;}
	public String getName() {return name;}
	public boolean isSolid() {return solid;}
	public boolean isLiquid() {return liquid;}
	public boolean isReplaceable() {return replaceable;}
	
	public static Tile getTile(int x, int y) {
		return Game.tileIds.get(Map.mapTiles[x][y]);
	}
	
	public static Tile getTile(String name) {
		return Game.tileIds.get(name);
	}
}
