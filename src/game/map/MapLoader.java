package game.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MapLoader {
	
	public boolean load(String path) {
		Scanner fileIn = null;
		try { 
			fileIn = new Scanner(new FileInputStream(path));
			Map.MAP_SIZE = Integer.parseInt(fileIn.nextLine());
			Map.MAP_SIZE = Integer.parseInt(fileIn.nextLine());
			Map.mapTiles = new String[Map.MAP_SIZE][Map.MAP_SIZE];
			
			StringTokenizer st;
			for (int y = 0; y < Map.MAP_SIZE; y++) {
				st = new StringTokenizer(fileIn.nextLine(), ",");
				for (int x = 0; x < Map.MAP_SIZE; x++) {
					String id = st.nextToken();
					Map.mapTiles[x][y] = id;
				}
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
			writer.println(Map.MAP_SIZE);
			writer.println(Map.MAP_SIZE);
			for (int y = 0; y < Map.MAP_SIZE; y++) {
				for ( int x = 0; x < Map.MAP_SIZE; x++) {
					writer.print(Map.mapTiles[x][y] + ",");
				}
				writer.println();
			}
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("--ERROR! Could not save to '" + path + "'--");
			return false;
		}
	}
}
