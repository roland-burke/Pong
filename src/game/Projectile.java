package game;

public class Projectile implements GameElement {

	public final int WIDTH = 40;
	public final int HEIGHT = 10;
	private double xPos;
	private double yPos;

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
		if (origin == PlayerEnum.Player1) {
			if(this.xPos < Pong.fieldWidth) {
				this.xPos += speed;
				return true;
			}
		} else {
			if(this.xPos > -WIDTH) {
				this.xPos -= speed;
				return true;
			}
		}
		return false;
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
