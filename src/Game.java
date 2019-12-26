
import javax.swing.JFrame;

public final class Game {
	private DrawPanel dp;
	private Bar leftBar;
	private Bar rightBar;
	private ScoreBoard score;
	private Ball ball;
	private JFrame frame;

	private boolean running = false;
	private boolean paused = false;

	private boolean isWPressed;
	private boolean isSPressed;
	private boolean isUpPressed;
	private boolean isDownPressed;
	private boolean isRPressed;
	private boolean isF3Pressed = false;

	private final int leftBarInitWidth = 20;
	private final int leftBarInitHeight = 140;
	private final int leftBarInitX = 40;
	private final int leftBarInitY = (954 / 2) - (leftBarInitHeight / 2); // (dp.getPanelHeight() / 2) -
																			// (leftBarInitHeight / 2)

	private final int rightBarInitWidth = leftBarInitWidth;
	private final int rightBarInitHeight = 140;
	private final int rightBarInitX = 1394 - leftBarInitX - rightBarInitWidth;
	private final int rightBarInitY = (954 / 2) - (rightBarInitHeight / 2); // (dp.getPanelHeight() / 2) -
																			// (rightBarInitHeight / 2)

	private int ballXPos;
	private int ballYPos;
	private final int ballRadius = 40;

	private int fps = 180;
	private int frameCount = 0;
	
	
	public Game(JFrame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		ballXPos = frame.getWidth() / 2 - 28;
		ballYPos = frame.getHeight() / 2 - 28;

		leftBar = new Bar(leftBarInitX, leftBarInitY, leftBarInitWidth, leftBarInitHeight);
		rightBar = new Bar(rightBarInitX, rightBarInitY, rightBarInitWidth, rightBarInitHeight);
		ball = new Ball(ballXPos, ballYPos, ballRadius);
		score = new ScoreBoard();
		dp = new DrawPanel(ball, leftBar, rightBar, score);
		frame.add(dp);
		ball.resetDirectionAndPosition();

		frame.setVisible(true);
	}

	private void runGame() {
		double GAME_HERTZ = 120;
		// Calculate how many ns each frame should take for our target game hertz.
		double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new render.
		// If you're worried about visual hitches more than perfect timing, set this to
		// 1.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		final double TARGET_FPS = 120;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		
		// Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			if (!paused) {
				// Do as many game updates as we need to, potentially playing catchup.
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
					tick();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				// If for some reason an update takes forever, we don't want to do an insane
				// number of catchups.
				// If you were doing some sort of game that needed to keep EXACT time, you would
				// get rid of this.
				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				// Render. To do so, we need to calculate interpolation for a smooth render.
				float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
				render(interpolation);
				lastRenderTime = now;

				// Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					fps = frameCount;
					frameCount = 0;
					lastSecondTime = thisSecond;
				}

				// Yield until it has been at least the target time between renders. This saves
				// the CPU from hogging.
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
						&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();

					// This stops the app from consuming all your CPU. It makes this slightly less
					// accurate, but is worth it.
					// You can remove this line and it will still work (better), your CPU just
					// climbs on certain OSes.
					// FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a
					// look at different peoples' solutions to this.
					try {
						Thread.sleep(1);
					} catch (Exception e) {
					}

					now = System.nanoTime();
				}
			}
		}
	}

	public void start() {
		running = true;
		Thread loop = new Thread() {
			public void run() {
				runGame();
			}
		};
		loop.start();
	}

	public void setPause(boolean paused) {
		this.paused = paused;
	}

	private void tick() {
		CollisionDetection.calculate(ball, leftBar, rightBar);

		ball.move();

		if (score.updateScore(ball.getHitBox())) {
			ball.resetDirectionAndPosition();
		}

		if (score.checkForWinner()) {
			ball.resetDirectionAndPositionAndSpeed();
		}

		moveBars();

		if (isRPressed) {
			reset();
			isRPressed = false;
		}

		if (isF3Pressed) {
			dp.setInfo("FPS: " + Integer.toString(fps));
		} else {
			dp.setInfo("");
		}
	}

	private void render(float interpolation) {
		frameCount++;
		dp.setInterpolation(interpolation);
		dp.repaint();
	}

	private void reset() {
		ball.resetDirectionAndPosition();
		score.reset();
	}

	public void moveBars() {
		if (isWPressed) {
			leftBar.moveUp();
		}
		if (isSPressed) {
			leftBar.moveDown();
		}
		if (isUpPressed) {
			rightBar.moveUp();
		}
		if (isDownPressed) {
			rightBar.moveDown();
		}
	}

	public void setIsWPRessed(boolean bool) {
		this.isWPressed = bool;
	}

	public void setIsSPRessed(boolean bool) {
		this.isSPressed = bool;
	}

	public void setIsUPPRessed(boolean bool) {
		this.isUpPressed = bool;
	}

	public void setIsDownPRessed(boolean bool) {
		this.isDownPressed = bool;
	}

	public void setIsRPRessed(boolean bool) {
		this.isRPressed = bool;
	}

	public void setIsF3PRessed(boolean bool) {
		this.isF3Pressed = bool;
	}

	public boolean getIsF3Pressed() {
		return this.isF3Pressed;
	}
}
