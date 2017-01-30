package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import server.Server;

public class ClientGUI extends Server  {

	protected ClientGUI() {

		createGUI();
	}

	protected JFrame frameGUI;
	protected JPanel inputPanelGUI, messagePanelGUI;
	protected JTextField textinputPanelGUI;
	protected JButton sendMessageButtonGUI;
	public static JTextArea incomingMessagesGUI;

	protected void createGUI()  {
		frameGUI = new JFrame("WorldWide Instant Messaging");
		frameGUI.setSize(800, 500);
		frameGUI.setResizable(false);
		frameGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		inputPanelGUI = new JPanel(new BorderLayout());
		frameGUI.add(inputPanelGUI, BorderLayout.SOUTH);

		textinputPanelGUI = new JTextField(20);
		textinputPanelGUI.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER && textinputPanelGUI.getText() != null) {
					incomingMessagesGUI.setText(textinputPanelGUI.getText());
					textinputPanelGUI.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		inputPanelGUI.add(textinputPanelGUI);

		sendMessageButtonGUI = new JButton("Send");
		sendMessageButtonGUI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textinputPanelGUI.getText() != null) {
					sendMessage(textinputPanelGUI.getText());
				}

			}
		});
		inputPanelGUI.add(sendMessageButtonGUI, BorderLayout.EAST);

		messagePanelGUI = new JPanel(new BorderLayout());
		messagePanelGUI.setBorder(new EmptyBorder(10, 10, 10, 10));
		frameGUI.add(messagePanelGUI, BorderLayout.NORTH);

		incomingMessagesGUI = new JTextArea("Hello");
		messagePanelGUI.add(incomingMessagesGUI);

	   
		frameGUI.setVisible(true);

	}
	

	
	
	
	
}
