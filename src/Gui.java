import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private int frameHeight = 1000;
	private int frameWidth = 1400;
	private Game game;
	
	public Gui(String name, Game game, DrawPanel dp) {
		this.game = game;
		
		this.setTitle(name);
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//frame.setResizable(false);
		this.add(dp);
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
				case KeyEvent.VK_UP:
					game.setIsUPPRessed(false);
					break;
				case KeyEvent.VK_DOWN:
					game.setIsDownPRessed(false);
					break;
				case KeyEvent.VK_F3:
					game.showFps();
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