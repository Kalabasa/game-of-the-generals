package gg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	JButton start, exit, sound, instructions;
	GamePanel gamePanel;

	public MenuPanel(final JPanel parent) {
		ImageIcon menuBg = new ImageIcon("res/menubg.png");
		JLabel menuLabel = new JLabel(menuBg);

		ImageIcon startButton = new ImageIcon("res/start.png");
		start = new JButton(startButton);
		start.setBorder(null);
		start.setBounds(0, 350, startButton.getIconWidth(),
				startButton.getIconHeight());
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.removeAll();
				parent.revalidate();
				parent.repaint();
				gamePanel = new GamePanel();
				parent.add(gamePanel);
			}
		});

		ImageIcon exitButton = new ImageIcon("res/exit.png");
		exit = new JButton(exitButton);
		exit.setBorder(null);
		exit.setBounds(0, 420, exitButton.getIconWidth(),
				exitButton.getIconHeight());
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		ImageIcon soundButton = new ImageIcon("res/sounds.png");
		sound = new JButton(soundButton);
		sound.setContentAreaFilled(false);
		sound.setBorderPainted(false);
		sound.setBorder(null);
		sound.setBounds(720, 500, soundButton.getIconWidth(),
				soundButton.getIconHeight());
		sound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("sound");
			}
		});

		ImageIcon instructionsButton = new ImageIcon("res/howto.png");
		instructions = new JButton(instructionsButton);
		instructions.setContentAreaFilled(false);
		instructions.setBorderPainted(false);
		instructions.setBorder(null);
		instructions.setBounds(650, 500, soundButton.getIconWidth(),
				soundButton.getIconHeight());
		instructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("instructions");
			}
		});

		menuLabel.add(start);
		menuLabel.add(exit);
		menuLabel.add(sound);
		menuLabel.add(instructions);
		add(menuLabel);
	}
}
