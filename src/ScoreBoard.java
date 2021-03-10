
public final class ScoreBoard {
	private Player player1;
	private Player player2;
	
	private Player winningPlayer = player1;
	private int winningScore = Pong.WINNING_SCORE;
	
	public ScoreBoard(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public boolean updateScore(double[][] hitBoxBall) {
		if (hitBoxBall[0][0] < -40) {
			this.player2.score();
		} else if (hitBoxBall[0][0] > Pong.fieldWidth) {
			this.player1.score();
		} else {
			return false;
		}
		return true;
	}
	
	public void reset() {
		player1.resetScore();
		player2.resetScore();
	}
	
	public boolean checkForWinner() {
		if(player1.getScore() >= winningScore) {
			winningPlayer = player1;
		} else if(player2.getScore() >= winningScore) {
			winningPlayer = player2;
		} else {
			return false;
		}
		return true;
	}
	
	public int getScore1() {
		return player1.getScore();
	}

	public int getScore2() {
		return player2.getScore();
	}
	
	public String getScoreBoard() {
		return player1.getScore() + "    " + player2.getScore();
	}

	public String getPlayer1Name() {
		return player1.getName();
	}

	public String getPlayer2Name() {
		return player2.getName();
	}

	public String getWinningPlayerName() {
		return winningPlayer.getName();
	}
	
}
