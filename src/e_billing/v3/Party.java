package e_billing.v3;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Party extends javax.swing.JFrame {

    String role, username, sr;
    Statement st;
    Connection con;

    public Party() {
        initComponents();
        getDatabase_Connections();
        showDate();
        getShopname();

    }

    public Party(String Role, String Username) {
        initComponents();
        getDatabase_Connections();
        role = Role;
        username = Username;
        txtUser.setText(username);
        getShopname();
        showDate();
        txtContact.setDocument(new Party.JTextFieldLimit(10));

    }

    public Party(String Role, String Username, String Sr) {
        initComponents();
        getDatabase_Connections();
        role = Role;
        username = Username;
        sr = Sr;
        txtUser.setText(username);
        showDate();
        getShopname();

        btnSave.setText("Update");
        getItem_Details();
        txtContact.setDocument(new Party.JTextFieldLimit(10));
    }

    public void getDatabase_Connections() {
        try {
            Database db = new Database();
            con = (Connection) db.conn();
            st = (Statement) con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e + "@ getconnection");
        }
    }

    public void getShopname() {

        try {

            ResultSet rs = st.executeQuery("select * from tabshopname ");

            while (rs.next()) {
                jLabel1.setText(rs.getString("Shopname"));
                String font = rs.getString("Fontname");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }

    public void getItem_Details() {

        try {
            ResultSet rs = st.executeQuery("select * from tabparty where Sr='" + sr + "' ");

            if (rs.next()) {
                txtPartyname.setText(rs.getString("Partyname"));

                txtContact.setText(rs.getString("Contact"));
                txtAddress.setText(rs.getString("Address"));
                cmbState.setSelectedItem(rs.getString("State"));
                code = rs.getString("Code");
                txtGSTIN.setText(rs.getString("GSTIN"));
                txtEmailID.setText(rs.getString("Emailid"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }

    public void showDate() {
        Date date = new Date();

        SimpleDateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd");
        String todaydate = dateformate.format(date);
        lblDate.setText(todaydate);

        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();

            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
    }

    public class JTextFieldLimit extends PlainDocument {

        private int limit;
        // optional uppercase conversion
        private boolean toUppercase = false;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
            toUppercase = upper;
        }

        public void insertString(int offset, String str, AttributeSet attr)
                throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                if (toUppercase) {
                    str = str.toUpperCase();
                }
                super.insertString(offset, str, attr);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlMain1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JLabel();
        txtBillNo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtPartyname = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        cmbState = new javax.swing.JComboBox();
        txtGSTIN = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtEmailID = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        lblPartyname = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Company = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        Admin = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        Transaction = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        Sale = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        Reports = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        pnlMain1.setBackground(new java.awt.Color(255, 204, 0));

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VaghJai");

        txtUser.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtBillNo.setFont(new java.awt.Font("Tekton Pro Ext", 1, 18)); // NOI18N
        txtBillNo.setForeground(new java.awt.Color(255, 255, 255));
        txtBillNo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("OORT TECHNOLOGY");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contact -  7709013826");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(443, 443, 443)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPartyname.setBackground(new java.awt.Color(240, 240, 240));
        txtPartyname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnSave.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave.setText("Save ");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Party Name  :");

        txtContact.setBackground(new java.awt.Color(240, 240, 240));
        txtContact.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactKeyPressed(evt);
            }
        });

        txtAddress.setBackground(new java.awt.Color(240, 240, 240));
        txtAddress.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        cmbState.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmbState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select State", "Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chattisgarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Lakshadweep Islands", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Pondicherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal" }));
        cmbState.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStateMouseClicked(evt);
            }
        });
        cmbState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStateActionPerformed(evt);
            }
        });

        txtGSTIN.setBackground(new java.awt.Color(240, 240, 240));
        txtGSTIN.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Contact  :");

        jLabel39.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Address  :");

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("State  :");

        jLabel42.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("GSTIN | Unique ID  :");

        txtEmailID.setBackground(new java.awt.Color(240, 240, 240));
        txtEmailID.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Email ID  :");

        lblPartyname.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lblPartyname.setForeground(new java.awt.Color(255, 102, 0));
        lblPartyname.setText("Enter Party Name Unique");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 426, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContact))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGSTIN)
                            .addComponent(txtPartyname, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addComponent(cmbState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAddress)
                            .addComponent(txtEmailID))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPartyname, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPartyname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(lblPartyname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbState, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGSTIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmailID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMain1Layout = new javax.swing.GroupLayout(pnlMain1);
        pnlMain1.setLayout(pnlMain1Layout);
        pnlMain1Layout.setHorizontalGroup(
            pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMain1Layout.setVerticalGroup(
            pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMain1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N

        Company.setText("Company");
        Company.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem10.setText("Logout");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        Company.add(jMenuItem10);

        jMenuItem28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem28.setText("New Login");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        Company.add(jMenuItem28);

        jMenuBar1.add(Company);

        Admin.setText("Adminstration");
        Admin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenu7.setText("Party");
        jMenu7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem12.setText("Add");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem13.setText("List");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        Admin.add(jMenu7);

        jMenu5.setText("Item");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem6.setText("Add");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem7.setText("List");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        Admin.add(jMenu5);

        jMenuItem8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem8.setText("Unit");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        Admin.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem9.setText("Taxes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        Admin.add(jMenuItem9);

        jMenuBar1.add(Admin);

        Transaction.setText("Transactions");
        Transaction.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenu8.setText("Purchase");
        jMenu8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem14.setText("Add");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem14);

        jMenuItem15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem15.setText("List");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        Transaction.add(jMenu8);

        jMenu11.setText("Purchase Return");
        jMenu11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem21.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem21.setText("Add");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem21);

        Transaction.add(jMenu11);

        jMenu9.setText("Sale");
        jMenu9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        Sale.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Sale.setText("Sale Bill");
        Sale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaleActionPerformed(evt);
            }
        });
        jMenu9.add(Sale);

        jMenuItem17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem17.setText("Report - All");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem17);

        jMenuItem20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem20.setText("Report - Pending Payments");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem20);

        Transaction.add(jMenu9);

        jMenu10.setText("Sale Return");
        jMenu10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem18.setText("Add");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem18);

        Transaction.add(jMenu10);

        jMenu12.setText("Payment");
        jMenu12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem19.setText("Add");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem19);

        jMenuItem22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem22.setText("List");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem22);

        Transaction.add(jMenu12);

        jMenu13.setText("Receipt");
        jMenu13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem23.setText("Add");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem23);

        jMenuItem24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem24.setText("List");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem24);

        Transaction.add(jMenu13);

        jMenuBar1.add(Transaction);

        Reports.setText("Reports");
        Reports.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem11.setText("General Stock");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        Reports.add(jMenuItem11);

        jMenuItem27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem27.setText("Barcoded Stock");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        Reports.add(jMenuItem27);

        jMenuBar1.add(Reports);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String txt = btnSave.getText();
        if (txt.equals("Update")) {

            String partyname = txtPartyname.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();
            String state = cmbState.getSelectedItem().toString();
            String gstin = txtGSTIN.getText();
            String emailid = txtEmailID.getText();

            String date = lblDate.getText();

            if (partyname.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Enter Party's Name");
            } else if (contact.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Enter contact");
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Enter address");
            } else {

                try {

                    if (state.equals("Andaman and Nicobar Islands")) {
                        code = "35";
                    }
                    if (state.equals("Andhra Pradesh")) {
                        code = "37";
                    }
                    if (state.equals("Arunachal Pradesh")) {
                        code = "12";
                    }
                    if (state.equals("Assam")) {
                        code = "18";
                    }
                    if (state.equals("Bihar")) {
                        code = "10";
                    }
                    if (state.equals("Chandigarh")) {
                        code = "04";
                    }
                    if (state.equals("Chattisgarh")) {
                        code = "22";
                    }
                    if (state.equals("Dadra and Nagar Haveli")) {
                        code = "26";
                    }
                    if (state.equals("Daman and Diu")) {
                        code = "25";
                    }
                    if (state.equals("Delhi")) {
                        code = "07";
                    }
                    if (state.equals("Goa")) {
                        code = "30";
                    }
                    if (state.equals("Gujarat")) {
                        code = "24";
                    }
                    if (state.equals("Haryana")) {
                        code = "06";
                    }
                    if (state.equals("Himachal Pradesh")) {
                        code = "02";
                    }
                    if (state.equals("Jammu and Kashmir")) {
                        code = "01";
                    }
                    if (state.equals("Jharkhand")) {
                        code = "20";
                    }
                    if (state.equals("Karnataka")) {
                        code = "29";
                    }
                    if (state.equals("Kerala")) {
                        code = "32";
                    }
                    if (state.equals("Lakshadweep Islands")) {
                        code = "31";
                    }
                    if (state.equals("Madhya Pradesh")) {
                        code = "23";
                    }
                    if (state.equals("Maharashtra")) {
                        code = "27";
                    }
                    if (state.equals("Manipur")) {
                        code = "14";
                    }
                    if (state.equals("Meghalaya")) {
                        code = "17";
                    }
                    if (state.equals("Mizoram")) {
                        code = "15";
                    }
                    if (state.equals("Nagaland")) {
                        code = "13";
                    }
                    if (state.equals("Odisha")) {
                        code = "21";
                    }
                    if (state.equals("Pondicherry")) {
                        code = "34";
                    }
                    if (state.equals("Punjab")) {
                        code = "03";
                    }
                    if (state.equals("Rajasthan")) {
                        code = "08";
                    }
                    if (state.equals("Sikkim")) {
                        code = "11";
                    }
                    if (state.equals("Tamil Nadu")) {
                        code = "33";
                    }
                    if (state.equals("Telangana")) {
                        code = "36";
                    }
                    if (state.equals("Tripura")) {
                        code = "16";
                    }
                    if (state.equals("Uttar Pradesh")) {
                        code = "09";
                    }
                    if (state.equals("Uttarakhand")) {
                        code = "05";
                    }
                    if (state.equals("West Bengal")) {
                        code = "19";
                    }

                    ResultSet rs = st.executeQuery("SELECT * FROM tabparty WHERE Sr='" + sr + "' ");
                    if ((rs.next())) {

                        st.executeUpdate(" update tabparty set Partyname='" + partyname + "',Contact='" + contact + "',Address='" + address + "'"
                                + ",State='" + state + "',Code='" + code + "',GSTIN='" + gstin + "'"
                                + ",Emailid='" + emailid + "' WHERE Sr='" + sr + "' ");

                    }

                    JOptionPane.showMessageDialog(rootPane, "Data saved sucessfully");
                    Party in = new Party(role, username);
                    in.setVisible(true);
                    this.dispose();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, e);

                }
            }
        } else {
            saveSupplier_Data();
        }


    }//GEN-LAST:event_btnSaveActionPerformed
    int unitid = 0;
    private void cmbStateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStateMouseClicked
    String code = "";
    private void cmbStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStateActionPerformed


    }//GEN-LAST:event_cmbStateActionPerformed

    private void txtContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyPressed

        int s = evt.getKeyCode();
        if ((s >= evt.VK_0 && s <= evt.VK_9) || (s >= evt.VK_NUMPAD0 && s <= evt.VK_NUMPAD9) || s == evt.VK_BACK_SPACE || s == evt.VK_TAB
                || s == evt.VK_LEFT || s == evt.VK_RIGHT) {

            txtContact.setEditable(true);

        } else {
            txtContact.setEditable(false);

        }
    }//GEN-LAST:event_txtContactKeyPressed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Login_Form lf = new Login_Form();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        Registration regi = new Registration(role, username);
        regi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        Party p = new Party(role, username);
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        Party_List p = new Party_List(role, username);
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Items im = new Items(role, username);
        im.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Items_List im = new Items_List(role, username);
        im.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Unit u = new Unit(role, username);
        u.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        Tax t = new Tax(role, username);
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        Purchase p = new Purchase(role, username);
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        Purchase_List list = new Purchase_List(role, username);
        list.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        Purchase_Return pr = new Purchase_Return(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void SaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaleActionPerformed
        Sale sale = new Sale(role, username);
        sale.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SaleActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        Sale_List sl = new Sale_List(role, username);
        sl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        Sale_Bill_Pending pr = new Sale_Bill_Pending(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        Sale_Return pr = new Sale_Return(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        Payment pr = new Payment(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        Payment_List pr = new Payment_List(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        Reciept pr = new Reciept(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        Receipt_List pr = new Receipt_List(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        Stock_Itemwise stock = new Stock_Itemwise(role, username);
        stock.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        Stock_Barcodewise barcode = new Stock_Barcodewise(role, username);
        barcode.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    public void saveSupplier_Data() {

        String partyname = txtPartyname.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String state = cmbState.getSelectedItem().toString();
        String gstin = txtGSTIN.getText();
        String emailid = txtEmailID.getText();

        String date = lblDate.getText();

        if (partyname.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter Party's Name");
        } else if (contact.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter contact");
        } else if (address.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter address");
        } else {

            if (state.equals("Andaman and Nicobar Islands")) {
                code = "35";
            }
            if (state.equals("Andhra Pradesh")) {
                code = "37";
            }
            if (state.equals("Arunachal Pradesh")) {
                code = "12";
            }
            if (state.equals("Assam")) {
                code = "18";
            }
            if (state.equals("Bihar")) {
                code = "10";
            }
            if (state.equals("Chandigarh")) {
                code = "04";
            }
            if (state.equals("Chattisgarh")) {
                code = "22";
            }
            if (state.equals("Dadra and Nagar Haveli")) {
                code = "26";
            }
            if (state.equals("Daman and Diu")) {
                code = "25";
            }
            if (state.equals("Delhi")) {
                code = "07";
            }
            if (state.equals("Goa")) {
                code = "30";
            }
            if (state.equals("Gujarat")) {
                code = "24";
            }
            if (state.equals("Haryana")) {
                code = "06";
            }
            if (state.equals("Himachal Pradesh")) {
                code = "02";
            }
            if (state.equals("Jammu and Kashmir")) {
                code = "01";
            }
            if (state.equals("Jharkhand")) {
                code = "20";
            }
            if (state.equals("Karnataka")) {
                code = "29";
            }
            if (state.equals("Kerala")) {
                code = "32";
            }
            if (state.equals("Lakshadweep Islands")) {
                code = "31";
            }
            if (state.equals("Madhya Pradesh")) {
                code = "23";
            }
            if (state.equals("Maharashtra")) {
                code = "27";
            }
            if (state.equals("Manipur")) {
                code = "14";
            }
            if (state.equals("Meghalaya")) {
                code = "17";
            }
            if (state.equals("Mizoram")) {
                code = "15";
            }
            if (state.equals("Nagaland")) {
                code = "13";
            }
            if (state.equals("Odisha")) {
                code = "21";
            }
            if (state.equals("Pondicherry")) {
                code = "34";
            }
            if (state.equals("Punjab")) {
                code = "03";
            }
            if (state.equals("Rajasthan")) {
                code = "08";
            }
            if (state.equals("Sikkim")) {
                code = "11";
            }
            if (state.equals("Tamil Nadu")) {
                code = "33";
            }
            if (state.equals("Telangana")) {
                code = "36";
            }
            if (state.equals("Tripura")) {
                code = "16";
            }
            if (state.equals("Uttar Pradesh")) {
                code = "09";
            }
            if (state.equals("Uttarakhand")) {
                code = "05";
            }
            if (state.equals("West Bengal")) {
                code = "19";
            }

            try {

                ResultSet rs = st.executeQuery("SELECT * FROM tabparty WHERE Partyname='" + partyname + "' ");
                if ((rs.next())) {
                    JOptionPane.showMessageDialog(rootPane, "Name already exist.");
                } else {
                    st.executeUpdate(" insert into tabparty(Partyname,Contact,Address,State,Code,GSTIN,Emailid) "
                            + "values('" + partyname + "','" + contact + "','" + address + "','" + state + "','" + code + "'"
                            + ",'" + gstin + "','" + emailid + "')  ");
                }

                JOptionPane.showMessageDialog(rootPane, "Data saved sucessfully");
                Party in = new Party(role, username);
                in.setVisible(true);
                this.dispose();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }
        }
    }

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
            java.util.logging.Logger.getLogger(Party.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Party.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Party.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Party.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Party().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Admin;
    private javax.swing.JMenu Company;
    private javax.swing.JMenu Reports;
    private javax.swing.JMenuItem Sale;
    private javax.swing.JMenu Transaction;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblPartyname;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMain1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JLabel txtBillNo;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmailID;
    private javax.swing.JTextField txtGSTIN;
    private javax.swing.JTextField txtPartyname;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}
