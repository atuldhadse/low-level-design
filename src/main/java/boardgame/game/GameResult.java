package boardgame.game;

public class GameResult {

	boolean over;
	Player player;

	public GameResult(boolean over, Player player) {
		this.over = over;
		this.player = player;
	}

	public boolean isOver() {
		return over;
	}

	public Player getPlayer() {
		return player;
	}

}
