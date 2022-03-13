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
	private ArrayList<Node> allNodes = new ArrayList<>();
	
	private int currentPosX = 0;
	private int currentPosY = 0;
	

	
	private int endPosX = 0;
	private int endPosY = 0;
	private boolean won = false;
	
	
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
						this.currentPosX = x;
						this.currentPosY = y;
					}
					else if(tile == '2') {
						this.endPosX = x;
						this.endPosY = y;
					}
					this.map[x][y] = tile;
					this.mappedLaby[x][y] = false;
				}
			}
			this.map[endPosX][endPosY] = ' ';
			this.allNodes.add(new Node(currentPosX,currentPosY));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void solveBinaryTree() {
		do {
			ArrayList<Node> nodeToAdd = new ArrayList<>();
			for(Node e : allNodes) {
				if(!e.isDead())
					this.nodeMove(e,nodeToAdd);
			}
			//System.out.println(nodeToAdd.size());
			for(Node e : nodeToAdd) {
				this.allNodes.add(e);
			}
			//System.out.println(allNodes.size());
			//this.drawMap();
		}while(!this.won);
	}
	
	public void nodeMove(Node n,ArrayList<Node> nodeToAdd) {
		if(n.getPosX() == endPosX && n.getPosY() == endPosY) {
			this.won = true;
		}
		this.mappedLaby[n.getPosX()][n.getPosY()] = true;
		this.map[n.getPosX()][n.getPosY()] = 'O';
		if(get(n.getPosX(),n.getPosY()-1) == ' ') {
			this.map[n.getPosX()][n.getPosY()-1] = 'O';
			nodeToAdd.add(new Node(n.getPosX(),n.getPosY()-1));
		}
		if(get(n.getPosX(),n.getPosY()+1) == ' ') {
			this.map[n.getPosX()][n.getPosY()+1] = 'O';
			nodeToAdd.add(new Node(n.getPosX(),n.getPosY()+1));
		}
		if(get(n.getPosX()+1,n.getPosY()) == ' ') {
			this.map[n.getPosX()+1][n.getPosY()] = 'O';
			nodeToAdd.add(new Node(n.getPosX()+1,n.getPosY()));
		}
		if(get(n.getPosX()-1,n.getPosY()) == ' ') {
			this.map[n.getPosX()-1][n.getPosY()] = 'O';
			nodeToAdd.add(new Node(n.getPosX()-1,n.getPosY()));
		}
		
		n.setDead(true);
	}


	public void wait(int buffer) {
		try {
			Thread.sleep(buffer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public char get(int posX,int posY) {
		if(inBound(posX,posY) && !mappedLaby[posX][posY] && map[posX][posY] != 'O') {
			return map[posX][posY];
		}
		return '-';
	}
	
	private boolean inBound(int x,int y) {
		if(x >= 0 && y >= 0 && x < sizeX && y < sizeY) {
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
	
	

}
