package boardgame.game;

public class GameInfo {

	private boolean over;

	private String winner;

	private boolean hasFork;

	private Player player;

	public GameInfo(GameResult gameResult, boolean hasFork, Player player) {
		over = gameResult.isOver();
		winner = gameResult.getPlayer().getSymbol();
		this.hasFork = hasFork;
		this.player = player;
	}

	public boolean isOver() {
		return over;
	}

	public String getWinner() {
		return winner;
	}

	public boolean isHasFork() {
		return hasFork;
	}

	public Player getPlayer() {
		return player;
	}

}