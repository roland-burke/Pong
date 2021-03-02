import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Ball {
	private Random r = new Random();
	private int randomFactor = 16;
	private double xPos;
	private double yPos;
	private double diameter;
	private double speed = 20;
	private double[][] directionVector = new double[2][1];
	private double[][] angleZero = { { speed }, { 0 } }; // Speed
	
	private double[][] ballHitBox = { {xPos, xPos + diameter},
			{yPos, yPos + diameter} };
	
	public Ball(int xPos, int yPos, int radius) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.diameter = radius;
	}

	public void move() {
		ballHitBox[0][0] = xPos;
		ballHitBox[0][1] = xPos + diameter;
		ballHitBox[1][0] = yPos;
		ballHitBox[1][1] = yPos + diameter;
		
		xPos += directionVector[0][0];
		yPos += directionVector[1][0];	
	}

	
	public void resetDirectionAndPosition() {
		// Place Ball in center
		xPos = (Pong.fieldWidth / 2) - (this.diameter/2);
		yPos = Pong.getFieldHeight() / 2;
		
		// Generate random angle
		double angle;
		if(ballHitBox[0][0] > 1400) {
			angle = getRandomAngle(140, 220);
		} else {
			angle = getRandomAngle(-40, 40);
		}
		
		double[][] rotationMatrix = { { Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)) },
				{ Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)) } };
		directionVector = CollisionDetection.matrixMult(rotationMatrix, angleZero);
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
	
	private double getRandomAngle(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	public void changeDirectionVectorX() {
		this.directionVector[0][0] *= -1;
		int randomInt = r.nextInt() % randomFactor;
		changeAngle(randomInt);
	}
	
	public void changeDirectionVectorY() {
		this.directionVector[1][0] *= -1;
	}
	
	public void changeAngle(int angle) {
		double[][] rotationMatrix = { { Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)) },
				{ Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)) } };
		directionVector = CollisionDetection.matrixMult(rotationMatrix, directionVector);
		angle = 0;
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
	public double getYPos() {
		return this.yPos;
	}
}
