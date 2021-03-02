
public final class Pong {
	public static int frameHeight = 1000;
	private static int frameWidth = 1400;

	private static Game game;
	
	public static final int FRAME_OFFSET = 20;
	public static int fieldWidth = 1400;
	
	private static final int barWidth = 22;
	private static final int barHeight = 140;
	
	public static final int leftBarX = 40;
	private static final int leftBarY = (954 / 2) - (barHeight / 2);
	private static final int rightBarX = 1385 - leftBarX - barWidth;
	private static final int rightBarY = (954 / 2) - (barHeight / 2);
	
	public static void main(String[] args) {
		
		Bar leftBar = new Bar(leftBarX, leftBarY, barWidth, barHeight);
		Bar rightBar = new Bar(rightBarX, rightBarY, barWidth, barHeight);
		Ball ball = new Ball(frameWidth / 2 - 28, frameHeight / 2 - 28, 40);
		ScoreBoard score = new ScoreBoard();
		
		DrawPanel dp = new DrawPanel(ball, leftBar, rightBar, score);
		game = new Game(ball, leftBar, rightBar, score, dp);
		new MainMenu("Pong - Main Menu", game, dp);
	}
	
	public static int getFieldHeight() {
		return frameHeight - FRAME_OFFSET;
	}
	
	public static void setFrameHeight(int newFrameHeight) {
		Pong.frameHeight = newFrameHeight;
	}
}

