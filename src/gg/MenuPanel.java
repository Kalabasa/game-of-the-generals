package gg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JButton start, exit, sound, instructions;

	public MenuPanel(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
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
				Sound.click.play();
				mainFrame.newGame();
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
				Sound.click.play();
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
				Sound.click.play();
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
				mainFrame.instructions();
				Sound.click.play();
			}
		});

		
		menuLabel.add(start);
		menuLabel.add(exit);
		menuLabel.add(sound);
		menuLabel.add(instructions);
		add(menuLabel);
	}
}