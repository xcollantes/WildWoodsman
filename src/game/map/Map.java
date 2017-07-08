package game.map;

import java.util.ArrayList;

import game.Game;
import game.map.tile.Tile;
import game.player.Player;

public class Map extends MapLoader {

	public static int MAP_SIZE = 129 ;
	
	public static String[][] mapTiles;
	public static ArrayList<ReplacedTile> replacedTiles;
	static Game game;
	
	public Map(Game game) {
		Map.game = game;
		newMap();
		replacedTiles =  new ArrayList<ReplacedTile>();
	}
	
	public Map(String path, Game game) {
		Map.game = game;
		load(path);
		replacedTiles =  new ArrayList<ReplacedTile>();
	}

	public void drawTiles() {
		int xmin = Player.x - Game.WIDTH / 2 / Game.TILE_SIZE - 3;
		int ymin = Player.y - Game.HEIGHT / 2 / Game.TILE_SIZE - 3;
		int xmax = Player.x + Game.WIDTH / 2 / Game.TILE_SIZE + 3;
		int ymax = Player.y + Game.HEIGHT / 2 / Game.TILE_SIZE + 3;
		
		for (int y = ymin; y < ymax; y++)
			for (int x = xmin; x < xmax; x++) {
				
				int xmap = x;
				int ymap = y;
				while (xmap < 0) xmap += MAP_SIZE;
				while (ymap < 0) ymap += MAP_SIZE;
				while (xmap >= MAP_SIZE) xmap -= MAP_SIZE;
				while (ymap >= MAP_SIZE) ymap -= MAP_SIZE;

				try {
					Game.tileIds.get((mapTiles[xmap][ymap])).drawFloor(x, y);
				} catch(NullPointerException e) {
					System.out.println("--ERROR! Tile id '" + mapTiles[xmap][ymap] + "' not found. Tile at " + xmap + "," + ymap + " not loaded--");
					System.out.println();
					mapTiles[xmap][ymap] = "0";
					Game.tileIds.get(mapTiles[xmap][ymap]).drawFloor(x, y);
				}
			}
	}
	
	public void drawWalls1() {
		int xmin = Player.x - Game.WIDTH / 2 / Game.TILE_SIZE - 3;
		int ymin = Player.y - Game.HEIGHT / 2 / Game.TILE_SIZE - 3;
		int xmax = Player.x + Game.WIDTH / 2 / Game.TILE_SIZE + 3;
		int ymax = Player.y+1;
		for (int y = ymin; y < ymax; y++)
			for (int x = xmin; x < xmax; x++) {
				
				int xmap = x;
				int ymap = y;
				while (xmap < 0) xmap += MAP_SIZE;
				while (ymap < 0) ymap += MAP_SIZE;
				while (xmap >= MAP_SIZE) xmap -= MAP_SIZE;
				while (ymap >= MAP_SIZE) ymap -= MAP_SIZE;

				try {
					Game.tileIds.get(mapTiles[xmap][ymap]).drawWall(x, y);
				} catch(NullPointerException e) {
					System.out.println("--ERROR! Tile id '" + mapTiles[xmap][ymap] + "' not found. Tile at " + xmap + "," + ymap + " not loaded--");
					System.out.println();
					mapTiles[xmap][ymap] = "0";
					Game.tileIds.get(mapTiles[xmap][ymap]).drawWall(x, y);
				}
			}
	}
	
	public void drawWalls2() {
		int xmin = Player.x - Game.WIDTH / 2 / Game.TILE_SIZE - 3;
		int ymin = Player.y+1;
		int xmax = Player.x + Game.WIDTH / 2 / Game.TILE_SIZE + 3;
		int ymax = Player.y + Game.HEIGHT / 2 / Game.TILE_SIZE + 3;
		for (int y = ymin; y < ymax; y++)
			for (int x = xmin; x < xmax; x++) {
				
				int xmap = x;
				int ymap = y;
				while (xmap < 0) xmap += MAP_SIZE;
				while (ymap < 0) ymap += MAP_SIZE;
				while (xmap >= MAP_SIZE) xmap -= MAP_SIZE;
				while (ymap >= MAP_SIZE) ymap -= MAP_SIZE;

				try {
					Game.tileIds.get(mapTiles[xmap][ymap]).drawWall(x, y);
				} catch(NullPointerException e) {
					System.out.println("--ERROR! Tile id '" + mapTiles[xmap][ymap] + "' not found. Tile at " + xmap + "," + ymap + " not loaded--");
					System.out.println();
					mapTiles[xmap][ymap] = "0";
					Game.tileIds.get(mapTiles[xmap][ymap]).drawWall(x, y);
				}
			}
	}
	
	public void updateTiles() {
		for (String name : Game.tileList.keySet()) {
			Game.tileList.get(name).update();
		}
	}
	
	public void newMap() {
		new MapGenerator().getMap();
	}
	
	public void changeTile(String tileName, int x, int y) {
		mapTiles[x][y] = Game.tileList.get(tileName).getId();
	}
	
	public boolean replaceTile(String tileName, int x, int y) {
		Tile temp = Game.tileIds.get(mapTiles[x][y]);
		if (temp.isReplaceable()) {
			replacedTiles.add(new ReplacedTile(temp.getId(), x, y));
			mapTiles[x][y] = Game.tileList.get(tileName).getId();
			return true;
		}
		return false;
	}
	
	public boolean revertTile(int x, int y) {
		int idx = replacedTiles.indexOf(new ReplacedTile("", x, y));
		
		if (idx < 0) return false;
		mapTiles[x][y] = replacedTiles.get(idx).getTile();
		return true;
	}
	
	private class ReplacedTile {
		public String tileId;
		public int x;
		public int y;
		
		public ReplacedTile(String tileId, int x, int y) {
			this.tileId = tileId;
			this.x = x;
			this.y = y;
		}
		
		public String getTile() {
			return tileId;
		}
		
		public boolean equals(Object other) {
			ReplacedTile otherTile = (ReplacedTile)other;
			if (x == otherTile.x && y == otherTile.y)
				return true;
			return false;
		}
	}

	public static Tile getTile(int x, int y) {
		return Game.tileIds.get(mapTiles[x][y]);
	}
}
