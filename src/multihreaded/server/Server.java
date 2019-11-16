package multihreaded.server;

/**
 * @class Server.java
 * @author Luke Fox
 * @description Server class which creates server socket on port 8000. Then 
 * creates an instance of ClientHandler runnable to process incoming requests.
 * @date 16/11/2019
 * @instructions After running this class, next run Client.java
 */

import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Date;

public class Server extends JFrame {

	// surpress versionUID warning
	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();
	private BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
	private JButton clearLogsBtn = new JButton("CLEAR");
	private JTextArea serverLog = new JTextArea();
	private JLabel header = new JLabel("SERVER LOGS");

	ServerSocket serverSocket = null;

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		constructFrame();

		// set up server socket and start client thread
		try {
			serverSocket = new ServerSocket(8000);
			serverLog.append("Server started at " + new Date() + '\n');

			while (true) {
				Socket newSocket = serverSocket.accept();
				ClientHandler client = new ClientHandler(newSocket, serverLog);
				client.start();
			}

		} catch (IOException ex) {
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
			System.err.println(ex);
		}
	}

	// display server logs text area and clear button
	private void constructFrame() {

		JScrollPane scrollPane = new JScrollPane(serverLog);
		scrollPane.setBounds(3, 3, 600, 300);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel.setLayout(boxlayout);
		panel.add(header);
		panel.add(scrollPane);
		panel.add(clearLogsBtn);

		header.setAlignmentX(CENTER_ALIGNMENT);
		clearLogsBtn.setAlignmentX(CENTER_ALIGNMENT);
		clearLogsBtn.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				serverLog.setText("");
			}
		});

		setResizable(false);
		setTitle("Server");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
	}
}
