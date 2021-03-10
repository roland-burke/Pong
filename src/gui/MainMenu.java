package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Ball;
import game.DrawPanel;
import game.Game;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	private int frameHeight = 400;
	private int frameWidth = 600;
	public static final Color background = new Color(186, 204, 245);

	private JButton startGameButton;
	private JButton exitGameButton;
	private JButton singlePlayerButton;
	private JButton controlsButton;
	private JPanel panel;

	private Game game;
	private DrawPanel dp;
	private Ball ball;

	public MainMenu(String name, Game game, DrawPanel dp, Ball ball) {
		this.game = game;
		this.dp = dp;
		this.ball = ball;

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
		panel.setBackground(background);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		this.setupStartGameButton();
		this.setupSinglePlayerButton();
		this.setupControlsButton();
		this.setupExitButton();

		getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void setupStartGameButton() {
		startGameButton = new JButton();
		startGameButton.setSize(200, 50);
		startGameButton.setText("Start Game");
		startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		panel.add(startGameButton);
		startGameButton.setVisible(true);
	}
	
	private void setupSinglePlayerButton() {
		singlePlayerButton = new JButton();
		singlePlayerButton.setSize(200, 50);
		singlePlayerButton.setText("Start Game (Singleplayer)");
		singlePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		singlePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//startGame();
			}
		});

		panel.add(singlePlayerButton);
		startGameButton.setVisible(true);
	}
	
	private void setupControlsButton() {
		controlsButton = new JButton();
		controlsButton.setSize(200, 50);
		controlsButton.setText("Controls");
		controlsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Controls();
			}
		});

		panel.add(controlsButton);
		startGameButton.setVisible(true);
	}

	private void setupExitButton() {
		exitGameButton = new JButton();
		exitGameButton.setSize(200, 50);
		exitGameButton.setText("Exit Game");
		exitGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});

		panel.add(exitGameButton);
		exitGameButton.setVisible(true);
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
		
		new Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				ball.startBall();
			}
		}, 3000);
		
	}

}
