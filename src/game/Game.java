package game;

public final class Game {
	private Bar leftBar;
	private Bar rightBar;
	private ScoreBoard score;
	private Ball ball;
	private StaticDrawPanel dp;
	private ElementsDrawPanel edp;

	private Thread loop;

	private boolean running = false;
	private volatile boolean paused = false;

	private boolean isWPressed;
	private boolean isSPressed;
	private boolean isUpPressed;
	private boolean isDownPressed;
	private boolean isF3Pressed = false; // FPS
	private boolean isF2Pressed = false; // debug

	private Player player1;
	private Player player2;
	private Projectile projectiles[] = new Projectile[Player.MAX_AMMO * 2];
	private int projectileCounter = 0;

	private int fps = 120;
	private int frameCount = 0;

	public Game(Ball ball, Bar leftBar, Bar rightBar, ScoreBoard score, StaticDrawPanel dp, ElementsDrawPanel edp, Player player1, Player player2) {
		this.dp = dp;
		this.edp = edp;
		this.leftBar = leftBar;
		this.rightBar = rightBar;
		this.ball = ball;
		this.score = score;
		this.player1 = player1;
		this.player2 = player2;
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
						//Thread.sleep(1);
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
		if (paused) {
			this.paused = false;
		} else {
			this.paused = true;
		}
	}

	private void tick() {
		dp.setInfo("FPS: " + fps);
		for (Projectile p : projectiles) {
			if (p != null) {
				if (!p.move()) {
					p = null;
					this.edp.setProjectiles(projectiles);
				}
			}
		}
		ball.move(leftBar, rightBar);
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
		// dp.setInterpolation(interpolation);
		edp.repaint();
	}

	public void reset() {
		ball.setFirstMove();
		ball.resetDirectionAndPosition();
		player1.reset();
		player2.reset();
		projectiles = new Projectile[Player.MAX_AMMO * 2];
		projectileCounter = 0;
		edp.setProjectiles(null);
		
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

	public void shoot(PlayerEnum player) {
		if (player == PlayerEnum.Player1) {
			if (player1.shoot()) {
				projectiles[projectileCounter++] = new Projectile(player, leftBar);
				edp.setProjectiles(projectiles);
			}
		} else {
			if (player2.shoot()) {
				projectiles[projectileCounter++] = new Projectile(player, rightBar);
				edp.setProjectiles(projectiles);
			}
		}
	}

	public void showFps() {
		if (!isF3Pressed) {
			isF3Pressed = true;
		} else {
			isF3Pressed = false;
		}
		dp.setFps(isF3Pressed);
	}

	public void showDebugInfo() {
		if (!isF2Pressed) {
			isF2Pressed = true;
		} else {
			isF2Pressed = false;
		}
		dp.setDebug(isF2Pressed);
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
