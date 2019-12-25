import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.PLAIN, 45));
		g2d.translate(panelWidth / 2, panelHeight / 2);
		setBackground(new Color(50, 50, 120)); // dunkelblau
		
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine((int) getWidth() / 2, 0, getWidth() / 2, getHeight());
		
		g2d.setColor(new Color(180, 180, 180)); // grau
		g2d.setFont(new Font("Arial", Font.PLAIN, 30));
		g2d.drawString(scoreboard.getP1(), 100, 30); // Spieler1
		g2d.drawString(scoreboard.getP2(), 1180, 30); // Spieler2
		
		g2d.setColor(new Color(200, 21, 0)); // rot
		g2d.fillRect((int) leftBar.getXPos(), (int) leftBar.getYPos(), (int) leftBar.getWidth(), (int) leftBar.getHeight()); // Linker Balken
		g2d.fillRect((int) rightBar.getXPos(), (int) rightBar.getYPos(), (int) rightBar.getWidth(), (int) rightBar.getHeight()); // Rechter Balken
		
		g2d.setColor(new Color(180, 180, 180)); // grau
		g2d.fillOval((int) ball.getXPos(), (int) ball.getYPos(), 40, 40); // Ball
		
		g2d.setFont(new Font("Arial", Font.PLAIN, 45));
		g2d.drawString(scoreboard.getScoreBoard(), (getWidth() / 2) - (metrics.stringWidth(scoreboard.getScoreBoard()) / 2), 50); // ScoreBoard
		
		g2d.setColor(new Color(180, 255, 0)); // gruen
		g2d.setFont(new Font("Arial", Font.PLAIN, 100));
		g2d.drawString(winner, winnerX, winnerY); // Gewinner
		
		g2d.setColor(Color.WHITE); // weiﬂ
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));
		g2d.drawString(info, 1250, 950); // FPS

		
		if(scoreboard.checkForWinner()) {
			setWinner(scoreboard.getWinningPlayer());
		} else {
			setWinner("");
		}
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
}

