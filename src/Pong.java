
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public final class Pong {

	private static JFrame frame;
	public static int frameHeight = 1000;
	public static int frameWidth = 1400;
	private static Game game;
	private static boolean ppressed = false;
	
	public static void main(String[] args) {
		createGameFrame();
		game = new Game(frame);
		game.start();
	}
	
	public static int getWidth() {
		return frame.getWidth();
	}

	public static void createGameFrame() {
		frame = new JFrame("Pong");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		frame.addKeyListener(new KeyListener() {
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
				case KeyEvent.VK_R:
					game.setIsRPRessed(true);
					break;
				case KeyEvent.VK_P:
					if(ppressed) {
						ppressed = false;
						game.setPause(ppressed);
						break;
					} else {
						ppressed = true;
						game.setPause(ppressed);;
						break;
					}
				case KeyEvent.VK_F3:
					if(!game.getIsF3Pressed()) {
						game.setIsF3PRessed(true);
						break;
					}
					game.setIsF3PRessed(false);
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
				}
			}
		});
	}
}

