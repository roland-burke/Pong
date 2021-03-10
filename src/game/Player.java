package game;

public class Player {
	private String name;
	private int score;
	private int life;
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.life = 100;
	}

	public int getScore() {
		return score;
	}

	public void score() {
		this.score++;
	}
	
	public void resetScore() {
		this.score = 0;
	}

	public String getName() {
		return name;
	}
	
	
}
