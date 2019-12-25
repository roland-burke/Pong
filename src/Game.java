
import javax.swing.JFrame;

public final class Game{
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
	private final int leftBarInitY = (954 / 2) - (leftBarInitHeight / 2); // (dp.getPanelHeight() / 2) - (leftBarInitHeight / 2)
	
	private final int rightBarInitWidth = leftBarInitWidth;
	private final int rightBarInitHeight = 140;
	private final int rightBarInitX = 1394 - leftBarInitX - rightBarInitWidth;
	private final int rightBarInitY = (954 / 2) - (rightBarInitHeight / 2); // (dp.getPanelHeight() / 2) - (rightBarInitHeight / 2)
	
	private int ballXPos;
	private int ballYPos;
	private final int ballRadius = 40;
	
	
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
		ballXPos = frame.getWidth() / 2 - 20;
		ballYPos = frame.getHeight() / 2 - 20;
		
		leftBar = new Bar(leftBarInitX, leftBarInitY, leftBarInitWidth, leftBarInitHeight);
		rightBar = new Bar(rightBarInitX, rightBarInitY, rightBarInitWidth, rightBarInitHeight);
		ball = new Ball(ballXPos, ballYPos, ballRadius);
		score = new ScoreBoard();
		dp = new DrawPanel(ball, leftBar, rightBar, score);
		frame.add(dp);
		ball.resetDirectionAndPosition();
		
		frame.setVisible(true);
	}
	
	private void run() {
		while(running) {
			if(isF3Pressed) {
				dp.setInfo("FPS: " + Integer.toString(fps));
			} else {
				dp.setInfo("");
			}
			if(!paused) {
				tick();				
				render();
			}
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
		run();
	}
	
	public void setPause(boolean paused) {
		this.paused = paused;
	}
	
	private void tick() {
		CollisionDetection.update(leftBar.getHitBox(), rightBar.getHitBox(), ball.getHitBox());
		CollisionDetection.calculate();
		
		ball.move();
		
		if(score.updateScore(ball.getHitBox())) {
			ball.resetDirectionAndPosition();
		}
		
		if(score.checkForWinner()) {
			ball.resetDirectionAndPositionAndSpeed();
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
