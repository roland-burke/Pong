
public final class ScoreBoard {
	private int score1 = 0;
	private int score2 = 0;
	private DrawPanel dp;
	
	public ScoreBoard(DrawPanel dp, Drop[] drops) {	
		this.dp = dp;
	}
	
	public boolean updateScore(double[][] hitBoxBall) {
		if (hitBoxBall[0][0] < -40) {
			Score2();
			return true;
		} else if (hitBoxBall[0][0] > 1400) {
			Score1();
			return true;
		}
		dp.setScore(score1 + " : " + score2);
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
		dp.setScore(0 + " : " + 0);
		dp.setWinner("");
	}
	
	public boolean checkForWinner() {
		if(score1 >= 10) {
			dp.setWinner("Spieler 1 hat Gewonnen!");
			return true;
			
		} else if(score2 >= 10) {
			dp.setWinner("Spieler 2 hat Gewonnen!");
			return true;
		}
		dp.setCelebration(false);
		return false;
	}
}
