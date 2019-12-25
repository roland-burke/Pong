import java.util.Random;

public class CollisionDetection {
	private static double[][] leftBarHitBox = new double[2][2];
	private static double[][] rightBarHitBox = new double[2][2];
	private static double[][] ballHitBox = new double[2][2];

	private static double[][] directionVector = new double[2][1];

	private static boolean top = true;
	private static boolean bottom = true;
	private static boolean left = true;
	private static boolean right = true;
	
	private static Random r = new Random();

	public static void calculate() {
		// left side
		if (ballHitBox[0][0] <= leftBarHitBox[0][1] && ballHitBox[0][0] > leftBarHitBox[0][0] && ballHitBox[1][0] + 20 > leftBarHitBox[1][0] && ballHitBox[1][0] + 20 < leftBarHitBox[1][1] && left) {
			changeDirectionLeftCollision();
		}
		// right side
		if (ballHitBox[0][0] < rightBarHitBox[0][1] && ballHitBox[0][1] > rightBarHitBox[0][0] && ballHitBox[1][0] + 20 > rightBarHitBox[1][0] && ballHitBox[1][0] + 20 < rightBarHitBox[1][1] && right) {
			changeDirectionRightCollision();
		}
		// top
		if (ballHitBox[1][0] < 0 && top) {
			changeDirectionTopCollision();
		}
		// bottom
		if (ballHitBox[1][1] > 954 && bottom) {
			changeDirectionBottomCollision();
		}
	}

	public static void changeDirectionLeftCollision() {
		directionVector[0][0] = directionVector[0][0] * -1;
		left = false;
		right = top = bottom = true;
		int randomInt = r.nextInt() % 15;
		changeAngle1(randomInt);
	}

	public static void changeDirectionRightCollision() {
		directionVector[0][0] = directionVector[0][0] * -1;
		right = false;
		left = top = bottom = true;
		int randomInt = r.nextInt() % 15;
		changeAngle1(randomInt);
	}

	public static void changeDirectionTopCollision() {
		directionVector[1][0] = directionVector[1][0] * -1;
		top = false;
		left = right = bottom = true;
	}

	public static void changeDirectionBottomCollision() {
		directionVector[1][0] = directionVector[1][0] * -1;
		bottom = false;
		left = right = top = true;
	}

	public static void update(double[][] thatleftBarHitBox, double[][] thatrightBarHitBox, double[][] thatballHitBox) {
		leftBarHitBox = thatleftBarHitBox;
		rightBarHitBox = thatrightBarHitBox;
		ballHitBox = thatballHitBox;
	}

	
	public static double[][] getDirectionVector() {
		return directionVector;
	}
	
	public static void setDirectionVector(double[][] dirVec) {
		directionVector = dirVec;
	}
	
	public static void changeAngle1(int angle) {
		double[][] rotationMatrix = { { Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)) },
				{ Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)) } };
		directionVector = CollisionDetection.matrixMult(rotationMatrix, directionVector);
		angle = 0;
	}
	
	public static void resetCollisionConditions() {
		top = true;
		bottom = true;
		left = true;
		right = true;
	}
	
	public static double[][] matrixMult(double[][] A, double[][] B) {
		if (A[0].length != B.length) {
			System.err.println("Fehler! Matrizen können nicht multipliziert werden.");
			double[][] C = { { 0 }, { 0 } };
			return C;
		}
		double[][] C;
		
		int zeilenA = A.length;
		int spaltenA = A[0].length;
		int spaltenB = B[0].length;
		
		C = new double[zeilenA][spaltenB];
		
		for (int i = 0; i < zeilenA; i++) {
			for (int j = 0; j < spaltenB; j++) {
				C[i][j] = 0;
				for (int k = 0; k < spaltenA; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}
}
