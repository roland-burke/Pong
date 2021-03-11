package game;

import Utils.PlayerEnum;

public class Projectile implements GameElement {

	public final int WIDTH = 40;
	public final int HEIGHT = 10;
	private double xPos;
	private double yPos;
	private boolean exist = true;

	private int speed = Pong.PROJECTILE_SPEED;

	private PlayerEnum origin;

	private double[][] hitBox = { { xPos, xPos + WIDTH }, { yPos, yPos + HEIGHT } };

	public Projectile(PlayerEnum player, Bar bar) {
		this.origin = player;
		if (origin == PlayerEnum.Player1) {
			this.xPos = bar.getXPos();
			this.yPos = bar.getYPos() + bar.getHeight() / 2;
		} else {
			this.xPos = bar.getXPos() - bar.getWidth();
			this.yPos = bar.getYPos() + bar.getHeight() / 2;
		}
	}

	public boolean move() {
		if(!exist) {
			return exist;
		}
		if (origin == PlayerEnum.Player1) {
			if(this.xPos < Pong.fieldWidth) {
				for(int i = 0; i < speed; i++) {
					this.xPos += 1;					
				}
				return true;
			}
		} else {
			if(this.xPos > -WIDTH) {
				for(int i = 0; i < speed; i++) {
					this.xPos -= 1;					
				}
				return true;
			}
		}
		return false;
	}
	
	public void dissapear() {
		this.exist = false;
	}

	public PlayerEnum getOrigin() {
		return this.origin;
	}
	
	@Override
	public double[][] getHitBox() {
		return hitBox;
	}

	@Override
	public double getXPos() {
		return xPos;
	}

	@Override
	public double getYPos() {
		return yPos;
	}

	public int getWidth() {
		return this.WIDTH;
	}

	public int getHeight() {
		return this.HEIGHT;
	}

}
