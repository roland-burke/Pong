import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public final class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Ball ball;
	private Bar leftBar;
	private Bar rightBar;
	private ScoreBoard scoreboard;
	
	private int panelWidth = getWidth(); // *1349* weil der Frame 6 pxl breiter ist als das Panel
	private int panelHeight = getHeight(); // *954* weil der Frame 46 pxl hoeher ist als das Panel
	
	private int winnerX = 1500;
	private int winnerY = 1500;
	
	private String winner = "";
	private String info = "";
	
	private float interpolation = 1;
	
	private final Color BAR_COLOR = new Color(200, 21, 0); // red
	private final Color BALL_COLOR = new Color(180, 180, 180); // gray
	private final Color PLAYER_COLOR = new Color(180, 180, 180); // gray
	private final Color WINNER_COLOR = new Color(180, 255, 0); // green
	private final Color BACK_COLOR = new Color(50, 50, 120); // darkblue
	
	private final Font SCORE_FONT = new Font("Arial", Font.PLAIN, 45);
	private final Font WINNER_FONT = new Font("Arial", Font.PLAIN, 100);
	private final Font FPS_FONT = new Font("Arial", Font.PLAIN, 20);
	private final Font PLAYER_FONT = new Font("Arial", Font.PLAIN, 30);
	
	private final Stroke MIDDLELINE_STROKE = new BasicStroke(2);
	

	public DrawPanel(Ball ball, Bar leftBar, Bar rightBar, ScoreBoard scoreBoard) {
		this.ball = ball;
		this.leftBar = leftBar;
		this.rightBar = rightBar;
		this.scoreboard = scoreBoard;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.translate(panelWidth / 2, panelHeight / 2);
		setBackground(BACK_COLOR);
		
		if(scoreboard.checkForWinner()) {
			setWinner(scoreboard.getWinningPlayer());
		} else {
			setWinner("");
		}
		
		updateValues();
		drawMiddleLine(g2d);
		drawPlayerNames(g2d);
		drawBars(g2d);
		drawBall(g2d);
		drawScoreBoard(g2d);
		drawWinner(g2d);
		drawFPS(g2d);
		
	}
	
	private void updateValues() {
		double rightBarXPos = getWidth() - Pong.leftBarX - rightBar.getWidth();
		rightBar.setXPos(rightBarXPos);
		Pong.fieldWidth = getWidth();
		Pong.setFrameHeight(getHeight());
	}
	
	private void drawMiddleLine(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.setStroke(MIDDLELINE_STROKE);
		g2d.drawLine((int) getWidth() / 2, 0, getWidth() / 2, getHeight());
	}
	
	private void drawPlayerNames(Graphics2D g2d) {
		drawString(g2d, scoreboard.getP1(), 100, 30, PLAYER_COLOR, PLAYER_FONT);
		drawString(g2d, scoreboard.getP2(), getWidth() - 180, 30, PLAYER_COLOR, PLAYER_FONT);
	}
	
	private void drawBars(Graphics2D g2d) {
		g2d.setColor(BAR_COLOR);
		g2d.fillRect((int) leftBar.getXPos(), (int) leftBar.getYPos(), (int) leftBar.getWidth(), (int) leftBar.getHeight()); // left bar
		g2d.fillRect((int) rightBar.getXPos(), (int) rightBar.getYPos(), (int) rightBar.getWidth(), (int) rightBar.getHeight()); // right bar
	}
	
	private void drawBall(Graphics2D g2d) {
		g2d.setColor(BALL_COLOR);
		g2d.fillOval((int) ball.getXPos(), (int) ball.getYPos(), 40, 40); // Ball
	}
	
	private void drawScoreBoard(Graphics2D g2d) {
		FontMetrics metrics = g2d.getFontMetrics(SCORE_FONT);
		drawString(g2d, scoreboard.getScoreBoard(), (getWidth() / 2) - (metrics.stringWidth(scoreboard.getScoreBoard()) / 2), 50, PLAYER_COLOR, SCORE_FONT);
	}
	
	private void drawWinner(Graphics2D g2d) {
		drawString(g2d, winner, winnerX, winnerY, WINNER_COLOR, WINNER_FONT);
	}
	
	private void drawFPS(Graphics2D g2d) {
		drawString(g2d, info, 1250, 950, Color.WHITE, FPS_FONT);
	}
	
	private void drawString(Graphics2D g2d, String text, int xPos, int yPos, Color color, Font font) {
		g2d.setColor(color);
		g2d.setFont(font);
		g2d.drawString(text, xPos, yPos);
	}
	
	/*
	 * set
	 * #######################################################################################################################
	 */
	
	public void setWinner(String winner) {
		this.winner = winner + " hat gewonnen!";
		this.winnerX = 160;
		this.winnerY = 300;
		if(winner.isEmpty()) {
			winnerX = 1500;
			winnerY = 1500;
		}
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public void setInterpolation(float interpolation) {
		this.interpolation = interpolation;
	}
}

