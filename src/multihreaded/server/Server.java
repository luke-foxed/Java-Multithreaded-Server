package multihreaded.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luke Fox
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends JFrame {

    private JTextArea jta = new JTextArea();

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        // Place text area on the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);
        setTitle("Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        try {

            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server started at " + new Date() + '\n');

            while (true) {
                Socket newSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(newSocket);
                client.start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}

/// Class to handle client input
class ClientHandler extends Thread {

    private DBController database;
    private Socket socket;
    private InetAddress address;
    private DataInputStream inuputFromClient;
    private DataOutputStream outputToServer;

    public ClientHandler(Socket socket) throws IOException {

        this.socket = socket;
        this.inuputFromClient = new DataInputStream(socket.getInputStream());
        this.outputToServer = new DataOutputStream(socket.getOutputStream());
        this.address = socket.getInetAddress();
        this.database = new DBController();
        database.run();
    }

    /*
         * The method that runs when the thread starts
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Receive input from client
                requestHandler(inuputFromClient.readUTF());
            } catch (IOException e) {
                System.err.println(e + " on " + socket);
            }
        }
    }

    public void requestHandler(String request) {
        System.out.println("Recieved: " + request);
        String requestType = request.split("-")[0];
        String requestData = request.split("-")[1];
        switch (requestType) {
            case "login": {
                try {
                    Student student = database.findStudentByID(requestData);

                    if (student != null) {
                        System.out.println("Welcome " + student.getFirstName() + " " + student.getSurname());
                    } else {
                        System.out.println("No Student Found!");
                    }
                } catch (SQLException ex) {
                    System.out.println("Error finding Student! \n " + ex.getMessage());;
                }
            }

            case "getAllUsers": {

            }

            case "getUser": {

            }
        }
    }
}

/// Class to access DB
class DBController {

    private final String tableName = "students";
    private Connection conn;

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
        conn = DriverManager.getConnection(
                "jdbc:mysql://" + serverName + ":"
                + portNumber + "/" + dbName, connectionProps);
        System.out.println(conn);
        return conn;
    }

    ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String command = "SELECT * FROM " + this.tableName;
        ResultSet result = executeSelectQuery(conn, command);
        return formatStudents(students, result);
    }

    Student findStudentByID(String userID) throws SQLException {
        ArrayList<Student> student = new ArrayList<>();
        String command = "SELECT * FROM `students` WHERE `SID` = \'" + userID + "\'";
        ResultSet result = executeSelectQuery(conn, command);
        if (!result.isBeforeFirst()) {
            return null;
        } else {
            return formatStudents(student, result).get(0);
        }
    }

    ArrayList searchStudent(String surname) throws SQLException {
        ArrayList<Student> student = new ArrayList<>();
        String command = "SELECT * FROM `students` WHERE `SNAME` = \'" + surname + "\'";
        ResultSet result = executeSelectQuery(conn, command);
        return formatStudents(student, result);
    }

    private ArrayList<Student> formatStudents(ArrayList<Student> students, ResultSet result) throws SQLException {
        System.out.println("RESULT: " + result.toString());
        while (result.next()) {
            Student student = new Student(
                    result.getInt("SID"),
                    result.getInt("STUD_ID"),
                    result.getString("FNAME"),
                    result.getString("SNAME"));
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
