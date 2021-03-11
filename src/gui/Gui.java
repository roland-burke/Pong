package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.StaticDrawPanel;
import game.ElementsDrawPanel;
import game.Game;
import game.PlayerEnum;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private final int frameHeight = 1000;
	public final int frameWidth = 1400;
	
	public Gui(String name, Game game, StaticDrawPanel dp, ElementsDrawPanel edp) {
		this.game = game;
		
		this.setTitle(name);
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(dp);
		this.setGlassPane(edp);
		edp.setVisible(true);
		this.addKeyListener(getKeylistener());
		
		this.setVisible(true);
	}
	
	public KeyListener getKeylistener() {
		KeyListener kl = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// no code in here
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_W:
					game.setIsWPRessed(false);
					break;
				case KeyEvent.VK_S:
					game.setIsSPRessed(false);
					break;
				case KeyEvent.VK_SPACE: // Shoot
					game.shoot(PlayerEnum.Player1);
					break;
				case KeyEvent.VK_UP:
					game.setIsUPPRessed(false);
					break;
				case KeyEvent.VK_DOWN:
					game.setIsDownPRessed(false);
					break;
				case KeyEvent.VK_ENTER:
					game.shoot(PlayerEnum.Player2);
					break;
				case KeyEvent.VK_F3:
					game.showFps();
					break;
				case KeyEvent.VK_F2:
					game.showDebugInfo();
					break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_W:
					game.setIsWPRessed(true);
					break;
				case KeyEvent.VK_S:
					game.setIsSPRessed(true);
					break;
				case KeyEvent.VK_UP:
					game.setIsUPPRessed(true);
					break;
				case KeyEvent.VK_DOWN:
					game.setIsDownPRessed(true);
					break;
				case KeyEvent.VK_R:
					game.reset();
					break;
				case KeyEvent.VK_P:
					game.pause();
					break;
				}
			}
		};
		return kl;
	}
}
