package main;

public class Node {
	
	private int posX;
	private int posY;
	private boolean dead;
	public Node(int posX,int posY) {
		this.dead = false;
		this.posX = posX;
		this.posY = posY;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
