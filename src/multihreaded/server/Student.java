package multihreaded.server;

/**
 * @class Student.java
 * @author Luke Fox
 * @description Student class to parse and store objects returned from the 
 * DBController
 * @date 16/11/2019
 */

import java.io.Serializable;

final class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    String SID;
    String studID;
    String fName;
    String sName;

    public Student(String SID, String studID, String fName, String sName) {
        this.setSID(SID);
        this.setStudID(studID);
        this.setFirstName(fName);
        this.setSurname(sName);
    }

    // Getters and Setters
    public String getSID() {
        return SID;
    }

    public String getStudID() {
        return studID;
    }

    public String getFirstName() {
        return fName;
    }

    public String getSurname() {
        return sName;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public void setStudID(String studID) {
        this.studID = studID;
    }

    public void setFirstName(String fName) {
        this.fName = fName;
    }

    public void setSurname(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "Student{" + "SID=" + SID + ", studID=" + studID + ", firstName=" + fName + ", surname=" + sName + '}';
    }
}
