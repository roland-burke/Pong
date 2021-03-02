import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame{
private static final long serialVersionUID = 1L;
	
	private int frameHeight = 400;
	private int frameWidth = 600;
	
	private JButton startGameButton;
	
	private Game game;
	private DrawPanel dp;
	
	public MainMenu(String name, Game game, DrawPanel dp) {
		this.game = game;
		this.dp = dp;
		
		this.setLayout(new FlowLayout());
		
	
		this.setTitle(name);
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setupUI();
		this.setVisible(true);
	}
	
	private void setupUI() {
		this.setupStartGameButton();
		this.setupExitButton();
	}
	
	private void setupStartGameButton() {
		startGameButton = new JButton();
		startGameButton.setSize(200,50);
		startGameButton.setVisible(true);
		startGameButton.setText("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		this.add(startGameButton);
	}
	
	private void setupExitButton() {
		startGameButton = new JButton();
		startGameButton.setSize(200,50);
		startGameButton.setVisible(true);
		startGameButton.setText("Exit Game");
		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
		
		this.add(startGameButton);
	}
	
	private void exitGame() {
		this.setVisible(false);
		this.dispose();
	}
	
	private void startGame() {
		this.setVisible(false);
		this.dispose();
		new Gui("Pong", game, dp);
		game.start();
	}
	
	

}
