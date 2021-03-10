package game;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Ball implements GameElement {
	private Random r = new Random();
	private int randomFactor = 16;
	private double xPos;
	private double yPos;
	private final double DIAMETER = 40;
	private double speed = 1;
	private double[][] directionVector = new double[2][1];
	private double[][] angleZero = { { speed }, { 0 } }; // Speed
	
	// This is to shoot the ball randomly to the left or the right side at the first move.
	private boolean firstMove = true;

	private double[][] ballHitBox = { { xPos, xPos + this.DIAMETER }, { yPos, yPos + this.DIAMETER } };

	public Ball() {
		resetDirectionAndPositionAndSpeed();
	}

	public void move(Bar leftBar, Bar rightBar) {
		ballHitBox[0][0] = xPos;
		ballHitBox[0][1] = xPos + this.DIAMETER;
		ballHitBox[1][0] = yPos;
		ballHitBox[1][1] = yPos + this.DIAMETER;

		for (int i = 0; i < Pong.BALL_SPEED; i++) {
			CollisionDetection.calculate(this, leftBar, rightBar);
			xPos += directionVector[0][0];
			yPos += directionVector[1][0];
		}
	}

	public void resetDirectionAndPosition() {
		// Place Ball in center
		xPos = (Pong.fieldWidth / 2) - (this.DIAMETER / 2);
		yPos = Pong.fieldHeight / 2 - (this.DIAMETER / 2);
		double angle;

		// Generate random angle
		if (firstMove) {
			if (ThreadLocalRandom.current().nextBoolean()) {
				angle = getRandomAngle(140, 220);
			} else {
				angle = getRandomAngle(-40, 40);
			}
			firstMove = false;
		} else {
			if (ballHitBox[0][0] > Pong.fieldWidth) {
				angle = getRandomAngle(140, 220);
			} else {
				angle = getRandomAngle(-40, 40);
			}
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

	public void startBall() {
		resetDirectionAndPosition();
	}
	
	public void setFirstMove() {
		this.firstMove = true;
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
	}

	public double getXPos() {
		return this.xPos;
	}

	public double getYPos() {
		return this.yPos;
	}
}
