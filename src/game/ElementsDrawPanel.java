package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class ElementsDrawPanel extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private final Color BAR_COLOR = new Color(200, 21, 0); // red
	private final Color BALL_COLOR = new Color(180, 180, 180); // gray
	private final Color PROJECTILE_COLOR = new Color(255, 229, 36); // yellow
	
	private Ball ball;
	private Bar leftBar;
	private Bar rightBar;
	private Projectile projectiles[] = null;
	
	public ElementsDrawPanel(Ball ball, Bar leftBar, Bar rightBar) {
		this.ball = ball;
		this.leftBar = leftBar;
		this.rightBar = rightBar;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		updateValues();
		drawBars(g2d);
		drawBall(g2d);
		if(projectiles != null) {
			for (Projectile p : projectiles) {
				if(p != null) {
					drawProjectile(g2d, p);					
				}
			}
		}
		
	}
	
	private void updateValues() {
		double rightBarXPos = getWidth() - Pong.LEFT_BAR_X_POS - rightBar.getWidth();
		rightBar.setXPos(rightBarXPos);
		Pong.fieldWidth = getWidth();
		Pong.setFrameHeight(getHeight());
	}
	
	private void drawProjectile(Graphics2D g2d, Projectile p) {
		g2d.setColor(PROJECTILE_COLOR);
		g2d.fillRect((int) p.getXPos(), (int) p.getYPos(), (int) p.getWidth(), (int) p.getHeight());
	}
	
	private void drawBars(Graphics2D g2d) {
		g2d.setColor(BAR_COLOR);
		g2d.fillRect((int) leftBar.getXPos(), (int) leftBar.getYPos(), (int) leftBar.getWidth(), (int) leftBar.getHeight()); // left bar
		g2d.fillRect((int) rightBar.getXPos(), (int) rightBar.getYPos(), (int) rightBar.getWidth(), (int) rightBar.getHeight()); // right bar
		
		g2d.setColor(Color.BLACK);
		g2d.drawRect((int) leftBar.getXPos(), (int) leftBar.getYPos(), (int) leftBar.getWidth(), (int) leftBar.getHeight()); // left bar
		g2d.drawRect((int) rightBar.getXPos(), (int) rightBar.getYPos(), (int) rightBar.getWidth(), (int) rightBar.getHeight()); // right bar
	}
	
	private void drawBall(Graphics2D g2d) {
		g2d.setColor(BALL_COLOR);
		g2d.fillOval((int) ball.getXPos(), (int) ball.getYPos(), 40, 40); // Ball
	}
	
	public void setProjectiles(Projectile[] projectiles) {
		this.projectiles = projectiles;
	}
	
}
