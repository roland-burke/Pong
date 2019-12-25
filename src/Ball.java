import java.util.concurrent.ThreadLocalRandom;

public final class Ball {
	private double defaultXPos;
	private double defaultYPos;
	private double xPos;
	private double yPos;
	private double radius;
	private double speed = 10;
	private double[][] directionVector = new double[2][1];
	private double[][] angleZero = { { speed }, { 0 } }; // Speed
	
	private double[][] ballHitBox = { {xPos, xPos + radius},
			{yPos, yPos + radius} };
	
	public Ball(int xPos, int yPos, int radius) {
		this.xPos = this.defaultXPos = xPos;
		this.yPos = this.defaultYPos = yPos;
		this.radius = radius;
	}

	public void move() {
		ballHitBox[0][0] = xPos;
		ballHitBox[0][1] = xPos + radius;
		ballHitBox[1][0] = yPos;
		ballHitBox[1][1] = yPos + radius;
		
		directionVector = CollisionDetection.getDirectionVector();
		xPos += directionVector[0][0];
		yPos += directionVector[1][0];	
	}

	
	public void resetDirectionAndPosition() {
		// Place Ball in center
		xPos = defaultXPos;
		yPos = defaultYPos;
		
		// Generate random angle
		double angle;
		if(ballHitBox[0][0] < -40) {
			angle = getRandomAngle(-40, 40, 8, -8);
		} else if (ballHitBox[0][0] > 1400) {
			angle = getRandomAngle(140, 220, 188, 172);
		} else {
			angle = getRandomAngle(-40, 40, 8, -8);
		}
		
		double[][] rotationMatrix = { { Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)) },
				{ Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)) } };
		directionVector = CollisionDetection.matrixMult(rotationMatrix, angleZero);
		CollisionDetection.setDirectionVector(directionVector);
		CollisionDetection.resetCollisionConditions();
		angleZero[0][0] = speed;
	}
	
	public void resetDirectionAndPositionAndSpeed() {
		angleZero[0][0] = 0;
		resetDirectionAndPosition();
	}
	
	public double[][] getHitBox() {
		return ballHitBox;
	}
	
	private double getRandomAngle(int min, int max, int min2, int max2) {
		double angle = ThreadLocalRandom.current().nextInt(min, max);
		while(angle < min2 && angle > max2) {
			angle = ThreadLocalRandom.current().nextInt(min, max);
		}
		return angle;
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
	public double getYPos() {
		return this.yPos;
	}
}
