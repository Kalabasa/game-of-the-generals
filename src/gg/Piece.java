package gg;

public class Piece {
	private int posX;
	private int posY;
	private int rank;
	private int isAlive;
	private boolean team; // 1 - white, 0 - black

	public Piece(int posX, int posY, int rank, int isAlive, boolean team) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.rank = rank;
		this.isAlive = isAlive;
		this.team = team;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(int isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isTeam() {
		return team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}

	public void moveLeft() {
		posX--;
	}

	public void moveRight() {
		posX++;
	}

	public void moveUp() {
		posY++;
	}

	public void moveDown() {
		posY--;
	}

}
