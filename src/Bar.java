
public final class Bar {
	DrawPanel dp;
	
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	
	private int speed = 18;
	private int checkWhichBar;

	private double[][] hitBox = { {xPos, xPos + width},
							   {yPos, yPos + height} };

	public Bar(int bar, DrawPanel dp, int xPos, int yPos, int width, int height) {
		this.dp = dp;
		this.dp.setBarWidthHeight(width, height, checkWhichBar);
		this.dp.setxPos(xPos, bar);
		this.checkWhichBar = bar;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public void moveUp() {
		if (yPos > 0) {
			yPos -= speed;
		}
		dp.setyPos(yPos, checkWhichBar);
	}

	public void moveDown() {
		if (yPos < 814) {
			yPos += speed;
		}
		dp.setyPos(yPos, checkWhichBar);
	}
	
	public double[][] getHitBox() {
		hitBox[0][0] = xPos;
		hitBox[0][1] = xPos + width;
		hitBox[1][0] = yPos;
		hitBox[1][1] = yPos + height;
		return hitBox;
	}
}
