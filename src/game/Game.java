package game;

import game.display.DisplayGame;
import game.display.DisplayMainMenu;
import game.display.DisplayNewGameMenu;
import game.display.DisplayPauseMenu;
import game.map.Map;
import game.map.tile.Tile;
import game.map.tile.TileGrass;
import game.map.tile.TileSand;
import game.map.tile.TileStone;
import game.map.tile.TileStoneFloor;
import game.map.tile.TileStoneWall;
import game.map.tile.TileStump;
import game.map.tile.TileTallGrass;
import game.map.tile.TileTree;
import game.map.tile.TileWater;
import game.map.tile.TileWoodFloor;
import game.map.tile.TileWoodWall;
import game.player.Player;
import game.player.inventory.items.Item;
import game.player.inventory.items.ItemAxe;
import game.player.inventory.items.ItemBoat;
import game.player.inventory.items.ItemHammer;
import game.player.inventory.items.ItemStone;
import game.player.inventory.items.ItemWood;
import game.sound.SoundThreadPlaylist;

import java.awt.CardLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int TILE_SIZE = 64;
	public static final int ITEM_SIZE = 64;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;

	public static HashMap<String, Tile> tileList;
	public static HashMap<String, Tile> tileIds;
	public static HashMap<String, Item> itemList;
	public static HashMap<String, Item> itemIds;

	public CardLayout cl;
	public JPanel panel;
	public DisplayMainMenu mainMenu;
	public DisplayGame gameDisplay;
	public DisplayPauseMenu pauseMenu;
	public DisplayNewGameMenu newGameMenu;
	public SoundThreadPlaylist mp3;

	public Player player;
	public Map map;

	public boolean running, gameStarted, newGame, loadGame, paused;

	public Game() {
		super("Wild Woodsman");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setFocusable(true);
		requestFocus();
		addKeyListener(new Input());

		cl = new CardLayout();
		panel = new JPanel(cl);
		mainMenu = new DisplayMainMenu(this);
		gameDisplay = new DisplayGame();
		pauseMenu = new DisplayPauseMenu(this);
		newGameMenu = new DisplayNewGameMenu(this);
		getContentPane().add(panel);
		panel.add(mainMenu, "Main");
		panel.add(gameDisplay, "Game");
		panel.add(pauseMenu, "Pause");
		panel.add(newGameMenu, "New");
		cl.show(panel, "Main");
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
		System.exit(0);
	}

	public void startGame() {
		addKeyListener(new Input());
		running = true;
		loadObjects();
		int gameSpeed = 60;
		long prev = System.nanoTime();
		boolean updated = false;
		
		mp3 = new SoundThreadPlaylist();
		
		while (running) {
			long curr = System.nanoTime();
			while (curr - prev > 1000000000.0 / gameSpeed) {
				prev = System.nanoTime();
				
				mp3.update();
				if (gameStarted && !paused) {
					update();
					updated = true;
					
				}
				if (updated) {
					updated = false;
					draw();
				} else try {Thread.sleep(1);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}

	private void update() {
		player.update();
		map.updateTiles();
	}

	private void draw() {
		map.drawTiles();
		map.drawWalls1();
		player.draw();
		map.drawWalls2();
		player.inv.draw();
		gameDisplay.update();
	}

	public void loadObjects() {
		
		tileList = new HashMap<String, Tile>();
		tileIds = new HashMap<String, Tile>();

		itemList = new HashMap<String, Item>();
		itemIds = new HashMap<String, Item>();

		int tileCount = 0;
		newTile(tileCount++, "tile", Tile.getInstance(this));
		newTile(tileCount++, "grass", TileGrass.getInstance());
		newTile(tileCount++, "sand", TileSand.getInstance());
		newTile(tileCount++, "wood floor", TileWoodFloor.getInstance());
		newTile(tileCount++, "wood wall", TileWoodWall.getInstance());
		newTile(tileCount++, "water", TileWater.getInstance());
		newTile(tileCount++, "tree", TileTree.getInstance());
		newTile(tileCount++, "stump", TileStump.getInstance());
		newTile(tileCount++, "tall grass", TileTallGrass.getInstance());
		newTile(tileCount++, "stone", TileStone.getInstance());
		newTile(tileCount++, "stone floor", TileStoneFloor.getInstance());
		newTile(tileCount++, "stone wall", TileStoneWall.getInstance());
		
		int itemCount = 0;
		newItem(itemCount++, "item", Item.getInstance(this));
		newItem(itemCount++, "wood", ItemWood.getInstance());
		newItem(itemCount++, "stone", ItemStone.getInstance());
		newItem(itemCount++, "axe", ItemAxe.getInstance());
		newItem(itemCount++, "hammer", ItemHammer.getInstance());
		newItem(itemCount++, "boat", ItemBoat.getInstance());
	}

	private void newTile(int id, String name, Tile tile) {
		tile.setName(name);
		tile.setId(id);
		tileIds.put(Integer.toString(id), tile);
		tileList.put(name, tile);
	}

	private void newItem(int id, String name, Item item) {
		item.setName(name);
		item.setId(id);
		itemIds.put(Integer.toString(id), item);
		itemList.put(name, item);
	}
}
