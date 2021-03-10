import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame{
private static final long serialVersionUID = 1L;
	
	private int frameHeight = 400;
	private int frameWidth = 600;
	
	private JButton startGameButton;
	private JButton exitGameButton;
	private JPanel panel;
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	
	private Game game;
	private DrawPanel dp;
	
	public MainMenu(String name, Game game, DrawPanel dp) {
		this.game = game;
		this.dp = dp;
	
		this.setTitle(name);
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setupUI();
		this.setVisible(true);
	}
	
	private void setupUI() {
		getContentPane().setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		
		this.setupStartGameButton();
		this.setupExitButton();
		
		getContentPane().add(panel, BorderLayout.CENTER);
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
		
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(startGameButton);
		
		panel.add(buttonPanel);
	}
	
	private void setupExitButton() {
		exitGameButton = new JButton();
		exitGameButton.setSize(200,50);
		exitGameButton.setVisible(true);
		exitGameButton.setText("Exit Game");
		exitGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
		
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(exitGameButton);
		
		panel.add(buttonPanel);
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
