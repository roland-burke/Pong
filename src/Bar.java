
public final class Bar {
	
	private int width = 20;
	private int height = 140;
	private double xPos;
	private double yPos = (Pong.frameHeight / 2) - (height / 2);
	
	private int speed = 28;

	private double[][] hitBox = { {xPos, xPos + width},
							   {yPos, yPos + height} };

	public Bar(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public void moveUp() {
		if (yPos > 0) {
			yPos -= speed;
		}
	}

	public void moveDown() {
		if (yPos < Pong.frameHeight - height) {
			yPos += speed;
		}
	}
	
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	
	public double[][] getHitBox() {
		hitBox[0][0] = xPos;
		hitBox[0][1] = xPos + width;
		hitBox[1][0] = yPos;
		hitBox[1][1] = yPos + height;
		return hitBox;
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
	public double getYPos() {
		return this.yPos;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
}
