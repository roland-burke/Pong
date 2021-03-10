package game;
public final class Bar implements GameElement {

	public static final int WIDTH = 22;
	public static final int HEIGHT = 140;
	private double xPos;
	private double yPos = (Pong.fieldHeight / 2) - (HEIGHT / 2);

	private int speed = Pong.BAR_SPEED;

	private double[][] hitBox = { { xPos, xPos + WIDTH }, { yPos, yPos + HEIGHT } };

	public Bar(int xPos, int initialYPos) {
		this.xPos = xPos;
		this.yPos = initialYPos;
	}

	public void moveUp() {
		for (int i = 0; i < speed; i++) {
			if (yPos > 0) {
				yPos--;
			} else {
				break;
			}
		}
	}

	public void moveDown() {
		for (int i = 0; i < speed; i++) {
			if (yPos < Pong.fieldHeight - HEIGHT) {
				yPos++;
			} else {
				break;
			}
		}
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	@Override
	public double[][] getHitBox() {
		hitBox[0][0] = xPos;
		hitBox[0][1] = xPos + WIDTH;
		hitBox[1][0] = yPos;
		hitBox[1][1] = yPos + HEIGHT;
		return hitBox;
	}
	
	@Override
	public double getXPos() {
		return this.xPos;
	}

	@Override
	public double getYPos() {
		return this.yPos;
	}

	public double getWidth() {
		return Bar.WIDTH;
	}

	public double getHeight() {
		return Bar.HEIGHT;
	}
}
