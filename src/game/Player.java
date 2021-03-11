package game;

import Utils.PlayerEnum;

public class Player {
	public static int Max_Ammo = 10;
	public static int Damage = 15;

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
		this.ammunition = Player.Max_Ammo;
	}

	public void score() {
		this.score++;
	}

	public void reset() {
		this.score = 0;
		this.ammunition = Player.Max_Ammo;
		this.life = 100;
	}

	public boolean shoot() {
		if (this.ammunition > 0) {
			this.ammunition--;
			return true;
		}
		return false;
	}
	
	public void damage() {
		this.life = this.life - Player.Damage;
	}
	
	public PlayerEnum getWhoAmI() {
		return this.whoAmI;
	}
	
	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public String getLifeString() {
		return " (" + String.valueOf(this.life) + ")";
	}
	
	public int getAmmo() {
		return this.ammunition;
	}
	
	public String getAmmoString() {
		return String.valueOf(this.ammunition) + "/" + Max_Ammo;
	}

}
