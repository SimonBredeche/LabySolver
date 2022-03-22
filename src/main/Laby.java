package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Laby {
	
	private char[][] map;
	private boolean[][] mappedLaby;
	private int sizeX;
	private int sizeY;
	
	private int startPosX = 0;
	private int startPosY = 0;
	

	

	
	
	public Laby(String fileName) {
		BufferedReader in;
		try {
			String line = "";
			in = new BufferedReader(new FileReader("maps/"+fileName));
			int tmpy = 0;
			ArrayList<String> tmp = new ArrayList<>();
			while((line = in.readLine()) != null) {
				tmp.add(line);
				tmpy++;
			}
			this.sizeX = tmp.get(0).length();
			this.sizeY = tmpy;
			this.map = new char[sizeX][sizeY];
			this.mappedLaby = new boolean[sizeX][sizeY];
			for(int y = 0; y < tmp.size(); y++) {
				for(int x = 0; x < tmp.get(y).length(); x++) {
					char tile = tmp.get(y).charAt(x);
					if(tile == '1') {
						this.startPosX = x;
						this.startPosY = y;
					}
					this.map[x][y] = tile;
					this.mappedLaby[x][y] = false;
				}
			}
			//this.map[endPosX][endPosY] = ' ';
			//this.allNodes.add(new Node(currentPosX,currentPosY));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void solve() {
		ArrayList<Position> path = new ArrayList<Position>();
		long start = System.currentTimeMillis();
		this.recursiveSolve(startPosX, startPosY, path);
		long end = System.currentTimeMillis();
		System.out.println("Solving Time in milli seconds: "+ (end-start));	
		boolean sw = true;
		for(Position p : path) {
			char choice = sw ? 'x' : '-';
			map[p.x][p.y] = choice;
			sw = !sw;
		}
		this.drawMapWithoutTry();
	}
	
	public boolean recursiveSolve(int posx,int posy,ArrayList<Position> path) {
		if(!inBound(posx,posy)) {
			return false;
		}
		if(this.map[posx][posy] == '2') {
			path.add(new Position(posx,posy));
			return true;
		}
		if(this.map[posx][posy] == ' ' || this.map[posx][posy] == '1') {
			this.map[posx][posy] = 'O';  
			if(this.recursiveSolve(posx+1,posy,path)) {
				path.add(new Position(posx,posy));
				return true;
			}
			if(this.recursiveSolve(posx-1,posy,path)) {
				path.add(new Position(posx,posy));
				return true;
			}
			if(this.recursiveSolve(posx,posy-1,path)) {
				path.add(new Position(posx,posy));
				return true;
			}
			if(this.recursiveSolve(posx,posy+1,path)) {
				path.add(new Position(posx,posy));
				return true;
			}
		}
		return false;
	} 
	
	public boolean inBound(int posX, int posY) {
		if(posX >= 0 && posY >= 0 && posX < sizeX && posY < sizeY) {
			return true;
		}
		return false;
	}
	

	
	public void drawMap() {
		for(int y = 0; y < sizeY; y++) {
			String row = "";
			for(int x = 0; x < sizeX;x++) {
				 row += map[x][y];
			}
			System.out.println(row);

		}
	}
	public void drawMapWithoutTry() {
		for(int y = 0; y < sizeY; y++) {
			String row = "";
			for(int x = 0; x < sizeX;x++) {
				if(map[x][y] != 'O') {
					row += map[x][y];
				}
				else {
					row += ' ';
				}
			}
			System.out.println(row);

		}
	}
	
	

}
