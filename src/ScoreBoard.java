
public final class ScoreBoard {
	private int score1 = 0;
	private int score2 = 0;
	private String p1 = "Player1";
	private String p2 = "Player2";
	private String winningPlayer = p1;
	
	public boolean updateScore(double[][] hitBoxBall) {
		if (hitBoxBall[0][0] < -40) {
			Score2();
			return true;
		} else if (hitBoxBall[0][0] > 1400) {
			Score1();
			return true;
		}
		return false;
	}
	
	private void Score1() {
		this.score1 += 1;
	}
	
	private void Score2() {
		this.score2 += 1;
	}
	
	public void reset() {
		score1 = 0;
		score2 = 0;
		winningPlayer = "";
	}
	
	public boolean checkForWinner() {
		if(score1 >= 10) {
			winningPlayer = p1;
			return true;
		} else if(score2 >= 10) {
			winningPlayer = p2;
			return true;
		}
		return false;
	}
	
	public int getScore1() {
		return score1;
	}

	public int getScore2() {
		return score2;
	}
	
	public String getScoreBoard() {
		return score1 + "  " + score2;
	}

	public String getP1() {
		return p1;
	}

	public String getP2() {
		return p2;
	}

	public String getWinningPlayer() {
		return winningPlayer;
	}
	
}
