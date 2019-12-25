
public class CollisionDetection {
	private static boolean top = true;
	private static boolean bottom = true;
	private static boolean left = true;
	private static boolean right = true;

	public static void calculate(Ball ball, Bar leftBar, Bar rightBar) {
		double[][] leftBarHitBox = leftBar.getHitBox();
		double[][] rightBarHitBox = rightBar.getHitBox();
		double[][] ballHitBox = ball.getHitBox();
		
		// left side
		if (ballHitBox[0][0] <= leftBarHitBox[0][1] && ballHitBox[0][0] > leftBarHitBox[0][0] && ballHitBox[1][0] + 20 > leftBarHitBox[1][0] && ballHitBox[1][0] + 20 < leftBarHitBox[1][1] && left) {
			changeDirectionLeftCollision(ball);
		}
		// right side
		if (ballHitBox[0][0] < rightBarHitBox[0][1] && ballHitBox[0][1] > rightBarHitBox[0][0] && ballHitBox[1][0] + 20 > rightBarHitBox[1][0] && ballHitBox[1][0] + 20 < rightBarHitBox[1][1] && right) {
			changeDirectionRightCollision(ball);
		}
		// top
		if (ballHitBox[1][0] < 0 && top) {
			changeDirectionTopCollision(ball);
		}
		// bottom
		if (ballHitBox[1][1] > 954 && bottom) {
			changeDirectionBottomCollision(ball);
		}
	}

	private static void changeDirectionLeftCollision(Ball ball) {
		ball.changeDirectionVectorX();
		left = false;
		right = top = bottom = true;
	}

	private static void changeDirectionRightCollision(Ball ball) {
		ball.changeDirectionVectorX();
		right = false;
		left = top = bottom = true;
	}

	private static void changeDirectionTopCollision(Ball ball) {
		ball.changeDirectionVectorY();
		top = false;
		left = right = bottom = true;
	}

	private static void changeDirectionBottomCollision(Ball ball) {
		ball.changeDirectionVectorY();
		bottom = false;
		left = right = top = true;
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
