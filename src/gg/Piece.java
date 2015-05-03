package gg;

public class Piece {
	private Rank rank;
	private boolean team; // 1 - white, 0 - black
	private enum Rank {
		FLAG, PRIVATE, SERGEANT, SECONDLIEUTENANT, FIRSTLIEUTENANT, CAPTAIN, MAJOR, LIEUTENANTCOLONEL, COLONEL, ONESTARGENERAL, TWOSTARGENERAL, THREESTARGENERAL, FOURSTARGENERAL, FIVESTARGENERAL, SPY;

		public int getRank() {
			return this.ordinal();
		}
	}
	
	public Piece(Rank rank, boolean team) {
		this.rank = rank;
		this.team = team;
	}
	
	public int getPieceRank() {
		return rank.getRank();
	}

	public boolean getTeam() {
		return team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}

	public static String getSprite(int rank) {
		return "piece" + rank;
	}

}
