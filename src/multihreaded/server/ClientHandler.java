
package multihreaded.server;

/**
 * @class ClientHandler.java
 * @author Luke Fox
 * @description ClientHandler class to process each incoming request from Client
 * on its given thread. Once processed, these requests are sent to the DBController
 * @date 16/11/2019
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextArea;

class ClientHandler extends Thread {

    private final DBController database;
    private final Socket socket;
    private final InetAddress address;
    private final DataInputStream inuputFromClient;
    private final DataOutputStream outputToClient;

    private final JTextArea serverLog;

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

    // switch menu to split incoming requests by their action (requestType) and
    // their data (requestData). The string is split using '-' delimeter
    public void requestHandler(String request) throws SQLException, IOException {
        String requestType = request.split("-")[0];
        String requestData = request.split("-")[1];

        switch (requestType) {
            case "login": {
                try {
                    String foundUser = database.findUserByID(requestData);
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

    // helper function to write to server logs
    private void writeToServer(String request, String response) {
        serverLog.append("Request: " + request + " - From: " + this.address + " || Response: " + response + " AT : "
                + new Date() + "\n");
    }
}
