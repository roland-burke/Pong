
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public final class Game{
	private DrawPanel dp;
	private Bar leftBar;
	private Bar rightBar;
	private ScoreBoard score;
	private Ball ball;
	private JFrame frame;
	
	private boolean running = false;
	
	private boolean isWPressed;
	private boolean isSPressed;
	private boolean isUpPressed;
	private boolean isDownPressed;
	private boolean isRPressed;
	private boolean isPPressed;
	private boolean isF3Pressed = false;
	
	private final int leftBarInitWidth = 20;
	private final int leftBarInitHeight = 140;
	private final int leftBarInitX = 40;
	private final int leftBarInitY = (954 / 2) - (leftBarInitHeight / 2); // (dp.getPanelHeight() / 2) - (leftBarInitHeight / 2)
	
	private final int rightBarInitWidth = leftBarInitWidth;
	private final int rightBarInitHeight = 140;
	private final int rightBarInitX = 1394 - leftBarInitX - rightBarInitWidth;
	private final int rightBarInitY = (954 / 2) - (rightBarInitHeight / 2); // (dp.getPanelHeight() / 2) - (rightBarInitHeight / 2)
	
	private Drop[] drops;
	private boolean celeb;
	
	public int counter = 0;
	public double startTimeCalcFPS = System.nanoTime();
	
	private final int FRAME_CAP = 120;
	private int fps = 0;
	private final int NANO_SECOND = 1000000000;
	private int rate = NANO_SECOND / FRAME_CAP;
	private int milliSleep = rate / 1000000;
	private int nanoSleep = rate - (milliSleep * 1000000);
	
	public Game(JFrame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		leftBar = new Bar(leftBarInitX, leftBarInitY, leftBarInitWidth, leftBarInitHeight);
		rightBar = new Bar(rightBarInitX, rightBarInitY, rightBarInitWidth, rightBarInitHeight);
		ball = new Ball();
		score = new ScoreBoard();
		dp = new DrawPanel(ball, leftBar, rightBar, score);
		frame.add(dp);
		ball.resetDirectionAndPosition();
		
		int nDrops;
		nDrops = ThreadLocalRandom.current().nextInt(200, 250 + 1);
		drops = new Drop[nDrops];
		for(int i = 0; i < nDrops; ++i) {
			int colorR = ThreadLocalRandom.current().nextInt(0, 255); // 0, 80
			int colorG = ThreadLocalRandom.current().nextInt(0, 255); // 0, 100
			int colorB = ThreadLocalRandom.current().nextInt(0, 255); // 190, 255
			int yPos = ThreadLocalRandom.current().nextInt(-550, -450); // -550 -450
			int xPos = ThreadLocalRandom.current().nextInt(0, 1500);
			double speed = ThreadLocalRandom.current().nextDouble(0.1, 1.0);
			double grav = ThreadLocalRandom.current().nextDouble(0.1, 0.5);
			Drop drop = new Drop(xPos, yPos, 4 + grav * 7, 50, speed, grav, colorR, colorG, colorB);
			drops[i] = drop;
		}
		frame.setVisible(true);
	}
	
	private void run() {
		while(running) {
			if(isPPressed) {
				System.out.println("Pause");
				running = false;
			}
			if(celeb) {
				for(Drop drop : drops) {
					drop.move();
				}
			}
			if(isF3Pressed) {
				dp.setInfo("FPS: " + Integer.toString(fps));
			} else {
				dp.setInfo("");
			}
			tick();
			render();
			try {
				Thread.sleep(milliSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			calculateFPS();
		}
	}
	
	public void start() {
		running = true;
		isPPressed = false;
		run();
	}
	
	public void pause() {
		isPPressed = true;
	}
	
	private void tick() {
		ball.move(leftBar, rightBar);
		
		if(score.updateScore(ball.getHitBox())) {
			ball.resetDirectionAndPosition();
		}
		
		if(score.checkForWinner()) {
			ball.resetDirectionAndPositionAndSpeed();
			celeb = true;
			dp.setCelebration(true, drops);
		}
		
		moveBars();
		
		if (isRPressed) {
			reset();
			isRPressed = false;
		}
	}
	
	private void render() {
		frame.repaint();
	}
	
	public void calculateFPS() {
		counter += 1;
		if(System.nanoTime() - startTimeCalcFPS >= NANO_SECOND) {
			fps = counter;
			counter = 0;
			startTimeCalcFPS = System.nanoTime();
		}
	}
	
	private void reset() {
		ball.resetDirectionAndPosition();
		score.reset();
		celeb = false;
		dp.setCelebration(false);
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
