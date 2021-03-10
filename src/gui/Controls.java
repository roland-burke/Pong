package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Controls extends JFrame {
	private static final long serialVersionUID = 1L;

	private int frameHeight = 200;
	private int frameWidth = 400;

	private JButton exitControlsButton;
	private JPanel panel;
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	private JLabel label1 = new JLabel("Player 1: W, S");
	private JLabel label2 = new JLabel("Player 2: UP, DOWN");
	private JLabel label3 = new JLabel("Pause: P");
	private JLabel label4 = new JLabel("Restart: R");
	private JLabel label5 = new JLabel("Debug info: F2");
	private JLabel label6 = new JLabel("Show FPS: F3");


	public Controls() {
		this.setTitle("Controls");
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setupUI();
		this.setVisible(true);
	}

	private void setupUI() {
		getContentPane().setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBackground(MainMenu.background);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		this.setupLabels();
		this.setupExitButton();

		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	private void setupLabels() {
	
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
	}
	
	private void setupExitButton() {
		exitControlsButton = new JButton();
		exitControlsButton.setSize(200, 50);
		exitControlsButton.setText("Close Controls");
		exitControlsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitControls();
			}
		});

		buttonPanel.add(exitControlsButton);
		buttonPanel.setBackground(MainMenu.background);
		panel.add(buttonPanel);
		exitControlsButton.setVisible(true);
	}
	
	private void exitControls() {
		this.setVisible(false);
		this.dispose();
	}

}
