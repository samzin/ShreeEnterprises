/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_billing.v3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Login_Form extends javax.swing.JFrame {

    int days;

    public Login_Form() {
        initComponents();
        showDate();
       

        updateDays();
        getPath();
        getBackup();

      
    }

   

    String msg = "";

    
   

    public void showDate() {
        Date date = new Date();

        SimpleDateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd");
        String todaydate = dateformate.format(date);
        lblDate.setText(todaydate);
        SimpleDateFormat dateformate1 = new SimpleDateFormat("dd.MM.yyyy");
        String todaydate1 = dateformate1.format(date);
        lblTime1.setText(todaydate1);
        final DateFormat timeFormat = new SimpleDateFormat("hh:mm");
        String time = timeFormat.format(date);
//        lblTime.setText(time);

        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                lblTime.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
    }

    public void getBackup() {
        String backuppath = path;
        String da = lblDate.getText();

        String Database = "dhanlaxmi";
        String dbname = Database.concat(da);
        BackUp b = new BackUp();
        try {
            byte[] data = b.getData("localhost", "3307", "root", "admin", Database).getBytes();
            File filedst = new File(backuppath + "\\" + dbname + ".zip");
            FileOutputStream dest = new FileOutputStream(filedst);
            ZipOutputStream zip = new ZipOutputStream(
                    new BufferedOutputStream(dest));
            zip.setMethod(ZipOutputStream.DEFLATED);
            zip.setLevel(Deflater.BEST_COMPRESSION);
            zip.putNextEntry(new ZipEntry("data.sql"));
            zip.write(data);
            zip.close();
            dest.close();
//            JOptionPane.showMessageDialog(null, "Back Up Successfully." + "\n" + "For Database: " + Database + "\n        " + "On Dated: ", "Database BackUp Wizard", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Back Up Failed." + "\n" + "For Database: " + Database + "\n " + "On     Dated: ", "Database BackUp Wizard", JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
    }

    String path = "";

    public void getPath() {

        try {
            Database db1 = new Database();

            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db1.conn();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabpath where Id='1' ");
            while (rs.next()) {
                path = rs.getString("Path");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        lblTime1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        lblTime = new javax.swing.JLabel();
        lblChangepass = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblLI = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(null);

        lblDate.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 102, 51));
        pnlMain.add(lblDate);
        lblDate.setBounds(1274, 628, 0, 0);

        lblError.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 0, 0));
        pnlMain.add(lblError);
        lblError.setBounds(1150, 690, 170, 34);

        lblTime1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lblTime1.setForeground(new java.awt.Color(255, 102, 102));
        lblTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblTime1);
        lblTime1.setBounds(570, 450, 120, 34);

        txtUsername.setBackground(new java.awt.Color(255, 102, 102));
        txtUsername.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });
        pnlMain.add(txtUsername);
        txtUsername.setBounds(570, 330, 226, 23);

        txtPass.setBackground(new java.awt.Color(255, 102, 102));
        txtPass.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });
        pnlMain.add(txtPass);
        txtPass.setBounds(570, 360, 226, 23);

        lblTime.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 102, 102));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblTime);
        lblTime.setBounds(700, 450, 100, 34);

        lblChangepass.setFont(new java.awt.Font("Calibri", 1, 10)); // NOI18N
        lblChangepass.setForeground(new java.awt.Color(255, 102, 102));
        lblChangepass.setText("Forget Password....");
        lblChangepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChangepassMouseClicked(evt);
            }
        });
        pnlMain.add(lblChangepass);
        lblChangepass.setBounds(570, 420, 99, 13);

        btnSave.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 102, 102));
        btnSave.setText("Login");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlMain.add(btnSave);
        btnSave.setBounds(570, 390, 226, 25);

        lblLI.setForeground(new java.awt.Color(255, 255, 255));
        lblLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2.jpg"))); // NOI18N
        pnlMain.add(lblLI);
        lblLI.setBounds(10, 0, 1330, 730);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1338, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        String username = txtUsername.getText();
        System.out.println("ee");
        try {
            Database db = new Database();
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db.conn();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select * from tabLogininfo where Username = '" + username + "'  ");

            if (rs.next()) {
//                lblLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/li2.jpg")));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_txtUsernameKeyPressed

    public void updateDays() {

        String td = lblDate.getText();
        String temptd = "";
        Date dcal = new Date();

        String yd = "2008-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        try {
            Database db1 = new Database();

            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db1.conn();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabexp ");
            while (rs.next()) {
                temptd = rs.getString("Systoday");
                yd = rs.getString("yestdate");
                Date instdate = rs.getDate("InstDate");
                Date sdate = rs.getDate("Systoday");

                long diff = dcal.getTime() - instdate.getTime();
                long ss = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                long tt = rs.getLong("days");
                days = rs.getInt("days");
                System.out.println("ss " + ss);
                System.out.println("tt " + tt);
                if ((ss >= tt)) {
                    days = (int) ss;
                }
//                System.out.println(days);
            }
            System.out.println("days" + days);
            days++;
            c.setTime(sdf.parse(yd));
            c.add(Calendar.DATE, 1);
            yd = sdf.format(c.getTime());

            if (td.equals(temptd)) {

            } else {
                st.executeUpdate("UPDATE tabexp SET Systoday='" + lblDate.getText() + "',yestdate='" + yd + "',days='" + days + "'  WHERE Name='GRAMVILLA' ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

        String d = lblDate.getText();
        String di[] = d.split("-");
        String da = di[2];
        int dd = (Integer.parseInt(da)) % 2;

    }

    int count = 0;

    public void wishBirthday_S_or_N() {
        String today = lblDate.getText();

        try {
            Database db1 = new Database();

            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db1.conn();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tabregistration_info   ");

            if (rs.next()) {

                st.executeUpdate("update tabregistration_info set SMS_Status='Sent'   where DOB='" + lblDate.getText() + "'  ");
                st.executeUpdate("update tabregistration_info set SMS_Status='NO'   where DOB !='" + lblDate.getText() + "'  ");

            }

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void wishBirthday_Sent() {
//        for (int i = 0; i < count; i++) {
        wishBirthday_S_or_N();
//    }
    }

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        txtPass.setBorder(BorderFactory.createEtchedBorder());
        int s = evt.getKeyCode();
        if (s == evt.VK_ENTER) {
            String username = txtUsername.getText();
            String password = txtPass.getText();
            try {
                Database db = new Database();
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db.conn();
                Statement st = (Statement) con.createStatement();
                ResultSet rs = st.executeQuery("select * from tabLogininfo where Username = '" + username + "' &&  Pass= '" + password + "'");
                if (rs.next()) {
                    String role = rs.getString("Role");

                    MF mainform = new MF(role, username);
                    mainform.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please Enter Username/Password");
                }
                // System.out.println(loggedInUser);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String username = txtUsername.getText();
        String password = txtPass.getText();
        try {
            if (username.equals("")) {
                txtUsername.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                txtPass.setBorder(BorderFactory.createEtchedBorder());
            } else if (password.equals("")) {
                txtUsername.setBorder(BorderFactory.createEtchedBorder());
                txtPass.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            } else {
                Database db = new Database();
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db.conn();
                Statement st = (Statement) con.createStatement();
                ResultSet rs = st.executeQuery("select * from tabLogininfo where Username = '" + username + "' &&  Pass= '" + password + "'");
                if (rs.next()) {
                    String role = rs.getString("Role");

                    MF mainform = new MF(role, username);
                    mainform.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please Enter Correct Username & Password");
                }
                // System.out.println(loggedInUser);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lblChangepassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangepassMouseClicked
//        Change_Pass change = new Change_Pass();
//        change.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_lblChangepassMouseClicked

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        String username = txtUsername.getText();
        System.out.println("ee");
        try {
            Database db = new Database();
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) db.conn();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select * from tabLogininfo where Username = '" + username + "'  ");

            if (rs.next()) {
//                lblLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/li1.jpg")));
//                txtPass.requestFocusInWindow();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblChangepass;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblLI;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTime1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
