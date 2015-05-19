package gg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public InstructionsPanel(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));

		ImageIcon instructionsBg1 = new ImageIcon(getClass().getResource(
				"/playingpieces.png"));
		JLabel instructionsLabel1 = new JLabel(instructionsBg1);
		add(instructionsLabel1);

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

		ImageIcon nextButton = new ImageIcon(getClass()
				.getResource("/next.png"));
		JButton next = new JButton(nextButton);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setBorder(null);
		next.setBounds(700, 15, nextButton.getIconWidth(),
				nextButton.getIconHeight());
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.instructions2();
			}
		});

		instructionsLabel1.add(returnBut);
		instructionsLabel1.add(next);
	}
}
