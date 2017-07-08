package game.player;

import game.Game;
import game.Input;
import game.display.DisplayGame;
import game.map.Map;
import game.player.inventory.Inventory;
import game.player.inventory.Stack;
import game.player.inventory.items.Item;

import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Player {

	public static int mapx, mapy;
	public static int x, y;
	
	private Game game;
	private BufferedImage[][] imgs;
	private BufferedImage[] raftImgs;
	
	private short dir;
	
	private int walkAnimCount;
	private int walkSpeed;
	private boolean moving, busy, rotating;
	
	public Inventory inv;
	
	// constructor for new player object
	public Player(Game game) {
		this.game = game;
		Random r = new Random();
		do {
			x = r.nextInt(Map.MAP_SIZE);
			y = r.nextInt(Map.MAP_SIZE);
		} while (Map.getTile(x, y).isLiquid() || Map.getTile(x, y).isSolid());
		
		walkSpeed = 20;
		inv = new Inventory(game);
		inv.items.add(new Stack(Game.itemList.get("axe"), 1));
		
		imgs = new BufferedImage[4][4];
		raftImgs = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				imgs[i][j] = DisplayGame.loadImage("res/player/player_" + i + "_" + j + ".png", 1, 1);
			raftImgs[i] = DisplayGame.loadImage("res/player/raft" + i + ".png", 1, 1);
		}
	}
	
	public Player(String path, Game game) {
		this.game = game;
		walkSpeed = 20;
		inv = new Inventory(game);
		imgs = new BufferedImage[4][4];
		raftImgs = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				imgs[i][j] = DisplayGame.loadImage("res/player/player_" + i + "_" + j + ".png", 1, 1);
			raftImgs[i] = DisplayGame.loadImage("res/player/raft" + i + ".png", 1, 1);
		}
		load(path);
	}

	// updates the player
	public void update() {
		Input.update();
		
		if (!moving) doAction();
		if (!busy) move();
		
		if (Input.key_up || Input.key_down || Input.key_left || Input.key_right) {
			if (!rotating) {
				if (Input.key_up) inv.rotateUp();
				else if (Input.key_down) inv.rotateDown();
				else if (Input.key_left) inv.rotateLeft();
				else if (Input.key_right) inv.rotateRight();
				rotating = true;
			}
		}
		else rotating = false;
		pause();
		getMapPos();
	}
	
	private void pause() {
		if (Input.key_esc) {
			game.cl = (CardLayout) (game.panel.getLayout());
			game.cl.show(game.panel, "Pause");
			game.paused = true;
		}
	}
	
	
	// calculates player position relative to map coordinates
	private void getMapPos() {
		mapx = (Game.WIDTH - Game.TILE_SIZE) / 2 - x * Game.TILE_SIZE;
		mapy = (Game.HEIGHT - Game.TILE_SIZE) / 2 - y * Game.TILE_SIZE;
		
		int dist = walkAnimCount * Game.TILE_SIZE / walkSpeed;
		switch(dir) {
		case 0:	mapy += dist;
				break;
		case 1:	mapx -= dist;
				break;
		case 2:	mapx += dist;
				break;
		case 3:	mapy -= dist;
				break;
		}
	}
	
	// performs action depending on tile in front of player
	private void doAction() {
		if (Input.key_space) {
			if (!busy) {
				busy = true;
				int nextx = x;
				int nexty = y;
				switch(dir) {
					case 0:	nexty++;
						break;
					case 1:	nextx--;
						break;
					case 2:	nextx++;
						break;
					case 3:	nexty--;
						break;
				}
				while (nextx < 0)
					nextx += Map.MAP_SIZE;
				while (nexty < 0)
					nexty += Map.MAP_SIZE;
				while (nextx >= Map.MAP_SIZE)
					nextx -= Map.MAP_SIZE;
				while (nexty >= Map.MAP_SIZE)
					nexty -= Map.MAP_SIZE;

				inv.action(nextx, nexty);
			}
		}
		else busy = false;;
	}

	// calculates and updates player position based on key inputs
	private void move() {
		int nextx = x;
		int nexty = y;
		
		if (!moving) {
			
			if (Input.key_w && !Input.key_s) {
				dir = 3; nexty = y-1; moving = true;
			}
			
			else if (Input.key_s && !Input.key_w) {
				dir = 0; nexty = y+1; moving = true;
			}
			
			else if (Input.key_d && !Input.key_a) {
				dir = 2; nextx = x+1; moving = true;
			}
			
			else if (Input.key_a && !Input.key_d) {
				dir = 1; nextx = x-1; moving = true;
			}
			
			while (nextx < 0) nextx += Map.MAP_SIZE;
			while (nexty < 0) nexty += Map.MAP_SIZE;
			while (nextx >= Map.MAP_SIZE) nextx -= Map.MAP_SIZE;
			while (nexty >= Map.MAP_SIZE) nexty -= Map.MAP_SIZE;
			
			if (!Game.tileIds.get(Map.mapTiles[nextx][nexty]).isSolid()) {
				x = nextx;	y = nexty;
				if (moving) walkAnimCount = walkSpeed;
			}	
		}
		else {
			if (walkAnimCount > 0) walkAnimCount--;
			else moving = false;
		}
	}
	
	// calculates player screen position and adds correct player image to display image
	public void draw() {
		BufferedImage img;
		if (Game.tileIds.get(Map.mapTiles[x][y]).isLiquid()) {
			img = imgs[dir][0];
			game.gameDisplay.addImage(raftImgs[dir], (Game.WIDTH - img.getWidth()) / 2, Game.HEIGHT / 2 - img.getHeight() + Game.TILE_SIZE);
		}
		else
			img = imgs[dir][(walkAnimCount % walkSpeed) / (walkSpeed/4)];
		
		game.gameDisplay.addImage(img, (Game.WIDTH - img.getWidth()) / 2, Game.HEIGHT / 2 - img.getHeight() + Game.TILE_SIZE / 2);
	}
	
	private boolean load(String path) {
		Scanner fileIn = null;
		try { 
			fileIn = new Scanner(new FileInputStream(path));
			x = Integer.parseInt(fileIn.nextLine());
			y = Integer.parseInt(fileIn.nextLine());
			dir = Short.parseShort(fileIn.nextLine());
			StringTokenizer st;
			while (fileIn.hasNext()) {
				st = new StringTokenizer(fileIn.nextLine(), ",");
				inv.items.add(new Stack(Item.getItem(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			fileIn.close();
			return true;
		} catch (FileNotFoundException e) { 
			System.out.println("--ERROR! Could not load from '" + path + "'--");
			return false;
		}
	}
	
	public boolean save(String path) {
		try {
			PrintWriter writer = new PrintWriter(path);
			writer.println(x);
			writer.println(y);
			writer.println(dir);
			for (Stack s : inv.items.getStacks()) {
				writer.println(s.getName() + "," + s.getNum());
			}
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("--ERROR! Could not save to '" + path + "'--");
			return false;
		}
	}
}
