package gg;

public class Piece {
	private int posX;
	private int posY;
	private Rank rank;

	private enum Rank {
		FLAG, PRIVATE, SERGEANT, SECONDLIEUTENANT, FIRSTLIEUTENANT, CAPTAIN, MAJOR, LIEUTENANTCOLONEL, COLONEL, ONESTARGENERAL, TWOSTARGENERAL, THREESTARGENERAL, FOURSTARGENERAL, FIVESTARGENERAL, SPY;

		public int getRank() {
			return this.ordinal();
		}
	}

	private int isAlive;
	private boolean team; // 1 - white, 0 - black

	public int getPieceRank() {
		return rank.getRank();
	}

	public Piece() {
		rank = rank.PRIVATE;
	}

	public Piece(int posX, int posY, int rank, int isAlive, boolean team) {
		super();
		this.posX = posX;
		this.posY = posY;
		// this.rank = rank;
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

	public void move(int newX, int newY) {
		this.posX = newX;
		this.posY = newY;
	}

	public static String getSprite(int rank) {
		return "piece" + rank;
	}

}
