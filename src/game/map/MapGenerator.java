package game.map;

import game.Game;

import java.util.Random;

public class MapGenerator {

	private int[][] vals;

	public void getMap() {
		vals = new int[Map.MAP_SIZE][Map.MAP_SIZE];
		newTerrain();
	}

	private void newTerrain() {
		Map.mapTiles = new String[Map.MAP_SIZE][Map.MAP_SIZE];
		for (int x = 0; x < Map.MAP_SIZE; x++)
			for (int y = 0; y < Map.MAP_SIZE; y++) {
				Map.mapTiles[x][y] = "0";
			}
		makeTerrain();
	}

	private void makeTerrain() {
		vals = new int[Map.MAP_SIZE][Map.MAP_SIZE];
		float range = 1000;
		int step = Map.MAP_SIZE - 1;
		setp(0, 0, 100);
		setp(Map.MAP_SIZE - 1, 0, 100);
		setp(0, Map.MAP_SIZE - 1, 100);
		setp(Map.MAP_SIZE - 1, Map.MAP_SIZE - 1, 100);

		while (step > 1) {
			for (int x = 0; x < Map.MAP_SIZE - 1; x += step)
				for (int y = 0; y < Map.MAP_SIZE - 1; y += step) {
					int sx = x + (step >> 1);
					int sy = y + (step >> 1);

					int[][] points = new int[4][2];
					points[0][0] = x;
					points[0][1] = y;
					points[1][0] = x + step;
					points[1][1] = y;
					points[2][0] = x;
					points[2][1] = y + step;
					points[3][0] = x + step;
					points[3][1] = y + step;

					computeVal(sx, sy, points, range);
				}

			for (int x = 0; x < Map.MAP_SIZE - 1; x += step)
				for (int y = 0; y < Map.MAP_SIZE - 1; y += step) {
					int halfstep = step >> 1;
					int x1 = x + halfstep;
					int y1 = y;
					int x2 = x;
					int y2 = y + halfstep;

					int[][] points1 = new int[4][2];
					points1[0][0] = x1 - halfstep;
					points1[0][1] = y1;
					points1[1][0] = x1;
					points1[1][1] = y1 - halfstep;
					points1[2][0] = x1 + halfstep;
					points1[2][1] = y1;
					points1[3][0] = x1;
					points1[3][1] = y1 + halfstep;

					int[][] points2 = new int[4][2];
					points2[0][0] = x2 - halfstep;
					points2[0][1] = y2;
					points2[1][0] = x2;
					points2[1][1] = y2 - halfstep;
					points2[2][0] = x2 + halfstep;
					points2[2][1] = y2;
					points2[3][0] = x2;
					points2[3][1] = y2 + halfstep;

					computeVal(x1, y1, points1, range);
					computeVal(x2, y2, points2, range);
				}
			range /= 2;
			step /= 2;
		}
		Random r = new Random();
		for (int i = 0; i < Map.MAP_SIZE; i++)
			Map.mapTiles[r.nextInt(Map.MAP_SIZE)][r.nextInt(Map.MAP_SIZE)] = Game.tileList.get("stone").getId();
	}

	private void computeVal(int x, int y, int[][] points, float range) {
		float v = 0;
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			if (points[i][0] < 0)
				points[i][0] += Map.MAP_SIZE - 1;
			else if (points[i][0] > Map.MAP_SIZE)
				points[i][0] -= Map.MAP_SIZE - 1;
			else if (points[i][1] < 0)
				points[i][1] += Map.MAP_SIZE - 1;
			else if (points[i][1] > Map.MAP_SIZE)
				points[i][1] -= Map.MAP_SIZE - 1;
			v += vals[points[i][0]][points[i][1]] / 4;
		}
		v += (r.nextFloat() * range - range / 2);
		setp(x, y, v);
		if (x == 0)
			setp(Map.MAP_SIZE - 1, y, v);
		else if (x == Map.MAP_SIZE - 1)
			setp(0, y, v);
		else if (y == 0)
			setp(x, Map.MAP_SIZE - 1, v);
		else if (y == Map.MAP_SIZE - 1)
			setp(x, 0, v);
	}

	private void setp(int x, int y, float value) {
		if (value < 0)
			value = 0;
		if (value > 500)
			value = 500;
		vals[x][y] += value;
		Random r = new Random();

		if (value > 450)
			Map.mapTiles[x][y] = Game.tileList.get("tall grass").getId();
		else if (value > 250 && value <= 450 && r.nextInt(3) == 1)
			Map.mapTiles[x][y] = Game.tileList.get("tree").getId();
		else if (value > 100 && value <= 450)
			Map.mapTiles[x][y] = Game.tileList.get("grass").getId();
		else if (value > 50 && value <= 100)
			Map.mapTiles[x][y] = Game.tileList.get("sand").getId();
		else if (value <= 50)
			Map.mapTiles[x][y] = Game.tileList.get("water").getId();
	}
}
