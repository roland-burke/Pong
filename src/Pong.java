
public final class Pong {
	public static int frameHeight = 1000;
	public static int frameWidth = 1400;
	public static int fieldWidth = 1400;
	public static final int FRAME_OFFSET = 20;
	
	// Game settings
	public final static double BALL_SPEED = 20;
	public final static int BAR_SPEED = 28;
	public final static int WINNING_SCORE = 10;
	
	
	// Left Bar
	public static final int LEFT_BAR_X_POS = 40;
	private static final int LEFT_BAR_INITIAL_Y_POS = Pong.getBarInitialYPos();
	
	// Right Bar
	private static final int RIGHT_BAR_X_POS = 1385 - LEFT_BAR_X_POS - Bar.WIDTH;
	private static final int RIGHT_BAR_INITIAL_Y_POS = Pong.getBarInitialYPos();
	
	public static void main(String[] args) {
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		Bar leftBar = new Bar(LEFT_BAR_X_POS, LEFT_BAR_INITIAL_Y_POS);
		Bar rightBar = new Bar(RIGHT_BAR_X_POS, RIGHT_BAR_INITIAL_Y_POS);
		Ball ball = new Ball();
		ScoreBoard scoreBoard = new ScoreBoard(player1, player2);
		
		DrawPanel dp = new DrawPanel(ball, leftBar, rightBar, scoreBoard);
		Game game = new Game(ball, leftBar, rightBar, scoreBoard, dp);
		new MainMenu("Pong - Main Menu", game, dp);
	}
	
	private static int getBarInitialYPos() {
		return (Pong.frameHeight / 2) - (Bar.HEIGHT / 2);
	}
	
	public static int getFieldHeight() {
		return Pong.frameHeight - FRAME_OFFSET;
	}
	
	public static void setFrameHeight(int newFrameHeight) {
		Pong.frameHeight = newFrameHeight;
	}
}

