package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFrameUserInterface extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	PlanningApp planApp;

	private String title = "Planning Application";

	protected JTextField input = new JTextField();
	protected JTextArea output = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(output);

	public JFrameUserInterface(PlanningApp planApp) {
		this.planApp = planApp;

		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(600, 800));
		
		Font font = new Font(Font.MONOSPACED, Font.BOLD,14);

		TextFieldListener tfListener = new TextFieldListener();
		input.addActionListener(tfListener);

		// Don't let the user change the output.
		output.setEditable(false);
		output.setAutoscrolls(true);
		output.setFont(font);

		// Add all the widgets to the applet
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(input, BorderLayout.SOUTH);
		this.pack();
		input.requestFocus(); // start with focus on this field

		this.setVisible(true);
	}

	public void readInput(String input) {

	}

	private class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String temp = input.getText();
			input.setText("");
			readInput(temp);
		}
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
