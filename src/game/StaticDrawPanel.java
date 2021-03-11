package game;
import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JPanel;

public final class StaticDrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Ball ball;
	private Bar leftBar;
	private Bar rightBar;
	private ScoreBoard scoreboard;
	private Player player1;
	private Player player2;
	
	private int winnerX = 1500;
	private int winnerY = 1500;
	
	private String winner = "";
	private String info = "";
	private boolean debug = false;
	private boolean fps = false;
	private boolean ready = true;
	
	private final Color PLAYER_COLOR = new Color(180, 180, 180); // gray
	private final Color WINNER_COLOR = new Color(180, 255, 0); // green
	private final Color BACK_COLOR = new Color(50, 50, 120); // darkblue
	private final Color READY_COLOR = new Color(240, 156, 22); // orange
	
	private final Font SCORE_FONT = new Font("Arial", Font.PLAIN, 45);
	private final Font WINNER_FONT = new Font("Arial", Font.PLAIN, 100);
	private final Font FPS_FONT = new Font("Arial", Font.PLAIN, 20);
	private final Font PLAYER_FONT = new Font("Arial", Font.PLAIN, 25);
	
	private final Stroke MIDDLELINE_STROKE = new BasicStroke(2);
	

	public StaticDrawPanel(Ball ball, Bar leftBar, Bar rightBar, ScoreBoard scoreBoard, Player player1, Player player2) {
		this.ball = ball;
		this.leftBar = leftBar;
		this.rightBar = rightBar;
		this.scoreboard = scoreBoard;
		this.player1 = player1;
		this.player2 = player2;
		setBackground(BACK_COLOR);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(scoreboard.checkForWinner()) {
			setWinner(scoreboard.getWinningPlayerName());
			drawWinner(g2d);
		}
		
		drawGameBackground(g2d);
		drawPlayerStats(g2d);
		drawScoreBoard(g2d);
		if(ready) {
			drawReady(g2d);
		}
		if(fps) {
			drawFPS(g2d);			
		}
		if(debug) {
			drawDebug(g2d);
		}
		
	}
	
	private void drawGameBackground(Graphics2D g2d) {
		drawMiddleLine(g2d);
		drawCircle(g2d);
	}
	
	private void drawCircle(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.setStroke(MIDDLELINE_STROKE);
		g2d.drawLine((int) getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
	}
	
	private void drawMiddleLine(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.setStroke(MIDDLELINE_STROKE);
		g2d.drawLine((int) getWidth() / 2, 0, getWidth() / 2, getHeight());
	}
	
	private void drawPlayerStats(Graphics2D g2d) {
		int player1xPos = 120;
		int player2xPos = getWidth() - 280;
		drawString(g2d, scoreboard.getPlayer1Name() + player1.getLifeString(), player1xPos, 24, PLAYER_COLOR, PLAYER_FONT);
		drawString(g2d, scoreboard.getPlayer2Name() + player2.getLifeString(), player2xPos, 24, PLAYER_COLOR, PLAYER_FONT);
		
		drawString(g2d, "Ammo: " + player1.getAmmoString(), player1xPos, 50, PLAYER_COLOR, PLAYER_FONT);
		drawString(g2d, "Ammo: " + player2.getAmmoString(), player2xPos, 50, PLAYER_COLOR, PLAYER_FONT);
	}
	
	private void drawScoreBoard(Graphics2D g2d) {
		FontMetrics metrics = g2d.getFontMetrics(SCORE_FONT);
		drawString(g2d, scoreboard.getScoreBoard(), (getWidth() / 2) - (metrics.stringWidth(scoreboard.getScoreBoard()) / 2), 50, PLAYER_COLOR, SCORE_FONT);
	}
	
	private void drawWinner(Graphics2D g2d) {
		drawString(g2d, winner, winnerX, winnerY, WINNER_COLOR, WINNER_FONT);
	}
	
	private void drawReady(Graphics2D g2d) {
		drawString(g2d, "Get Ready!", getWidth() / 2 - 250, getHeight() / 2 - 200, READY_COLOR, WINNER_FONT);
	}
	
	private void drawFPS(Graphics2D g2d) {
		drawString(g2d, info, 1200, 920, Color.WHITE, FPS_FONT);
	}
	
	private void drawDebug(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(1));
		int xpos = 80;
		drawString(g2d, "frame height: " + getHeight(), xpos, 840, Color.WHITE, FPS_FONT);
		drawString(g2d, "frame width: " + getWidth(), xpos, 860, Color.WHITE, FPS_FONT);
		drawString(g2d, "ball (top left) x: " + ball.getXPos() + ", y: " + ball.getXPos(), xpos, 880, Color.WHITE, FPS_FONT);
		drawString(g2d, "left bar (top left) x: " + leftBar.getXPos() + ", y: " + leftBar.getYPos(), xpos, 900, Color.WHITE, FPS_FONT);
		drawString(g2d, "right bar (top left) x: " + rightBar.getXPos() + ", y: " + rightBar.getYPos(), xpos, 920, Color.WHITE, FPS_FONT);
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
	
	public void setReadyFalse() {
		this.ready = false;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void setFps(boolean fps) {
		this.fps = fps;
	}
}

