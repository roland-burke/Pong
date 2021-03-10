
public final class Bar {
	
	public static final int WIDTH = 22;
	public static final int HEIGHT = 140;
	private double xPos;
	private double yPos = (Pong.frameHeight / 2) - (HEIGHT / 2);
	
	private int speed;

	private double[][] hitBox = { {xPos, xPos + WIDTH},
							   {yPos, yPos + HEIGHT} };

	public Bar(int xPos, int initialYPos) {
		this.xPos = xPos;
		this.yPos = initialYPos;
		this.speed = Pong.BAR_SPEED;
	}

	public void moveUp() {
		if (yPos > 0) {
			yPos -= speed;
		}
	}

	public void moveDown() {
		if (yPos < Pong.frameHeight - HEIGHT) {
			yPos += speed;
		}
	}
	
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	
	public double[][] getHitBox() {
		hitBox[0][0] = xPos;
		hitBox[0][1] = xPos + WIDTH;
		hitBox[1][0] = yPos;
		hitBox[1][1] = yPos + HEIGHT;
		return hitBox;
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
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
