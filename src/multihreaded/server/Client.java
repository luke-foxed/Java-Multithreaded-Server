package multihreaded.server;

/**
 *
 * @author Luke Fox
 * @description Client class which contains may layout which communicates with
 * Server
 *
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Client extends javax.swing.JFrame {

    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private Socket socket;
    private int selectedRow = 0;

    ArrayList<Student> students = new ArrayList<>();
    Student selectedStudent;

    // Define table columns and model with non-editable cells
    String col[] = {"SID", "StudID", "FirstName", "Surname"};
    private final DefaultTableModel tableModel = new DefaultTableModel(col, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Client() throws ClassNotFoundException {

        // Client setup and database connection
        initComponents();
        mainViewVisibility(false);
        connect();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabelLogin = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jButtonLoginOut = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelHeader = new javax.swing.JLabel();
        jLabelWelcomeName = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabelFirstName = new javax.swing.JLabel();
        jTextFieldSurname = new javax.swing.JTextField();
        jLabelSurname = new javax.swing.JLabel();
        jTextFieldSID = new javax.swing.JTextField();
        jLabelSID = new javax.swing.JLabel();
        jTextFieldStudentID = new javax.swing.JTextField();
        jLabelStudentID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStudents = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonSearch = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jLabelSurnameSearch = new javax.swing.JLabel();
        jTextFieldSearchSurname = new javax.swing.JTextField();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabelLogin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogin.setText(" PLEASE ENTER YOUR USER ID");

        jTextFieldLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldLoginKeyPressed(evt);
            }
        });

        jButtonLogin.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLogin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setText("LOG IN");
        jButtonLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonLogin.setBorderPainted(false);
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonLoginOut.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLoginOut.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonLoginOut.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLoginOut.setText("LOG OUT");
        jButtonLoginOut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonLoginOut.setBorderPainted(false);
        jButtonLoginOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLoginOut, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(316, 316, 316))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoginOut, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(132, 94, 194));

        jLabelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelHeader.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHeader.setText("STUDENT MANAGER");

        jLabelWelcomeName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelWelcomeName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWelcomeName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWelcomeName.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelWelcomeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabelHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelWelcomeName)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTextFieldFirstName.setEditable(false);

        jLabelFirstName.setText("First Name:");

        jTextFieldSurname.setEditable(false);

        jLabelSurname.setText("Surname:");

        jTextFieldSID.setEditable(false);

        jLabelSID.setText("SID:");

        jTextFieldStudentID.setEditable(false);

        jLabelStudentID.setText("Student ID:");

        jTableStudents.setAutoCreateRowSorter(true);
        jTableStudents.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableStudents.setSelectionBackground(new java.awt.Color(214, 93, 177));
        jTableStudents.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableStudentsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableStudents);

        jButtonSearch.setBackground(new java.awt.Color(255, 150, 113));
        jButtonSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSearch.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearch.setText("SEARCH");
        jButtonSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonSearch.setBorderPainted(false);
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jButtonReset.setBackground(new java.awt.Color(255, 199, 95));
        jButtonReset.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonReset.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReset.setText("RESET");
        jButtonReset.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonReset.setBorderPainted(false);
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jLabelSurnameSearch.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabelSurnameSearch.setText("Enter Surname:");

        jButtonPrev.setBackground(new java.awt.Color(214, 93, 177));
        jButtonPrev.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonPrev.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPrev.setText("< PREVIOUS");
        jButtonPrev.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonPrev.setBorderPainted(false);
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        jButtonNext.setBackground(new java.awt.Color(255, 111, 145));
        jButtonNext.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonNext.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNext.setText("NEXT >");
        jButtonNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonNext.setBorderPainted(false);
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSurnameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextFieldSearchSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(95, 95, 95)
                        .addComponent(jTextFieldSID, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSurname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelStudentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldFirstName)
                            .addComponent(jTextFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jTextFieldStudentID, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
                .addGap(517, 517, 517))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFirstName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSurname))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelStudentID))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSurnameSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSearchSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        previousStudent();
    }//GEN-LAST:event_jButtonPrevActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextStudent();
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jTextFieldLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLoginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLoginKeyPressed

    private void jTableStudentsMousePressed(java.awt.event.MouseEvent evt) {
        getSelectedRow();
    }

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            searchStudent(jTextFieldSearchSurname.getText());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            reset();         
        } catch (IOException | ClassNotFoundException ex) {
            alertHelper("Error", "Error Clearing Students", ex.getMessage());
        }
        mapToTable(students);
    }

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        if ("".equals(jTextFieldLogin.getText().strip())) {
            alertHelper("WARNING", "Missing Input", "Please Enter A UserID!");
        }
        login(jTextFieldLogin.getText().strip());
    }

    private void jButtonLoginOutActionPerformed(java.awt.event.ActionEvent evt) {
        logout();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void main(String args[]) throws InterruptedException, InvocationTargetException {
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        /* Create and display the form */
 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Client().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonLoginOut;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelHeader;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelSID;
    private javax.swing.JLabel jLabelStudentID;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JLabel jLabelSurnameSearch;
    private javax.swing.JLabel jLabelWelcomeName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableStudents;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldSID;
    private javax.swing.JTextField jTextFieldSearchSurname;
    private javax.swing.JTextField jTextFieldStudentID;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables

    // --- Controller Funtionality --- //
    private void mainViewVisibility(Boolean isVisible) {
        jButtonReset.setVisible(isVisible);
        jButtonSearch.setVisible(isVisible);
        jLabelStudentID.setVisible(isVisible);
        jLabelSurnameSearch.setVisible(isVisible);
        jLabelFirstName.setVisible(isVisible);
        jLabelSID.setVisible(isVisible);
        jLabelSurname.setVisible(isVisible);
        jPanel1.setVisible(isVisible);
        jSeparator3.setVisible(isVisible);
        jTableStudents.setVisible(isVisible);
        jTextFieldFirstName.setVisible(isVisible);
        jTextFieldSearchSurname.setVisible(isVisible);
        jTextFieldSID.setVisible(isVisible);
        jTextFieldStudentID.setVisible(isVisible);
        jTextFieldSurname.setVisible(isVisible);
        jScrollPane1.setVisible(isVisible);
        jLabelWelcomeName.setVisible(isVisible);
        jButtonNext.setVisible(isVisible);
        jButtonPrev.setVisible(isVisible);
        jButtonLoginOut.setEnabled(isVisible);
        jButtonLogin.setEnabled(!isVisible);
    }

    // get the employee from the selected row in table
    private void getSelectedRow() {
        System.out.println("Current selected row: " + jTableStudents.getSelectedRow());
        String ID = jTableStudents.getValueAt(jTableStudents.getSelectedRow(), 0).toString();
        selectedRow = jTableStudents.getSelectedRow();
        for (Student student : students) {
            if (student.getSID().equals(ID)) {
                selectedStudent = student;
                viewStudent(selectedStudent);
            }
        }
    }

    // map selected student to the text fields
    private void viewStudent(Student selectedStudent) {
        jTextFieldFirstName.setText(selectedStudent.getFirstName());
        jTextFieldSurname.setText(selectedStudent.getSurname());
        jTextFieldSID.setText(selectedStudent.getSID());
        jTextFieldStudentID.setText(selectedStudent.getSID());

    }

    // helper function to load employees into table
    private void mapToTable(ArrayList<Student> students) {
        tableModel.setRowCount(0); // reset rows to avoid duplicates
        jTableStudents.setModel(tableModel);

        for (Student student : students) {
            Object[] data = {student.getSID(), student.getStudID(), student.getFirstName(), student.getSurname()};
            tableModel.addRow(data);
        }

        jTableStudents.getRowSorter().toggleSortOrder(0);
        jTableStudents.changeSelection(0, 0, false, false);
        getSelectedRow();
    }

    private void nextStudent() {
        if (selectedRow+1 >= jTableStudents.getRowCount()) {
            alertHelper("ERROR", "No Next Student", "You are at the end of the list");
        } else {
            selectedRow++;
            jTableStudents.setRowSelectionInterval(selectedRow, selectedRow);
            getSelectedRow();
        }
    }

    private void previousStudent() {
        if (selectedRow == 0) {
            alertHelper("ERROR", "No Previous Student", "You are at the start of the list");
        } else {
            selectedRow--;
            jTableStudents.setRowSelectionInterval(selectedRow, selectedRow);
            getSelectedRow();

        }
    }
    
    private void reset() throws IOException, ClassNotFoundException {
        jTextFieldSearchSurname.setText("");
        selectedRow = 0;
        students = fetchAllStudents();
        mapToTable(students);
    }

    // helper function to quickly call a Swing Alert Frame
    private void alertHelper(String type, String title, String message) {
        JFrame frame = null;
        int warningType;
        switch (type.toUpperCase()) {

            case "ERROR":
                warningType = 0;
                break;
            case "INFOMRATION":
                warningType = 1;
                break;
            case "WARNING":
                warningType = 2;
                break;
            default:
                warningType = -1;
                break;
        }

        JOptionPane.showMessageDialog(frame, message, title, warningType);
    }

    // main functionality
    private void connect() {
        try {
            socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            alertHelper("ERROR", "Error connecting to localhost", ex.getMessage() + " - is server running?");
        }
    }

    public void login(String userID) {
        try {
            toServer.writeUTF("login-" + userID);
            if (fromServer.readBoolean()) {
                jTextFieldLogin.setText("");
                jLabelWelcomeName.setText("WELCOME, " + fromServer.readUTF().toUpperCase());
                mainViewVisibility(true);
                try {
                    fetchAllStudents();
                    mapToTable(students);
                } catch (IOException | ClassNotFoundException ex) {
                    alertHelper("ERROR", "Error retrieving students", ex.getMessage());
                }
            } else {
                alertHelper("WARNING", "Login Incorrect", "No User Found!");
            }

        } catch (IOException ex) {
            alertHelper("ERROR", "Error Logging In", ex.getMessage());
        }
    }

    public void logout() {
        try {
            toServer.writeUTF("logout-" + null);
        } catch (IOException e) {
        }
        System.exit(1);
    }

    private ArrayList<Student> fetchAllStudents() throws IOException, ClassNotFoundException {

        toServer.writeUTF("getAllStudents-" + null);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        students = (ArrayList) ois.readUnshared();

        return students;
    }

    private ArrayList<Student> searchStudent(String surname) throws IOException {

        try {
            toServer.writeUTF("searchStudents-" + surname);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            students = (ArrayList) ois.readUnshared();
            mapToTable(students);
        } catch (ClassNotFoundException ex) {
            alertHelper("WARNING", "Warning while Searching Student", ex.getMessage());
        }

        return students;
    }
}

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
