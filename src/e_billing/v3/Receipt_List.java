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
import java.math.MathContext;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Receipt_List extends javax.swing.JFrame {

    String role, username, sr;
    Statement st;
    Connection con;

    public Receipt_List() {
        initComponents();
        getDatabase_Connections();
        showDate();
        getShopname();
        showReport();

    }

    public Receipt_List(String Role, String Username) {
        initComponents();
        getDatabase_Connections();
        role = Role;
        username = Username;
        txtUser.setText(username);
        getShopname();
        showDate();
        showReport();

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

    public void showDate() {
        Date date = new Date();

        SimpleDateFormat dateformate = new SimpleDateFormat("yyyy-MM-dd");
        String todaydate = dateformate.format(date);
        lblDate.setText(todaydate);

        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
//                String time = timeFormat.format(date);
//                lblTime.setText(time);
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
        jPanel5 = new javax.swing.JPanel();
        btnSave2 = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        txtItemname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSave3 = new javax.swing.JButton();
        btnAll1 = new javax.swing.JButton();
        cmbPaymenttype = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        btnSettings1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dcStart = new com.toedter.calendar.JDateChooser();
        dcEnd = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabGoodentry = new javax.swing.JTable();
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
        jLabel2.setText("Developed By - MatrixHub Technology,Pune");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contact - 9595664413 | 8055664413 | 7709013826");

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnSave2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave2.setText("Delete");
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        btnAll.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnAll.setText("All Reports");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        txtItemname.setBackground(new java.awt.Color(240, 240, 240));
        txtItemname.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtItemname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtItemnameKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel4.setText("Search By Party's Name.");

        btnSave3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave3.setText("Edit");
        btnSave3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave3ActionPerformed(evt);
            }
        });

        btnAll1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnAll1.setText("Todays");
        btnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAll1ActionPerformed(evt);
            }
        });

        cmbPaymenttype.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmbPaymenttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type.", "Cash", "Cheque", "NFTS", "RTGS", "DD", "Not Now" }));
        cmbPaymenttype.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPaymenttypeMouseClicked(evt);
            }
        });
        cmbPaymenttype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaymenttypeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel6.setText("Search By Payments Type.");

        btnSettings1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSettings1.setText("Search");
        btnSettings1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettings1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Search By Date");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("To");

        dcStart.setDateFormatString("yyyy-MM-dd");
        dcStart.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        dcEnd.setDateFormatString("yyyy-MM-dd");
        dcEnd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtItemname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(cmbPaymenttype, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(dcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSettings1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(dcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSettings1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtItemname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPaymenttype, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jLabel5.setText("jLabel5");

        tabGoodentry.setFont(new java.awt.Font("Mangal", 1, 13)); // NOI18N
        tabGoodentry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr", "Sr", "Date", "Name [Payment to]", "Amount", "Payment Type", "Narration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabGoodentry.setSelectionBackground(new java.awt.Color(95, 158, 160));
        tabGoodentry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabGoodentryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabGoodentry);
        if (tabGoodentry.getColumnModel().getColumnCount() > 0) {
            tabGoodentry.getColumnModel().getColumn(0).setMinWidth(0);
            tabGoodentry.getColumnModel().getColumn(0).setPreferredWidth(1);
            tabGoodentry.getColumnModel().getColumn(0).setMaxWidth(1);
            tabGoodentry.getColumnModel().getColumn(1).setMinWidth(40);
            tabGoodentry.getColumnModel().getColumn(1).setPreferredWidth(40);
            tabGoodentry.getColumnModel().getColumn(1).setMaxWidth(40);
            tabGoodentry.getColumnModel().getColumn(2).setMinWidth(130);
            tabGoodentry.getColumnModel().getColumn(2).setPreferredWidth(130);
            tabGoodentry.getColumnModel().getColumn(2).setMaxWidth(130);
            tabGoodentry.getColumnModel().getColumn(3).setMinWidth(300);
            tabGoodentry.getColumnModel().getColumn(3).setPreferredWidth(300);
            tabGoodentry.getColumnModel().getColumn(3).setMaxWidth(300);
            tabGoodentry.getColumnModel().getColumn(4).setMinWidth(150);
            tabGoodentry.getColumnModel().getColumn(4).setPreferredWidth(150);
            tabGoodentry.getColumnModel().getColumn(4).setMaxWidth(150);
            tabGoodentry.getColumnModel().getColumn(5).setMinWidth(150);
            tabGoodentry.getColumnModel().getColumn(5).setPreferredWidth(150);
            tabGoodentry.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(15, Short.MAX_VALUE))
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
    String updateid = "";
    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        String id = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 1).toString();
        if (role.equals("Admin")) {

        } else {
            JOptionPane.showMessageDialog(rootPane, "You are not Go To Credit Note.");
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        showReport();
    }//GEN-LAST:event_btnAllActionPerformed

    private void txtItemnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemnameKeyReleased
        showReport_Partywise();
    }//GEN-LAST:event_txtItemnameKeyReleased

    private void btnSave3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave3ActionPerformed
        String id = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 2).toString();
        System.out.println("id : " + id);
        Purchase item = new Purchase(role, username, id);
        item.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSave3ActionPerformed

    private void btnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAll1ActionPerformed

        Sdate = lblDate.getText();
        Edate = lblDate.getText();
        showReport_Datewsie();
    }//GEN-LAST:event_btnAll1ActionPerformed

    private void cmbPaymenttypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPaymenttypeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPaymenttypeMouseClicked

    private void cmbPaymenttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaymenttypeActionPerformed
        int pid = cmbPaymenttype.getSelectedIndex();
        if (!(pid == 0)) {
            showReport_Paymentwise();
        } else {
            showReport();
        }
    }//GEN-LAST:event_cmbPaymenttypeActionPerformed
    String Sdate = "", Edate = "";
    private void btnSettings1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettings1ActionPerformed

        Sdate = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        Edate = ((JTextField) dcEnd.getDateEditor().getUiComponent()).getText();
        String partyname = txtItemname.getText();
        int ptype = cmbPaymenttype.getSelectedIndex();
        if ((ptype == 0) && (partyname.equals(""))) {
            showReport_Datewsie();
        } else if ((ptype == 0)) {
            showReport_Date_Partywsie();
        } else if ((partyname.equals(""))) {
            showReport_Date_Paymentwsie();
        } else {
            showReport();
        }

    }//GEN-LAST:event_btnSettings1ActionPerformed

    private void tabGoodentryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabGoodentryMouseClicked

        String desc = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 2).toString();
        String qty = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 3).toString();
        String unit = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 4).toString();
        String price = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 5).toString();
        String tax = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 6).toString();
        String amount = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 7).toString();

    }//GEN-LAST:event_tabGoodentryMouseClicked

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

    public void showReport() {

        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);

        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabGoodentry.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Date1,s.Amount,s.PT,s.Narration"
                    + ",p.Partyname from tabreciept s,tabparty p where s.Partyid=p.Sr  order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String billno = rs.getString("Sr");
                String itemname = rs.getString("Partyname");
                String date1 = rs.getString("Date1");
                String narration = rs.getString("Narration");
                String pt = rs.getString("PT");
                String amt = rs.getString("Amount");

                df.addRow(new Object[]{billno, count, date1, itemname, amt, pt, narration});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Datewsie() {

        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);

        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabGoodentry.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Date1,s.Amount,s.PT,s.Narration"
                    + ",p.Partyname from tabreciept s,tabparty p where s.Partyid=p.Sr "
                    + "&& s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String billno = rs.getString("Sr");
                String itemname = rs.getString("Partyname");
                String date1 = rs.getString("Date1");
                String narration = rs.getString("Narration");
                String pt = rs.getString("PT");
                String amt = rs.getString("Amount");

                df.addRow(new Object[]{billno, count, date1, itemname, amt, pt, narration});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Partywise() {

        String partyname1 = txtItemname.getText();
        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);

        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabGoodentry.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Date1,s.Amount,s.PT,s.Narration"
                    + ",p.Partyname from tabreciept s,tabparty p where s.Partyid=p.Sr "
                    + "&&  p.Partyname LIKE '%" + partyname1 + "%'  order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String billno = rs.getString("Sr");
                String itemname = rs.getString("Partyname");
                String date1 = rs.getString("Date1");
                String narration = rs.getString("Narration");
                String pt = rs.getString("PT");
                String amt = rs.getString("Amount");

                df.addRow(new Object[]{billno, count, date1, itemname, amt, pt, narration});

                tabGoodentry.setModel(df);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Paymentwise() {

        String ptype = cmbPaymenttype.getSelectedItem().toString();
        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);

        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabGoodentry.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
       

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Date1,s.Amount,s.PT,s.Narration"
                    + ",p.Partyname from tabreciept s,tabparty p where s.Partyid=p.Sr "
                    + "&&  s.PT LIKE '%" + ptype + "'   order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String billno = rs.getString("Sr");
                String itemname = rs.getString("Partyname");
                String date1 = rs.getString("Date1");
                String narration = rs.getString("Narration");
                String pt = rs.getString("PT");
                String amt = rs.getString("Amount");

                df.addRow(new Object[]{billno, count, date1, itemname, amt, pt, narration});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Date_Partywsie() {

        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);
        String pname = txtItemname.getText();
        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabGoodentry.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Date1,s.Amount,s.PT,s.Narration"
                    + ",p.Partyname from tabreciept s,tabparty p where s.Partyid=p.Sr "
                    + "&& s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' "
                    + " &&  p.Partyname LIKE '%" + pname + "%'   order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String billno = rs.getString("Sr");
                String itemname = rs.getString("Partyname");
                String date1 = rs.getString("Date1");
                String narration = rs.getString("Narration");
                String pt = rs.getString("PT");
                String amt = rs.getString("Amount");

                df.addRow(new Object[]{billno, count, date1, itemname, amt, pt, narration});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Date_Paymentwsie() {

        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);
        String pname = cmbPaymenttype.getSelectedItem().toString();
        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabGoodentry.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tabGoodentry.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
       

        int count = 0;
        try {

   
           ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Date1,s.Amount,s.PT,s.Narration"
                    + ",p.Partyname from tabreciept s,tabparty p where s.Partyid=p.Sr "
                    + "&& s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' "
                    + " &&  s.PT LIKE '%" + pname + "%'   order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String billno = rs.getString("Sr");
                String itemname = rs.getString("Partyname");
                String date1 = rs.getString("Date1");
                String narration = rs.getString("Narration");
                String pt = rs.getString("PT");
                String amt = rs.getString("Amount");

                df.addRow(new Object[]{billno, count, date1, itemname, amt, pt, narration});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
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
            java.util.logging.Logger.getLogger(Receipt_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Receipt_List().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Admin;
    private javax.swing.JMenu Company;
    private javax.swing.JMenu Reports;
    private javax.swing.JMenuItem Sale;
    private javax.swing.JMenu Transaction;
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnAll1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSave3;
    private javax.swing.JButton btnSettings1;
    private javax.swing.JComboBox cmbPaymenttype;
    private com.toedter.calendar.JDateChooser dcEnd;
    private com.toedter.calendar.JDateChooser dcStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMain1;
    private javax.swing.JTable tabGoodentry;
    private javax.swing.JLabel txtBillNo;
    private javax.swing.JTextField txtItemname;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}
