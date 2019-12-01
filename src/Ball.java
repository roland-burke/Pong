import java.util.concurrent.ThreadLocalRandom;

public final class Ball {
	private DrawPanel dp;
	
	private double xPos = Main.frameWidth / 2 - 20;
	private double yPos = Main.frameHeight / 2 - 20;
	private double radius = 40;
	private double speed = 10;
	private double[][] directionVector = new double[2][1];
	private double[][] angleZero = { { speed }, { 0 } }; //Speed
	
	private double[][] ballHitBox = { {xPos, xPos + radius},
			{yPos, yPos + radius} };
	
	private double[][] leftBarHitBox;
	private double[][] rightBarHitBox;

	public Ball(DrawPanel dp) {
		this.dp = dp;
	}

	public void move(Bar leftBar, Bar rightBar) {
		ballHitBox[0][0] = xPos;
		ballHitBox[0][1] = xPos + radius;
		ballHitBox[1][0] = yPos;
		ballHitBox[1][1] = yPos + radius;
		leftBarHitBox = leftBar.getHitBox();
		rightBarHitBox = rightBar.getHitBox();
			
		CollisionDetection.update(leftBarHitBox, rightBarHitBox, ballHitBox);
		CollisionDetection.calculate();
		directionVector = CollisionDetection.getDirectionVector();
		xPos += directionVector[0][0];
		yPos += directionVector[1][0];
		
		dp.setxPosBall(xPos);
		dp.setyPosBall(yPos);		
	}

	
	public void resetDirectionAndPosition() {
		// Ball in der Mitte platzieren
		xPos = Main.frameWidth / 2 - 20;
		yPos = Main.frameHeight / 2 - 20;
		
		// Zufaelligen Winkel erstellen
		double angle;
		if(ballHitBox[0][0] < -40) {
			angle = randomAngle(-40, 40, 8, -8);
		} else if (ballHitBox[0][0] > 1400) {
			angle = randomAngle(140, 220, 188, 172);
		} else {
			angle = randomAngle(-40, 40, 8, -8);
		}
		
		double[][] rotationMatrix = { { Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)) },
				{ Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)) } };
		directionVector = CollisionDetection.matrixMult(rotationMatrix, angleZero);
		CollisionDetection.setDirectionVector(directionVector);
		dp.setxPosBall(xPos);
		dp.setyPosBall(yPos);
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
	
	private double randomAngle(int min, int max, int min2, int max2) {
		double angle = ThreadLocalRandom.current().nextInt(min, max);
		while(angle < min2 && angle > max2) {
			angle = ThreadLocalRandom.current().nextInt(min, max);
		}
		return angle;
	}
}
