package multihreaded.server;

/**
*
* @author Luke Fox
* @description Server class which handles the client requests to read from the DB and write back to client
* 
*/

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends JFrame {

	// surpress versionUID warning
	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
	private JButton clearLogsBtn = new JButton("CLEAR");
	private JTextArea serverLog = new JTextArea();
	private JLabel header = new JLabel("SERVER LOGS");

	public static void main(String[] args) {
		new Server();
	}

	public Server() {

		// add log window and clear button
		constructFrame();

		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			serverLog.append("Server started at " + new Date() + '\n');

			while (true) {
				Socket newSocket = serverSocket.accept();
				ClientHandler client = new ClientHandler(newSocket, serverLog);
				client.start();
			}

		} catch (IOException ex) {
			
			System.err.println(ex);
		}
	}

	private void constructFrame() {
		panel.setLayout(boxlayout);
		panel.add(header);
		panel.add(serverLog);
		panel.add(clearLogsBtn);

		serverLog.setPreferredSize(new Dimension(600,300));
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


/// Class to handle client input
class ClientHandler extends Thread {

	private DBController database;
	private Socket socket;
	private InetAddress address;
	private DataInputStream inuputFromClient;
	private DataOutputStream outputToClient;

	private JTextArea serverLog;

	public ClientHandler(Socket socket, JTextArea serverLog) throws IOException {

		this.socket = socket;
		this.inuputFromClient = new DataInputStream(socket.getInputStream());
		this.outputToClient = new DataOutputStream(socket.getOutputStream());

		this.address = socket.getInetAddress();
		this.database = new DBController();
		this.serverLog = serverLog;
		database.run();
	}

	/*
	 * The method that runs when the thread starts
	 */
	@Override
	public void run() {
		try {
			while (true) {

				// Receive input from client
				requestHandler(inuputFromClient.readUTF());
			}
		} catch (SQLException | IOException e) {
			System.out.println("Termination - Connection Terminated");
			this.interrupt();
		}
	}

	public void requestHandler(String request) throws SQLException, IOException {
		// split string into type and data
		String requestType = request.split("-")[0];
		String requestData = request.split("-")[1];

		switch (requestType) {
		case "login": {
			try {
				String foundUser = database.findStudentByID(requestData);

				if (foundUser != null) {
					outputToClient.writeBoolean(true);
					outputToClient.writeUTF(foundUser);
					writeToServer("Login", "Welcome " + foundUser);
				} else {
					outputToClient.writeBoolean(false);
					writeToServer("Login", "No User Found");
				}
			} catch (SQLException ex) {
				System.out.println("Error finding User! \n " + ex.getMessage());

			}
			break;
		}

		case "getAllStudents": {
			ArrayList<Student> students = database.getStudents();
			writeToServer("Get All Students", "Retrieved [" + students.size() + "] students");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUnshared(students);
			break;
		}

		case "searchStudents": {
			ArrayList<Student> students = database.searchStudent(requestData);
			writeToServer("Search Students",
					"Found [" + students.size() + "] students from surname [" + requestData + "]");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUnshared(students);
			break;
		}
		
		case "logout": {
			// close socket and interrupt thread
			socket.close();
			this.interrupt();
			writeToServer("Logout", "User logged out");
			break;
		}

		}
	}

	private void writeToServer(String request, String response) {
		serverLog.append("Request: " + request + " - From: " + this.address + " || Response: " + response + " AT : "
				+ new Date() + "\n");
	}
}

/// Class to access DB and return data
class DBController {

	private final String studentsTable = "students";
	private final String usersTable = "users";
	private Connection conn = null;

	public Connection getConnection() throws SQLException {
		Connection conn;
		Properties connectionProps = new Properties();
		String userName = "root";
		String password = "";
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		String serverName = "localhost";
		int portNumber = 3306;
		String dbName = "dist_sys_assignment_2";
		conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName,
				connectionProps);
		return conn;
	}

	ArrayList<Student> getStudents() throws SQLException {
		ArrayList<Student> students = new ArrayList<>();
		String command = "SELECT * FROM " + this.studentsTable;
		ResultSet result = executeSelectQuery(conn, command);
		return formatStudents(students, result);
	}

	String findStudentByID(String userID) throws SQLException {
		String foundUser = null;
		String command = "SELECT * FROM " + this.usersTable + " WHERE `UID` = \'" + userID + "\'";
		ResultSet result = executeSelectQuery(conn, command);
		if (!result.isBeforeFirst()) {
			return null;
		} else {
			while (result.next()) {
				foundUser = result.getString("UNAME");
			}
			return foundUser;
		}
	}

	ArrayList<Student> searchStudent(String surname) throws SQLException {
		ArrayList<Student> student = new ArrayList<>();
		String command = "SELECT * FROM `students` WHERE `SNAME` = \'" + surname + "\'";
		ResultSet result = executeSelectQuery(conn, command);
		return formatStudents(student, result);
	}

	private ArrayList<Student> formatStudents(ArrayList<Student> students, ResultSet result) throws SQLException {
		while (result.next()) {
			Student student = new Student(result.getString("SID"), result.getString("STUD_ID"),
					result.getString("FNAME"), result.getString("SNAME"));
			students.add(student);
		}
		return students;
	}

	private ResultSet executeSelectQuery(Connection conn, String command) {
		Statement stmt;
		ResultSet result = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	void run() {
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
	}
}
