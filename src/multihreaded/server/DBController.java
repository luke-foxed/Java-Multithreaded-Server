package multihreaded.server;

/**
 * @class DBController.java
 * @author Luke Fox
 * @description DBController class which takes in commands from the ClientHandler.
 * The controller then issues these commands to the MySQL DB and returns the results.
 * @date 16/11/2019
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

class DBController {

    private final String studentsTable = "students";
    private final String usersTable = "users";
    private Connection conn = null;

    // establish connection to MySQL DB
    public Connection getConnection() throws SQLException {
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

    // get all students from DB
    ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String command = "SELECT * FROM " + this.studentsTable;
        ResultSet result = executeSelectQuery(conn, command);
        return formatStudents(students, result);
    }

    // find student by ID
    String findUserByID(String userID) throws SQLException {
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

    // find student by surname
    ArrayList<Student> searchStudent(String surname) throws SQLException {
        ArrayList<Student> student = new ArrayList<>();
        String command = "SELECT * FROM `students` WHERE `SNAME` = \'" + surname + "\'";
        ResultSet result = executeSelectQuery(conn, command);
        return formatStudents(student, result);
    }

    // parse resultset into arraylist of students
    private ArrayList<Student> formatStudents(ArrayList<Student> students, ResultSet result) throws SQLException {
        while (result.next()) {
            Student student = new Student(result.getString("SID"), result.getString("STUD_ID"),
                    result.getString("FNAME"), result.getString("SNAME"));
            students.add(student);
        }
        return students;
    }

    // helper function for executing select queries
    private ResultSet executeSelectQuery(Connection conn, String command) {
        Statement stmt;
        ResultSet result = null;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(command);
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return result;
    }

    // main run method to start connection
    void run() {
        try {
            conn = this.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database - " + e.getMessage());
        }
    }
}
