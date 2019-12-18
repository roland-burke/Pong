import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public final class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int panelWidth = getWidth(); // *1349* weil der Frame 6 pxl breiter ist als das Panel
	private int panelHeight = getHeight(); // *954* weil der Frame 46 pxl hoeher ist als das Panel
	
	private int winnerX = 1500;
	private int winnerY = 1500;
	
	private boolean celebration;
	
	private String score = "0 : 0";
	private String winner = "";
	private String info = "";
	
	private Ball ball;
	private Bar leftBar;
	private Bar rightBar;
	
	private Drop[] drops;


	public DrawPanel(Ball ball, Bar leftBar, Bar rightBar) {
		//xPosLeftBar = 40; //40
		//yPosLeftBar = (panelHeight / 2) - (leftBarHeight / 2); // +46 weil der Frame 46 pxl hoeher ist als das Panel
		//xPosRightBar = panelWidth - xPosLeftBar - rightBarWidth; // -6 weil der Frame 6 pxl breiter ist als das Panel
		//yPosRightBar = (panelHeight / 2) - (rightBarHeight / 2);
		this.ball = ball;
		this.leftBar = leftBar;
		this.rightBar = rightBar;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(panelWidth / 2, panelHeight / 2);
		setBackground(new Color(50, 50, 120)); // dunkelblau
		
		g.setColor(new Color(180, 180, 180)); // grau
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("Spieler 1", 100, 30); // Spieler1
		g.drawString("Spieler 2", 1180, 30); // Spieler2
		
		g.setColor(new Color(200, 21, 0)); // rot
		g.fillRect((int) leftBar.getXPos(), (int) leftBar.getYPos(), (int) leftBar.getWidth(), (int) leftBar.getHeight()); // Linker Balken
		g.fillRect((int) rightBar.getXPos(), (int) rightBar.getYPos(), (int) rightBar.getWidth(), (int) rightBar.getHeight()); // Rechter Balken
		
		g.setColor(new Color(180, 180, 180)); // grau
		g.fillOval((int) ball.getXPos(), (int) ball.getYPos(), 40, 40); // Ball
		
		g.setFont(new Font("Arial", Font.PLAIN, 45));
		g.drawString(score, 650, 50); // ScoreBoard
		
		g.setColor(new Color(180, 255, 0)); // gruen
		g.setFont(new Font("Arial", Font.PLAIN, 100));
		g.drawString(winner, winnerX, winnerY); // Gewinner
		
		g.setColor(Color.WHITE); // weiﬂ
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(info, 750, 950); // FPS
		
		if(celebration) {
			for(Drop drop : drops) {
				g.setColor(drop.color);
				g.fillRect(drop.xPos, drop.yPos, (int) drop.width, drop.height);
			}
		}
	}
	
	/*
	 * set
	 * #######################################################################################################################
	 */
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
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
	
	public void setCelebration(boolean bool, Drop[] drops) {
		celebration = bool;
		this.drops = drops;
	}
	
	public void setCelebration(boolean bool) {
		celebration = bool;
	}
}

