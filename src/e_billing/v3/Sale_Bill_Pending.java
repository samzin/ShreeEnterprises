package e_billing.v3;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Sale_Bill_Pending extends javax.swing.JFrame {

    private static final String[] specialNames = {
        "",
        " Thousand",
        " Million",
        " Billion",
        " Trillion",
        " Quadrillion",
        " Quintillion"
    };

    private static final String[] tensNames = {
        "",
        " Ten",
        " Twenty",
        " Thirty",
        " Forty",
        " Fifty",
        " Sixty",
        " Seventy",
        " Eighty",
        " Ninety"
    };

    private static final String[] numNames = {
        "",
        " One",
        " Two",
        " Three",
        " Four",
        " Five",
        " Six",
        " Seven",
        " Eight",
        " Nine",
        " Ten",
        " Eleven",
        " Twelve",
        " Thirteen",
        " Fourteen",
        " Fifteen",
        " Sixteen",
        " Seventeen",
        " Eighteen",
        " Nineteen"
    };

    String role, username, sr;
    Statement st;
    Connection con;

    public Sale_Bill_Pending() {
        initComponents();
        getDatabase_Connections();
        showDate();
        getShopname();
        showReport();
        getPartyname();
    }

    public Sale_Bill_Pending(String Role, String Username) {
        initComponents();
        getDatabase_Connections();
        role = Role;
        username = Username;
        txtUser.setText(username);
        getShopname();
        showDate();
        showReport();
        getPartyname();
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

    public void getPartyname() {
        TextAutoCompleter item = new TextAutoCompleter(txtItemname);
        try {

            ResultSet rs2 = st.executeQuery("select distinct Partyname from tabparty ");

            while (rs2.next()) {
                item.addItem(rs2.getString("Partyname"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

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
        jScrollPane2 = new javax.swing.JScrollPane();
        tabGoodentry = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnAll = new javax.swing.JButton();
        txtItemname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSettings1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dcStart = new com.toedter.calendar.JDateChooser();
        dcEnd = new com.toedter.calendar.JDateChooser();
        btnSettings2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbPayment = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtChqno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBankname = new javax.swing.JTextField();
        txtTotalamount = new javax.swing.JTextField();
        chqamlt = new javax.swing.JLabel();
        lblNewbalance = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblOldbalance = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
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
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

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

        jPanel4.setBackground(new java.awt.Color(204, 255, 225));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tabGoodentry.setFont(new java.awt.Font("Mangal", 1, 13)); // NOI18N
        tabGoodentry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr", "Sr", "Bill No", "Date", "Party Name", "Payable Amount", "Paid", "OutStanding Amt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
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
            tabGoodentry.getColumnModel().getColumn(0).setMinWidth(40);
            tabGoodentry.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabGoodentry.getColumnModel().getColumn(0).setMaxWidth(40);
            tabGoodentry.getColumnModel().getColumn(1).setMinWidth(0);
            tabGoodentry.getColumnModel().getColumn(1).setPreferredWidth(1);
            tabGoodentry.getColumnModel().getColumn(1).setMaxWidth(1);
            tabGoodentry.getColumnModel().getColumn(2).setMinWidth(80);
            tabGoodentry.getColumnModel().getColumn(2).setPreferredWidth(110);
            tabGoodentry.getColumnModel().getColumn(2).setMaxWidth(80);
            tabGoodentry.getColumnModel().getColumn(3).setMinWidth(100);
            tabGoodentry.getColumnModel().getColumn(3).setPreferredWidth(70);
            tabGoodentry.getColumnModel().getColumn(3).setMaxWidth(100);
            tabGoodentry.getColumnModel().getColumn(5).setMinWidth(90);
            tabGoodentry.getColumnModel().getColumn(5).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(5).setMaxWidth(90);
            tabGoodentry.getColumnModel().getColumn(6).setMinWidth(90);
            tabGoodentry.getColumnModel().getColumn(6).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(6).setMaxWidth(90);
            tabGoodentry.getColumnModel().getColumn(7).setMinWidth(110);
            tabGoodentry.getColumnModel().getColumn(7).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(7).setMaxWidth(110);
        }

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
        jLabel4.setText("Search By Party's Name | Contact");

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

        dcStart.setDateFormatString("dd-MM-yyyy");
        dcStart.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        dcEnd.setDateFormatString("dd-MM-yyyy");
        dcEnd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnSettings2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSettings2.setText("Update");
        btnSettings2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettings2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtItemname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(dcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSettings1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSettings2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
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
                                    .addComponent(dcEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                    .addComponent(btnSettings1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSettings2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtItemname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel5.setText("jLabel5");

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        cmbPayment.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmbPayment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Payment Type.", "Cash", "Cheque", "NFTS", "RTGS", "DD", "Credit" }));
        cmbPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPaymentMouseClicked(evt);
            }
        });
        cmbPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaymentActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cheque No.");

        txtChqno.setBackground(new java.awt.Color(240, 240, 240));
        txtChqno.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtChqno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChqno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChqnoKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Bank Name");

        txtBankname.setBackground(new java.awt.Color(240, 240, 240));
        txtBankname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtBankname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBankname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBanknameKeyReleased(evt);
            }
        });

        txtTotalamount.setBackground(new java.awt.Color(240, 240, 240));
        txtTotalamount.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        txtTotalamount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalamountKeyReleased(evt);
            }
        });

        chqamlt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        chqamlt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chqamlt.setText("Chq. Amt");

        lblNewbalance.setBackground(new java.awt.Color(204, 255, 204));
        lblNewbalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewbalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblOldbalance.setBackground(new java.awt.Color(204, 255, 204));
        lblOldbalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOldbalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOldbalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chqamlt, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbPayment, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalamount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNewbalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136))
                            .addComponent(txtChqno, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBankname, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblOldbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtChqno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBankname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTotalamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chqamlt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jMenu2.setText("Company");
        jMenu2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem10.setText("Logout");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Adminstration");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

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

        jMenu1.add(jMenu7);

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

        jMenu1.add(jMenu5);

        jMenuItem8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem8.setText("Unit");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem9.setText("Taxes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Transactions");
        jMenu3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

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

        jMenuItem25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem25.setText("Report - Pending Payments");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem25);

        jMenu3.add(jMenu8);

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

        jMenu3.add(jMenu11);

        jMenu9.setText("Sale");
        jMenu9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jMenuItem16.setText("Add");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem16);

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

        jMenu3.add(jMenu9);

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

        jMenu3.add(jMenu10);

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

        jMenu3.add(jMenu12);

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

        jMenu3.add(jMenu13);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reports");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenuItem11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMenuItem11.setText("Stock");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

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
    String updateid = "", pname = "",custid1="";
    private void tabGoodentryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabGoodentryMouseClicked
        updateid = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 1).toString();
  pname = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 4).toString();
        String remain = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 7).toString();
        lblOldbalance.setText(remain);
        getCustid();
    }//GEN-LAST:event_tabGoodentryMouseClicked

     public void getCustid() {
        try {
            ResultSet rs = st.executeQuery("Select * from tabparty where Partyname='" + pname + "' ");
            if (rs.next()) {
                custid1 = rs.getString("Sr");
            }
        } catch (Exception e) {

        }
    }
     
    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        showReport();
    }//GEN-LAST:event_btnAllActionPerformed

    private void txtItemnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemnameKeyReleased
        showReport_Partywise();
    }//GEN-LAST:event_txtItemnameKeyReleased

    private void cmbPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPaymentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPaymentMouseClicked

    private void cmbPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaymentActionPerformed
        int id = cmbPayment.getSelectedIndex();
        if (id == 2) {
            jLabel9.setText("Cheque No.");
        } else {
            jLabel9.setText("Note");
        }

        String txt = cmbPayment.getSelectedItem().toString();
        if (txt.equals("Cash")) {
            chqamlt.setText("Cash Amt");
        } else if (txt.equals("Cheque")) {
            chqamlt.setText("Chq Amt");
        } else if (txt.equals("NFTS")) {
            chqamlt.setText("NFTS Amt");
        } else if (txt.equals("RTGS")) {
            chqamlt.setText("RTGS Amt");
        } else if (txt.equals("Paytm")) {
            chqamlt.setText("Paytm Amt");
        }
    }//GEN-LAST:event_cmbPaymentActionPerformed
    String Sdate = "", Edate = "";
    private void btnSettings1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettings1ActionPerformed

        Sdate = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        Edate = ((JTextField) dcEnd.getDateEditor().getUiComponent()).getText();
        String partyname = txtItemname.getText();
        int ptype = cmbPayment.getSelectedIndex();
        if ((ptype == 0) && (partyname.equals(""))) {
            showReport_Datewsie();
        } else if (!(partyname.equals(""))) {
            showReport_Date_Partywsie();
        } else {
            showReport();
        }

    }//GEN-LAST:event_btnSettings1ActionPerformed

    private void txtChqnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChqnoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChqnoKeyReleased

    private void txtBanknameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBanknameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBanknameKeyReleased

    private void txtTotalamountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalamountKeyReleased
        if (txtTotalamount.getText().equals("")) {
            txtTotalamount.setText("");
            lblNewbalance.setText(txtTotalamount.getText());
        } else {
            double finalamount = Double.parseDouble(lblOldbalance.getText());
            double jama = Double.parseDouble(txtTotalamount.getText());
            if (!(finalamount < jama)) {
                double temp2 = finalamount - jama;
                BigDecimal temp = new BigDecimal(temp2);
                temp = temp.setScale(2, RoundingMode.UP);
                lblNewbalance.setText(temp.toString());
            } else {
                txtTotalamount.setText("");
                lblNewbalance.setText("");
            }
        }
    }//GEN-LAST:event_txtTotalamountKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String billno = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 2).toString();
        String tabtotal = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 7).toString();
        String remain = lblNewbalance.getText();

        String payment = cmbPayment.getSelectedItem().toString();
        String chqno = txtChqno.getText();
        String bankname = txtBankname.getText();
        String paid1 = txtTotalamount.getText();
        String date = lblDate.getText();
        if (bankname.equals("")) {
            bankname = ".";
        }
        if (txtTotalamount.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter Amount.");
        } else if (payment.equals("Payment By")) {
            JOptionPane.showMessageDialog(rootPane, "Select Payment Type.");
        } else {
            Double paid = Double.parseDouble(tabtotal) - Double.parseDouble(remain);
            try {
                convertWord();
                String custid = "";
                ResultSet rs = st.executeQuery("SELECT * FROM taborder_total WHERE BillNo='" + billno + "'  ");

                if ((rs.next())) {
                    custid = rs.getString("Partyid");
//                    st.executeUpdate(" update  taborder_total set Advance='" + paid1 + "',Balance='" + remain + "' WHERE BillNo='" + billno + "'  ");

                }
                st.executeUpdate(" insert into taborder_payment_details(Date1,BillNo,Custid,Totalamount,Advance,Balance,Payment,Chqno,Bankname,Adv_txt,Status) values "
                        + "('" + date + "','--','" + custid1 + "','--','" + paid1 + "','" + remain + "'"
                        + ",'" + payment + "','" + chqno + "','" + bankname + "','" + totalamount_word_txt + "','0')");

                if (searchid == 2) {
                    showReport_Datewsie();
                } else if (searchid == 3) {
                    showReport_Partywise();
                } else if (searchid == 2) {
                    showReport_Date_Partywsie();
                } else {
                    showReport();
                }
                lblOldbalance.setText("");
                txtTotalamount.setText("");
                lblNewbalance.setText("");
                txtChqno.setText("");
                txtBankname.setText("");
                cmbPayment.setSelectedIndex(0);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Login_Form lf = new Login_Form();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

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

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        Sale s = new Sale(role, username);
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

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

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        Purchase_Bill_Pending stock = new Purchase_Bill_Pending(role, username);
        stock.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void btnSettings2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettings2ActionPerformed
        if (tabGoodentry.getCellEditor() != null) {
            tabGoodentry.getCellEditor().stopCellEditing();
        }

        for (int i = 0; i < tabGoodentry.getRowCount(); i++) {
            String sr = tabGoodentry.getValueAt(i, 1).toString();
            String barcode = tabGoodentry.getValueAt(i, 7).toString();
             String paid = tabGoodentry.getValueAt(i, 6).toString();
            if (!(barcode.matches(".*[a-z].*"))) {
                try {
                    ResultSet rs = st.executeQuery("Select * from taborder_payment_details where Sr='" + sr + "' ");
                    if (rs.next()) {
                        st.executeUpdate("update taborder_payment_details set Balance='" + barcode + "',Advance='" + paid + "' where  Sr='" + sr + "' ");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Balance व्यवस्थित टाका.");
            }

        }

        Sale_Bill_Pending pr = new Sale_Bill_Pending(role, username);
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSettings2ActionPerformed

    private String convertLessThanOneThousand(int number) {
        String current;

        if (number % 100 < 20) {
            current = numNames[number % 100];
            number /= 100;
        } else {
            current = numNames[number % 10];
            number /= 10;

            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) {
            return current;
        }
        return numNames[number] + " Hundred" + current;
    }

    public String convert(int number) {

        if (number == 0) {
            return "zero";
        }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "negative";
        }

        String current = "";
        int place = 0;

        do {
            int n = number % 1000;
            if (n != 0) {
                String s = convertLessThanOneThousand(n);
                current = s + specialNames[place] + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);

        return (prefix + current).trim();
    }

    String totalamount_word_txt = "";

    public void convertWord() {
        String amt = txtTotalamount.getText();
        System.out.println("amt : " + amt);
        int one = Integer.parseInt(amt);
        totalamount_word_txt = convert(one) + " Only /-";

        System.out.println("totalamount_word_txt : " + totalamount_word_txt);
    }
    int searchid = 0;

    public void showReport() {
        searchid = 1;
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            String allcustid = "";

            ResultSet rs1 = st.executeQuery("Select Sr from tabparty  ");
            while (rs1.next()) {
                allcustid = allcustid.concat(rs1.getString("Sr")).concat(";");
            }
            String custid[] = allcustid.split(";");
            for (int i = 0; i < custid.length; i++) {
                if (!(custid[i].equals(""))) {

                    int tempid = 0;
                    ResultSet rs2 = st.executeQuery("select Sr from taborder_payment_details where Custid='" + custid[i] + "' ");

                    while (rs2.next()) {
                        tempid = rs2.getInt("Sr");
                    }

                    ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Sr,s.Date1,s.BillNo,s.TotalPayable,s.Advance,s.Balance,"
                            + "s.Payment,s.Chqno,s.Bankname from taborder_payment_details s ,tabparty p "
                            + " where p.Sr=s.Custid && !(s.Balance='0.0' || s.Balance='0.00' || s.Balance='0' ) && "
                            + " s.Sr='" + tempid + "' order by s.Sr asc ");

                    while (rs.next()) {
                        count = rs.getRow();

                        String partyname = rs.getString("Partyname");
                        String sr = rs.getString("Sr");
                        String date = rs.getString("Date1");
                        String billno = rs.getString("BillNo");
                        String payable = rs.getString("TotalPayable");
                        String advance = rs.getString("Advance");
                        String balance = rs.getString("Balance");

                        df.addRow(new Object[]{count, sr, billno, date, partyname, payable, advance, balance});

                        tabGoodentry.setModel(df);

                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Datewsie() {
        searchid = 2;
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.Discountamt,s.Discountprct,s.Payableamt,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' "
                    + " && !(s.Balance='0.0' || s.Balance='0.00' || s.Balance='0' ) "
                    + " && !(s.Balance='0.0' || s.Balance='0.00' || s.Balance='0' ) order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String partyname = rs.getString("Partyname");
                String sr = rs.getString("Sr");
                String date = rs.getString("Date1");
                String billno = rs.getString("BillNo");
                String taxable = rs.getString("TaxableamtT");
                String cgst = rs.getString("TCGST");
                String sgst = rs.getString("TSGST");
                String igst = rs.getString("TIGST");
                String totalamt = rs.getString("Totalamt");
                String discount = rs.getString("Discountamt");
                String payable = rs.getString("Payableamt");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");

                df.addRow(new Object[]{count, sr, billno, date, partyname, payable, advance, balance});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Partywise() {
        searchid = 3;
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            String allcustid = "";

            ResultSet rs1 = st.executeQuery("Select Sr from tabparty where  ( p.Partyname LIKE '%" + partyname1 + "%' || p.Contact LIKE '%" + partyname1 + "%') ");
            while (rs1.next()) {
                allcustid = allcustid.concat(rs1.getString("Sr")).concat(";");
            }
            String custid[] = allcustid.split(";");
            for (int i = 0; i < custid.length; i++) {
                if (!(custid[i].equals(""))) {

                    int tempid = 0;
                    ResultSet rs2 = st.executeQuery("select Sr from taborder_payment_details where Custid='" + custid[i] + "' ");

                    while (rs2.next()) {
                        tempid = rs2.getInt("Sr");
                    }

                    ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Sr,s.Date1,s.BillNo,s.TotalPayable,s.Advance,s.Balance,"
                            + "s.Payment,s.Custid,s.Chqno,s.Bankname from taborder_payment_details s ,tabparty p "
                            + " where p.Sr=s.Custid && !(s.Balance='0.0' || s.Balance='0.00' || s.Balance='0' ) && "
                            + " s.Sr='" + tempid + "' order by s.Sr asc ");

                    while (rs.next()) {
                        count = rs.getRow();

                        String partyname = rs.getString("Partyname");
                        String sr = rs.getString("Sr");
                        String date = rs.getString("Date1");
                        String billno = rs.getString("BillNo");
                        custid1=rs.getString("Custid");
                        String payable = rs.getString("TotalPayable");
                        String advance = rs.getString("Advance");
                        String balance = rs.getString("Balance");

                        df.addRow(new Object[]{count, sr, billno, date, partyname, payable, advance, balance});

                        tabGoodentry.setModel(df);

                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Paymentwise() {

        String ptype = cmbPayment.getSelectedItem().toString();
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.Discountamt,s.Discountprct,s.Payableamt,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && s.PT LIKE '%" + ptype + "'    order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String partyname = rs.getString("Partyname");
                String sr = rs.getString("Sr");
                String date = rs.getString("Date1");
                String billno = rs.getString("BillNo");
                String taxable = rs.getString("TaxableamtT");
                String cgst = rs.getString("TCGST");
                String sgst = rs.getString("TSGST");
                String igst = rs.getString("TIGST");
                String totalamt = rs.getString("Totalamt");
                String discount = rs.getString("Discountamt");
                String payable = rs.getString("Payableamt");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Date_Partywsie() {
        searchid = 4;
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.Discountamt,s.Discountprct,s.Payableamt,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' "
                    + " &&  (p.Partyname LIKE '%" + pname + "%' || p.Contact LIKE '%" + pname + "%') && !(s.Balance='0.0' || s.Balance='0.00' || s.Balance='0' ) order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String partyname = rs.getString("Partyname");
                String sr = rs.getString("Sr");
                String date = rs.getString("Date1");
                String billno = rs.getString("BillNo");
                String taxable = rs.getString("TaxableamtT");
                String cgst = rs.getString("TCGST");
                String sgst = rs.getString("TSGST");
                String igst = rs.getString("TIGST");
                String totalamt = rs.getString("Totalamt");
                String discount = rs.getString("Discountamt");
                String payable = rs.getString("Payableamt");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");

                df.addRow(new Object[]{count, sr, billno, date, partyname, payable, advance, balance});

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
        String pname = cmbPayment.getSelectedItem().toString();
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.Discountamt,s.Discountprct,s.Payableamt,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' "
                    + " &&  s.PT LIKE '%" + pname + "%' order by s.Sr asc ");

            while (rs.next()) {
                count = rs.getRow();

                String partyname = rs.getString("Partyname");
                String sr = rs.getString("Sr");
                String date = rs.getString("Date1");
                String billno = rs.getString("BillNo");
                String taxable = rs.getString("TaxableamtT");
                String cgst = rs.getString("TCGST");
                String sgst = rs.getString("TSGST");
                String igst = rs.getString("TIGST");
                String totalamt = rs.getString("Totalamt");
                String discount = rs.getString("Discountamt");
                String payable = rs.getString("Payableamt");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

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
            java.util.logging.Logger.getLogger(Sale_Bill_Pending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale_Bill_Pending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale_Bill_Pending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale_Bill_Pending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Sale_Bill_Pending().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSettings1;
    private javax.swing.JButton btnSettings2;
    private javax.swing.JLabel chqamlt;
    private javax.swing.JComboBox cmbPayment;
    private com.toedter.calendar.JDateChooser dcEnd;
    private com.toedter.calendar.JDateChooser dcStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
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
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNewbalance;
    private javax.swing.JLabel lblOldbalance;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMain1;
    private javax.swing.JTable tabGoodentry;
    private javax.swing.JTextField txtBankname;
    private javax.swing.JLabel txtBillNo;
    private javax.swing.JTextField txtChqno;
    private javax.swing.JTextField txtItemname;
    private javax.swing.JTextField txtTotalamount;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}
