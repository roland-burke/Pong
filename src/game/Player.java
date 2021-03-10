package game;

public class Player {
	public static int MAX_AMMO = 10;
	
	private String name;
	private int score;
	private int life;
	private int ammunition;
	
	private PlayerEnum whoAmI;
	
	public Player(String name, PlayerEnum whoAmI) {
		this.name = name;
		this.whoAmI = whoAmI;
		this.score = 0;
		this.life = 100;
		this.ammunition = Player.MAX_AMMO;
	}

	public int getScore() {
		return score;
	}

	public void score() {
		this.score++;
	}
	
	public void reset() {
		this.score = 0;
		this.ammunition = Player.MAX_AMMO;
	}

	public String getName() {
		return name;
	}
	
	public boolean shoot() {
		if(this.ammunition > 0) {
			this.ammunition--;
			return true;
		}
		return false;
	}
	
	
}
