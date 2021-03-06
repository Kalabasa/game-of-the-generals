package gg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsPanel2 extends JPanel {
	private static final long serialVersionUID = 1L;

	public InstructionsPanel2(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));

		ImageIcon instructionsBg2 = new ImageIcon(getClass().getResource(
				"/playingpieces2.png"));
		JLabel instructionsLabel2 = new JLabel(instructionsBg2);
		add(instructionsLabel2);

		ImageIcon returnButton = new ImageIcon(getClass().getResource(
				"/return.png"));
		JButton returnBut = new JButton(returnButton);
		returnBut.setContentAreaFilled(false);
		returnBut.setBorderPainted(false);
		returnBut.setBorder(null);
		returnBut.setBounds(620, 15, returnButton.getIconWidth(),
				returnButton.getIconHeight());
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.returnToMainMenu();
			}
		});

		ImageIcon backButton = new ImageIcon(getClass()
				.getResource("/back.png"));
		JButton back = new JButton(backButton);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setBorder(null);
		back.setBounds(700, 15, backButton.getIconWidth(),
				backButton.getIconHeight());
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.instructions();
			}
		});

		instructionsLabel2.add(returnBut);
		instructionsLabel2.add(back);

	}
}
