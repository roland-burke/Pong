
public final class Game {
	private Bar leftBar;
	private Bar rightBar;
	private ScoreBoard score;
	private Ball ball;
	private DrawPanel dp;
	
	private Thread loop;

	private boolean running = false;
	private volatile boolean paused = false;

	private boolean isWPressed;
	private boolean isSPressed;
	private boolean isUpPressed;
	private boolean isDownPressed;
	private boolean isF3Pressed = false;

	private int fps = 120;
	private int frameCount = 0;
	
	
	public Game(Ball ball, Bar leftBar, Bar rightBar, ScoreBoard score, DrawPanel dp) {
		this.dp = dp;
		this.leftBar = leftBar;
		this.rightBar = rightBar;
		this.ball = ball;
		this.score = score;
	}

	private void runGame() {
		double GAME_HERTZ = 60;
		// Calculate how many ns each frame should take for our target game hertz.
		double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new render.
		// If you're worried about visual hitches more than perfect timing, set this to
		// 1.
		final int MAX_UPDATES_BEFORE_RENDER = 1;
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
		loop = new Thread() {
			public void run() {
				runGame();
			}
		};
		loop.start();
	}

	public void pause() {
		if(paused) {
			this.paused = false;
		} else {
			this.paused = true;
		}
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
	}

	private void render(float interpolation) {
		frameCount++;
		dp.setInterpolation(interpolation);
		dp.repaint();
	}

	public void reset() {
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
	
	public void showFps() {
		if(!isF3Pressed) {
			isF3Pressed = true;
			dp.setInfo("FPS: " + fps);
		} else {
			isF3Pressed = false;
			dp.setInfo("");
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

	public void setIsF3PRessed(boolean bool) {
		this.isF3Pressed = bool;
	}
}
