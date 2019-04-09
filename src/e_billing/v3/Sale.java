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
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Sale extends javax.swing.JFrame {

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

    public Sale() {
        initComponents();
        getDatabase_Connections();
        showDate();
        getShopname();
        getUnits();
        billNo();
        if (txtBillNo.getText().equals("")) {
            txtBillNo.setText("1");
        }

    }

    public Sale(String Role, String Username) {
        initComponents();
        getDatabase_Connections();
        role = Role;
        username = Username;
        txtUser.setText(username);
        getShopname();
        showDate();
        getUnits();
        billNo();
        if (txtBillNo.getText().equals("")) {
            txtBillNo.setText("1");
        }

        txtContact.setDocument(new Sale.JTextFieldLimit(10));
    }

    public Sale(String Role, String Username, String Sr) {
        initComponents();
        getDatabase_Connections();
        role = Role;
        username = Username;
        sr = Sr;
        txtUser.setText(username);
        txtBillNo.setText(sr);
        showDate();
        showReport();
        getShopname();
        getUnits();
        getPartydetails();
        getDate_BillNo();
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

    public void billNo() {
        try {

            ResultSet rs = st.executeQuery("select * from taborder");

            while (rs.next()) {

                String billno = rs.getString("BillNo");

                if (billno.equals(null)) {

                } else {
                    x = Integer.valueOf(billno);
                    x++;

                    txtBillNo.setText(Integer.toString(x));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    int x = 0;

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

    int pid = 0;

    public void getPartydetails() {

        try {

            ResultSet rs = st.executeQuery("select * from tabparty where Sr='" + pid + "' ");

            while (rs.next()) {
                txtPartyname.setText(rs.getString("Partyname"));
                txtContact.setText(rs.getString("Contact"));
                partyid = rs.getInt("Sr");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }

    public void getDate_BillNo() {

        try {

            ResultSet rs = st.executeQuery("select * from tabstock_datewise where BillNo='" + txtBillNo.getText() + "' ");

            while (rs.next()) {
                dcStart.setDate(rs.getDate("Date1"));
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
        dcStart.setDate(date);
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

    public void getUnits() {
        cmbUnits.removeAllItems();
        cmbUnits.addItem("Select Unit");

        cmnTax.removeAllItems();
        cmnTax.addItem("Select Tax Category");
        TextAutoCompleter item = new TextAutoCompleter(txtItemname);
        TextAutoCompleter party = new TextAutoCompleter(txtPartyname);
        try {

            ResultSet rs = st.executeQuery("select * from tabunits ");

            while (rs.next()) {
                cmbUnits.addItem(rs.getString("Unit"));

            }

            ResultSet rs1 = st.executeQuery("select * from tabtax ");

            while (rs1.next()) {
                cmnTax.addItem(rs1.getString("Taxname"));

            }

            ResultSet rs2 = st.executeQuery("select distinct Itemname from tabitems ");

            while (rs2.next()) {
                item.addItem(rs2.getString("Itemname"));

            }

            ResultSet rs3 = st.executeQuery("select distinct Partyname from tabparty ");

            while (rs3.next()) {
                party.addItem(rs3.getString("Partyname"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

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
        jPanel6 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtItemname = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        cmbUnits = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        cmnTax = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        chkIGST = new javax.swing.JCheckBox();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabGoodentry = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        txtTaxableamt = new javax.swing.JTextField();
        txtCGST = new javax.swing.JTextField();
        txtTotalamt = new javax.swing.JTextField();
        txtDiscountprct = new javax.swing.JTextField();
        txtDiscountamt = new javax.swing.JTextField();
        txtPaymentdetails = new javax.swing.JTextField();
        txtAdvance = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIGST = new javax.swing.JTextField();
        txtSGST = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        cmbPaymenttype = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtPayableamt = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        lblRoundup = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtPartyname = new javax.swing.JTextField();
        dcStart = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        cmbState = new javax.swing.JComboBox();
        txtGSTIN = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Company = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
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
        Reports = new javax.swing.JMenu();
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
        jLabel2.setText("OORT ");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contact - 7709013826");

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

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel36.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Item Name  :");

        txtItemname.setBackground(new java.awt.Color(240, 240, 240));
        txtItemname.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txtItemname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtItemnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtItemnameKeyReleased(evt);
            }
        });

        txtQty.setBackground(new java.awt.Color(240, 240, 240));
        txtQty.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });

        cmbUnits.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmbUnits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type.", "Gold", "Silver", "Dimond" }));
        cmbUnits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbUnitsMouseClicked(evt);
            }
        });
        cmbUnits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnitsActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Qty  :");

        jLabel46.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Unit  :");

        txtPrice.setBackground(new java.awt.Color(240, 240, 240));
        txtPrice.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceKeyReleased(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Price  :");

        txtAmount.setBackground(new java.awt.Color(240, 240, 240));
        txtAmount.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Tax Category  :");

        cmnTax.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmnTax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type.", "Gold", "Silver", "Dimond" }));
        cmnTax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmnTaxMouseClicked(evt);
            }
        });
        cmnTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmnTaxActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Amount  :");

        chkIGST.setBackground(new java.awt.Color(204, 255, 204));
        chkIGST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        chkIGST.setText("IGST Applicable");
        chkIGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIGSTActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtItemname, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmnTax, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkIGST)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jLabel37)
                        .addComponent(jLabel46)
                        .addComponent(jLabel47)
                        .addComponent(jLabel48)
                        .addComponent(jLabel49)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAmount)
                    .addComponent(txtPrice)
                    .addComponent(txtQty)
                    .addComponent(txtItemname)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmnTax, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkIGST, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tabGoodentry.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        tabGoodentry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr", "Sr", "Items Name", "Qty.", "Unit", "Price", "Tax Category", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            tabGoodentry.getColumnModel().getColumn(1).setMinWidth(30);
            tabGoodentry.getColumnModel().getColumn(1).setPreferredWidth(30);
            tabGoodentry.getColumnModel().getColumn(1).setMaxWidth(30);
            tabGoodentry.getColumnModel().getColumn(3).setMinWidth(100);
            tabGoodentry.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabGoodentry.getColumnModel().getColumn(3).setMaxWidth(100);
            tabGoodentry.getColumnModel().getColumn(4).setMinWidth(120);
            tabGoodentry.getColumnModel().getColumn(4).setPreferredWidth(120);
            tabGoodentry.getColumnModel().getColumn(4).setMaxWidth(120);
            tabGoodentry.getColumnModel().getColumn(5).setMinWidth(125);
            tabGoodentry.getColumnModel().getColumn(5).setPreferredWidth(125);
            tabGoodentry.getColumnModel().getColumn(5).setMaxWidth(125);
            tabGoodentry.getColumnModel().getColumn(6).setMinWidth(150);
            tabGoodentry.getColumnModel().getColumn(6).setPreferredWidth(150);
            tabGoodentry.getColumnModel().getColumn(6).setMaxWidth(150);
            tabGoodentry.getColumnModel().getColumn(7).setMinWidth(135);
            tabGoodentry.getColumnModel().getColumn(7).setPreferredWidth(135);
            tabGoodentry.getColumnModel().getColumn(7).setMaxWidth(135);
        }

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel50.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Taxable Amount  :");

        txtTaxableamt.setEditable(false);
        txtTaxableamt.setBackground(new java.awt.Color(204, 255, 204));
        txtTaxableamt.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTaxableamt.setBorder(null);

        txtCGST.setEditable(false);
        txtCGST.setBackground(new java.awt.Color(204, 255, 204));
        txtCGST.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtCGST.setBorder(null);

        txtTotalamt.setBackground(new java.awt.Color(204, 255, 204));
        txtTotalamt.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTotalamt.setBorder(null);

        txtDiscountprct.setBackground(new java.awt.Color(240, 240, 240));
        txtDiscountprct.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtDiscountprct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountprctKeyReleased(evt);
            }
        });

        txtDiscountamt.setBackground(new java.awt.Color(240, 240, 240));
        txtDiscountamt.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtDiscountamt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountamtKeyReleased(evt);
            }
        });

        txtPaymentdetails.setBackground(new java.awt.Color(240, 240, 240));
        txtPaymentdetails.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        txtAdvance.setBackground(new java.awt.Color(240, 240, 240));
        txtAdvance.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtAdvance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAdvanceKeyReleased(evt);
            }
        });

        txtBalance.setBackground(new java.awt.Color(204, 255, 204));
        txtBalance.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtBalance.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel4.setText("CGST");

        txtIGST.setEditable(false);
        txtIGST.setBackground(new java.awt.Color(204, 255, 204));
        txtIGST.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtIGST.setBorder(null);

        txtSGST.setEditable(false);
        txtSGST.setBackground(new java.awt.Color(204, 255, 204));
        txtSGST.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtSGST.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel5.setText("SGST");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel6.setText("IGST");

        jLabel51.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText(" Amount  :");

        jLabel52.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText(" Discount [%]  :");

        jLabel53.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText(" Discount Amount  :");

        cmbPaymenttype.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmbPaymenttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type.", "Cash", "Cheque", "NFTS", "RTGS", "DD", "Credit" }));
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

        jLabel54.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText(" Payment Type  :");

        btnPrint.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnPrint.setText("Print Bill");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave.setText("Save Bill");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText(" Payment Details  :");

        jLabel56.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText(" Paid Amount  :");

        jLabel57.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText(" Balance Amount  :");

        txtPayableamt.setBackground(new java.awt.Color(240, 240, 240));
        txtPayableamt.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText(" Payable Amount  :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTaxableamt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCGST)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSGST, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtIGST, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalamt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscountprct, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscountamt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPaymentdetails)
                            .addComponent(txtAdvance)
                            .addComponent(txtBalance)
                            .addComponent(cmbPaymenttype, 0, 200, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRoundup, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPayableamt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTaxableamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiscountprct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiscountamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPayableamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPaymenttype, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaymentdetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRoundup, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel35.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Party Name  :");

        txtPartyname.setBackground(new java.awt.Color(240, 240, 240));
        txtPartyname.setFont(new java.awt.Font("Mangal", 1, 14)); // NOI18N
        txtPartyname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPartynameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPartynameKeyReleased(evt);
            }
        });

        dcStart.setDateFormatString("yyyy-MM-dd");
        dcStart.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Date  :");

        txtContact.setBackground(new java.awt.Color(240, 240, 240));
        txtContact.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContactKeyReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Contact No.  :");

        txtAddress.setBackground(new java.awt.Color(240, 240, 240));
        txtAddress.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txtAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddressKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddressKeyReleased(evt);
            }
        });

        cmbState.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cmbState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select State", "Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chattisgarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Lakshadweep Islands", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Pondicherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal" }));
        cmbState.setSelectedIndex(21);
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
        txtGSTIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGSTINKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGSTINKeyReleased(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Address");

        jLabel43.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("GSTIN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPartyname, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtGSTIN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbState, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcStart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(36, 36, 36))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGSTIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbState, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPartyname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(1, 1, 1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMain1Layout = new javax.swing.GroupLayout(pnlMain1);
        pnlMain1.setLayout(pnlMain1Layout);
        pnlMain1Layout.setHorizontalGroup(
            pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMain1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jMenuItem11.setText("Stock");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        Reports.add(jMenuItem11);

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

    private void cmbUnitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbUnitsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnitsMouseClicked
    int unitid = 0;
    private void cmbUnitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnitsActionPerformed
        int id = cmbUnits.getSelectedIndex();
        if (!(id == 0 || id == -1)) {
            try {
                String txt = cmbUnits.getSelectedItem().toString();
                ResultSet rs = st.executeQuery("select * from tabunits where Unit='" + txt + "' ");

                if (rs.next()) {

                    unitid = rs.getInt("Sr");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }
        }
    }//GEN-LAST:event_cmbUnitsActionPerformed

    private void cmnTaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmnTaxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmnTaxMouseClicked
    String igst_prct = "";
    int taxid = 0;
    private void cmnTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmnTaxActionPerformed
        int id = cmnTax.getSelectedIndex();
        if (!(id == 0 || id == -1)) {
            try {
                String txt = cmnTax.getSelectedItem().toString();
                ResultSet rs = st.executeQuery("select * from tabtax where Taxname='" + txt + "' ");

                if (rs.next()) {

                    gst_prct = rs.getString("Rate");
                    igst_prct = rs.getString("IGSTP");
                    taxid = rs.getInt("Sr");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }
            calTaxable_Amount();
        }
    }//GEN-LAST:event_cmnTaxActionPerformed
    BigDecimal old_qty;
    private void tabGoodentryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabGoodentryMouseClicked

        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);

        old_qty = BigDecimal.ZERO;

        String srr = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 0).toString();
        String desc = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 2).toString();
        String qty = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 3).toString();
        String unit = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 4).toString();
        String price = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 5).toString();
        String tax = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 6).toString();
        String amount = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 7).toString();

        old_qty = new BigDecimal(qty);
        txtItemname.setText(desc);
        txtQty.setText(qty);
        txtPrice.setText(price);
        txtAmount.setText(amount);
        cmbUnits.setSelectedItem(unit);
        cmnTax.setSelectedItem(tax);

        try {

            ResultSet rs = st.executeQuery("select * from taborder where Sr='" + srr + "' ");

            if (rs.next()) {
                itemid = rs.getInt("Itemid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }

    }//GEN-LAST:event_tabGoodentryMouseClicked

    private void cmbPaymenttypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPaymenttypeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPaymenttypeMouseClicked

    private void cmbPaymenttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaymenttypeActionPerformed
        String txt = cmbPaymenttype.getSelectedItem().toString();
        if (txt.equals("Credit")) {
            txtBalance.setText(txtPayableamt.getText());
            txtAdvance.setText("0.0");
        } else {
            txtAdvance.setText(txtPayableamt.getText());
            txtBalance.setText("0.0");
        }
    }//GEN-LAST:event_cmbPaymenttypeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String ssr = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 0).toString();
        String qty = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 3).toString();
        int result = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete.");

        if (result == JOptionPane.YES_OPTION) {

            try {
                int updateid = 0;
                int tempid = 0;
                ResultSet rs2 = st.executeQuery("select * from tabstock_datewise where Sr = '" + ssr + "'  ");
                if (rs2.next()) {
                    tempid = rs2.getInt("Itemid");
                }

                BigDecimal tabfqty = BigDecimal.ZERO;
                BigDecimal temp = new BigDecimal(qty);
                ResultSet rs1 = st.executeQuery("select * from tabstock_datewise where Itemid='" + tempid + "'  ");
                while (rs1.next()) {
                    tabfqty = rs1.getBigDecimal("FQty");
                    updateid = rs1.getInt("Sr");
                }
                BigDecimal fqty = tabfqty.add(temp);

                st.executeUpdate("update tabstock_datewise set FQty='" + fqty + "' where Sr = '" + updateid + "'  ");
                st.executeUpdate("delete from tabstock_datewise where Sr = '" + ssr + "'  ");
                btnAdd.setEnabled(true);
                showReport();
                 chkIGST.setSelected(false);
                txtItemname.setText("");
                txtQty.setText("");
                txtPrice.setText("");
                txtAmount.setText("");
                cmbUnits.setSelectedIndex(0);
                cmnTax.setSelectedIndex(0);
                txtItemname.requestFocusInWindow();
                
                int row = tabGoodentry.getRowCount();
                if (row < 1) {
                    Company.setEnabled(true);
                    Admin.setEnabled(true);
                    Transaction.setEnabled(true);
                    Reports.setEnabled(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        refreshid = 0;
        String pt = cmbPaymenttype.getSelectedItem().toString();
        String ptdetails = txtPaymentdetails.getText();
        String payable = txtPayableamt.getText();
        String advance = txtAdvance.getText().trim();

        if (pt.equals("Select Type.")) {
            JOptionPane.showMessageDialog(rootPane, "Please Select Payment Option..");
        } else if (advance.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Paid Amount");
        } else {
            saveData();
            String shopname = jLabel1.getText();
            String billno = txtBillNo.getText();

            Print_Option_Sale sale = new Print_Option_Sale(shopname, billno);
            sale.setVisible(true);
        }


    }//GEN-LAST:event_btnPrintActionPerformed
    int refreshid = 0;
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        refreshid = 1;
        saveData();

    }//GEN-LAST:event_btnSaveActionPerformed

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

    public void convertWord() {
        String amt = txtPayableamt.getText();
        System.out.println("amt : " + amt);
        int one = Integer.parseInt(amt);
        totalamount_word_txt = convert(one) + " Rs Only |-";

        System.out.println("totalamount_word_txt : " + totalamount_word_txt);
    }

    String totalamount_word_txt = "";

    public void saveData() {
        String date = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        String taxable = txtTaxableamt.getText();
        String totalamt = txtTotalamt.getText();
        String cgst = txtCGST.getText();
        String sgst = txtSGST.getText();
        String igst = txtIGST.getText();
        String discount = txtDiscountprct.getText();
        String discamt = txtDiscountamt.getText();
        String pt = cmbPaymenttype.getSelectedItem().toString();
        String ptdetails = txtPaymentdetails.getText();
        String payable = txtPayableamt.getText();
        String advance = txtAdvance.getText().trim();
        String balance = txtBalance.getText();
        String roundup = lblRoundup.getText();
        if (pt.equals("Select Type.")) {
            JOptionPane.showMessageDialog(rootPane, "Please Select Payment Option..");
        } else if (advance.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Paid Amount");
        } else {
            convertWord();
            try {
                ResultSet rs = st.executeQuery("select * from taborder_total where BillNo='" + txtBillNo.getText() + "' ");
                if (rs.next()) {
                    st.executeUpdate("update taborder_total set Partyid='" + partyid + "',Date1='" + date + "',"
                            + "TCGST='" + cgst + "',TSGST='" + sgst + "',TIGST='" + igst + "',Totalamt='" + totalamt + "'"
                            + ",TaxableamtT='" + taxable + "',Discountprct='" + discount + "',Discountamt='" + discamt + "'"
                            + ",Payableamt='" + payable + "',PT='" + pt + "',PTDetails='" + ptdetails + "'"
                            + ",Advance='" + advance + "',Balance='" + balance + "',Roundup='" + roundup + "',TGST='" + gst + "'"
                            + " ,Adv_txt='" + totalamount_word_txt + "' where BillNo='" + txtBillNo.getText() + "' ");

                    st.executeUpdate(" update taborder_payment_details set Date1='" + date + "',Totalamount='" + payable + "',Custid='" + partyid + "' "
                            + ",Advance='" + advance + "',Balance='" + balance + "' "
                            + ",Payment='" + pt + "',Chqno='" + ptdetails + "',Adv_txt='" + totalamount_word_txt + "' WHERE BillNo='" + txtBillNo.getText() + "' ");
                    if (refreshid == 1) {
                        JOptionPane.showMessageDialog(rootPane, "Record Updated..");
                        Sale p = new Sale(role, username);
                        p.setVisible(true);
                        this.dispose();
                    }

                } else {
                    st.executeUpdate("insert into taborder_total(Partyid,Date1,BillNo,TCGST,TSGST,TIGST,"
                            + "Totalamt,TaxableamtT,Discountprct,Discountamt,Payableamt,PT,PTDetails,Advance,Balance,TGST,Roundup,Adv_txt)"
                            + "values ('" + partyid + "','" + date + "','" + txtBillNo.getText() + "','" + cgst + "','" + sgst + "'"
                            + ",'" + igst + "','" + totalamt + "','" + taxable + "','" + discount + "','" + discamt + "','" + payable + "',"
                            + "'" + pt + "','" + ptdetails + "','" + advance + "','" + balance + "','" + gst + "','" + roundup + "','" + totalamount_word_txt + "' )  ");

                    st.executeUpdate("insert into taborder_payment_details(Date1,Custid,BillNo,Totalamount,Advance,Balance,Payment,Chqno,Adv_txt,Bankname,Status) "
                            + "values ('" + date + "','" + partyid + "','" + txtBillNo.getText() + "','" + payable + "'"
                            + ",'" + advance + "','" + balance + "','" + pt + "','" + ptdetails + "','" + totalamount_word_txt + "','.','0')");

                    if (refreshid == 1) {
                        JOptionPane.showMessageDialog(rootPane, "Record Saved..");
                        Sale p = new Sale(role, username);
                        p.setVisible(true);
                        this.dispose();
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }
        }

    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        String partyname = txtPartyname.getText();
        String contact = txtContact.getText();
        String addrss = txtAddress.getText();
        String gstin = txtGSTIN.getText();
        String state = cmbState.getSelectedItem().toString();
        
        String date = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        String billno = "";
        String mcenter = "";
        String narration = "";

        String qty = txtQty.getText();
        String unit = cmbUnits.getSelectedItem().toString();
        String price = txtPrice.getText();
        String amt = txtAmount.getText();
       if (qty.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Item's Qty !.");
        } else if (price.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Item's Price !.");
        } else {

            try {

                 ResultSet rs1 = st.executeQuery("SELECT * FROM tabparty WHERE Partyname='" + partyname + "' ");
                if (!(rs1.next())) {
                    st.executeUpdate(" insert into tabparty(Partyname,Contact,Address,State,Code,GSTIN,Emailid) "
                            + "values('" + partyname + "','" + contact + "','" + addrss + "','" + state + "','" + code + "'"
                            + ",'" + gstin + "','')  ");
                }
                String txt = txtPartyname.getText();
                ResultSet rs = st.executeQuery("select * from tabparty where Partyname='" + txt + "' ");

                if (rs.next()) {

                    partyid = rs.getInt("Sr");
                }
                
                BigDecimal tabqty = BigDecimal.ZERO;
                BigDecimal tempqty = new BigDecimal(qty);

                int updateid = 0;
                ResultSet rs2 = st.executeQuery("SELECT * FROM tabstock_datewise WHERE Itemid='" + itemid + "'   ");

                while ((rs2.next())) {
                    tabqty = rs2.getBigDecimal("FQty");
                    updateid = rs2.getInt("Sr");
                }
                BigDecimal fqty = tabqty.subtract(tempqty);
                fqty = fqty.setScale(2, RoundingMode.CEILING);

                st.executeUpdate("insert into taborder(Partyid,Date1,BillNo,PBillNo,MCenter,Narration,"
                        + "Itemid,Qty,Unitid,Price,Tax,Amount,CGST,SGST,IGST,CGSTP,SGSTP,IGSTP,Taxableamt,Cr_Note)"
                        + "values ('" + partyid + "','" + date + "','" + txtBillNo.getText() + "','" + billno + "','" + mcenter + "'"
                        + ",'" + narration + "','" + itemid + "','" + qty + "','" + unitid + "','" + price + "','" + taxid + "',"
                        + "'" + amt + "','" + cgst + "','" + sgst + "','" + igst_value + "','" + half_gst + "'"
                        + ",'" + half_gst + "','" + igst_prct + "','" + taxable_amount + "','0' )  ");

                st.executeUpdate(" update tabstock_datewise set FQty='" + fqty + "' where Sr='" + updateid + "' ");

                showReport();
                chkIGST.setSelected(false);
                txtItemname.setText("");
                txtQty.setText("");
                txtPrice.setText("");
                txtAmount.setText("");
                cmbUnits.setSelectedIndex(0);
                cmnTax.setSelectedIndex(0);
                txtItemname.requestFocusInWindow();

                Company.setEnabled(false);
                Admin.setEnabled(false);
                Transaction.setEnabled(false);
                Reports.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }

        }

    }//GEN-LAST:event_btnAddActionPerformed
    BigDecimal gst = BigDecimal.ZERO;

    public void showReport() {

        DefaultTableModel model2 = (DefaultTableModel) this.tabGoodentry.getModel();
        model2.setRowCount(0);

        tabGoodentry.setRowHeight(25);
        BigDecimal taxableamt = BigDecimal.ZERO;
        BigDecimal cgst = BigDecimal.ZERO;
        BigDecimal sgst = BigDecimal.ZERO;
        BigDecimal igst = BigDecimal.ZERO;
        BigDecimal totalamt = BigDecimal.ZERO;
        BigDecimal totalamt1 = BigDecimal.ZERO;

        gst = BigDecimal.ZERO;
        DefaultTableModel df;
        df = (DefaultTableModel) tabGoodentry.getModel();
        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT s.Partyid,s.Sr,s.Qty,s.Price,s.Amount,s.CGST,s.SGST,s.IGST,s.Taxableamt ,"
                    + "itm.Itemname,t.Taxname,u.Unit  from taborder s,tabunits u,"
                    + "tabtax t,tabitems itm  where s.BillNo='" + txtBillNo.getText() + "' && s.Itemid=itm.Sr && "
                    + " s.Unitid =u.Sr && s.Tax=t.Sr  ");

            while (rs.next()) {
                count = rs.getRow();

                pid = rs.getInt("Partyid");
                String billno = rs.getString("Sr");
                String itemname = rs.getString("Itemname");
                String qty = rs.getString("Qty");
                String unit = rs.getString("Unit");
                String price = rs.getString("Price");
                String taxid = rs.getString("Taxname");
                String amt = rs.getString("Amount");

                taxableamt = taxableamt.add(rs.getBigDecimal("Taxableamt"));
                cgst = cgst.add(rs.getBigDecimal("CGST"));
                sgst = sgst.add(rs.getBigDecimal("SGST"));
                igst = igst.add(rs.getBigDecimal("IGST"));
                totalamt = totalamt.add(rs.getBigDecimal("Amount"));
                totalamt1 = totalamt;
                df.addRow(new Object[]{billno, count, itemname, qty, unit, price, taxid, amt});

                tabGoodentry.setModel(df);

            }
            taxableamt = taxableamt.setScale(2, RoundingMode.CEILING);
            cgst = cgst.setScale(2, RoundingMode.CEILING);
            sgst = sgst.setScale(2, RoundingMode.CEILING);
            igst = igst.setScale(2, RoundingMode.CEILING);
            totalamt = totalamt.setScale(0, RoundingMode.UP);
            System.out.println("Totalamt : " + totalamt);
            BigDecimal temp2 = cgst.add(sgst);
            gst = temp2.add(igst);

            totalamt1 = totalamt1.setScale(2, RoundingMode.UP);
            BigDecimal diff = totalamt.subtract(totalamt1);
            diff = diff.setScale(2, RoundingMode.HALF_UP);
            lblRoundup.setText(diff.toString());

            txtTaxableamt.setText(taxableamt.toString());
            txtCGST.setText(cgst.toString());
            txtSGST.setText(sgst.toString());
            txtIGST.setText(igst.toString());
            txtTotalamt.setText(totalamt.toString());
            txtPayableamt.setText(totalamt.toString());
            txtAdvance.setText(totalamt.toString());
            txtBalance.setText("0.0");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        String date = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        String billno = "";
        String mcenter = "";
        String narration = "";
        String gsr = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 0).toString();
        String qty = txtQty.getText();
        String unit = cmbUnits.getSelectedItem().toString();
        String price = txtPrice.getText();
        String amt = txtAmount.getText();
        if (partyid == 0) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Party's Name !.");
        } else if (itemid == 0) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Item's Name !.");
        } else if (qty.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Item's Qty !.");
        } else if (price.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Item's Price !.");
        } else {

            try {

                BigDecimal tabqty = BigDecimal.ZERO;
                BigDecimal tempqty = new BigDecimal(qty);

                int updateid = 0;
                ResultSet rs4 = st.executeQuery("SELECT * FROM tabitems WHERE Itemname='" + txtItemname.getText() + "'   ");

                if ((rs4.next())) {
                    itemid = rs4.getInt("Sr");
                }

                ResultSet rs2 = st.executeQuery("SELECT * FROM tabstock_datewise WHERE Itemid='" + itemid + "'   ");

                while ((rs2.next())) {
                    tabqty = rs2.getBigDecimal("FQty");
                    updateid = rs2.getInt("Sr");
                }

                BigDecimal diff = old_qty.subtract(tempqty);

                BigDecimal fqty = tabqty.add(diff);
                fqty = fqty.setScale(2, RoundingMode.CEILING);

                ResultSet rs3 = st.executeQuery("Select * from taborder where Sr='" + gsr + "'  ");
                if (rs3.next()) {

                    st.executeUpdate(" update taborder set Qty='" + qty + "',Itemid='" + itemid + "',Unitid='" + unitid + "',Price='" + price + "',"
                            + "Tax='" + taxid + "',Amount='" + amt + "',CGST='" + cgst + "',SGST='" + sgst + "',IGST='" + igst_value + "',"
                            + "CGSTP='" + half_gst + "',SGSTP='" + half_gst + "',IGSTP='" + igst_prct + "',Taxableamt='" + taxable_amount + "' where Sr='" + gsr + "'   ");
                }

                st.executeUpdate(" update tabstock_datewise set FQty='" + fqty + "' where Sr='" + updateid + "' ");

                showReport();
                chkIGST.setSelected(false);
                txtItemname.setText("");
                txtQty.setText("");
                txtPrice.setText("");
                txtAmount.setText("");
                cmbUnits.setSelectedIndex(0);
                cmnTax.setSelectedIndex(0);
                btnAdd.setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            }

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        calTaxable_Amount();
    }//GEN-LAST:event_txtQtyKeyReleased
    String mrpprice = "";
    int itemid = 0;
    private void txtItemnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemnameKeyReleased

        String unitid = "", taxid = "";
        try {
            String temp = txtItemname.getText();
            ResultSet rs4 = st.executeQuery("select * from tabitems where Itemname LIKE '%" + temp + "%' ");

            if (!(rs4.next())) {
                txtItemname.setText("");
            }

            String txt = txtItemname.getText();
            ResultSet rs = st.executeQuery("select * from tabitems where Itemname='" + txt + "' ");

            if (rs.next()) {
                txtPrice.setText(rs.getString("Salesprice"));
                mrpprice = rs.getString("MRP");
                unitid = rs.getString("Unit");
                taxid = rs.getString("Taxid");
                itemid = rs.getInt("Sr");

            } 
            String unitname = "";
            ResultSet rs1 = st.executeQuery("select * from tabunits where Sr='" + unitid + "' ");

            if (rs1.next()) {
                unitname = rs1.getString("Unit");
                cmbUnits.setSelectedItem(rs1.getString("Unit"));

            }

            ResultSet rs2 = st.executeQuery("select * from tabtax where Sr='" + taxid + "' ");

            if (rs2.next()) {
                cmnTax.setSelectedItem(rs2.getString("Taxname"));
            }

            ResultSet rs3 = st.executeQuery("select * from tabstock_datewise where Itemid='" + itemid + "' ");

            while (rs3.next()) {
                String stock = rs3.getString("FQty");
                lblStock.setText("Stock Qty : " + stock + " - " + unitname);

                txtQty.requestFocusInWindow();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }//GEN-LAST:event_txtItemnameKeyReleased

    private void txtPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyReleased
        calTaxable_Amount();
    }//GEN-LAST:event_txtPriceKeyReleased
    String igst_value = "";
    private void chkIGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIGSTActionPerformed
        if (chkIGST.isSelected()) {
            calTaxable_Amount_IGST();
        } else {
            calTaxable_Amount();
        }
    }//GEN-LAST:event_chkIGSTActionPerformed
    int partyid = 0;
    BigDecimal partybalance = BigDecimal.ZERO;
    private void txtDiscountprctKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountprctKeyReleased
        if (txtDiscountprct.getText().equals("")) {
            txtDiscountamt.setText("");
            txtPayableamt.setText(txtTotalamt.getText());
            txtAdvance.setText(txtTotalamt.getText());
            txtBalance.setText("0");
        }
        calDiscount();
    }//GEN-LAST:event_txtDiscountprctKeyReleased

    private void txtDiscountamtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountamtKeyReleased
        if (txtDiscountamt.getText().equals("")) {
            txtPayableamt.setText(txtTotalamt.getText());
            txtAdvance.setText(txtTotalamt.getText());
            txtBalance.setText("0");
        }
        txtDiscountprct.setText("");
        double finalamount = Double.parseDouble(txtTotalamt.getText());
        double jama = Double.parseDouble(txtDiscountamt.getText());
        finalamount = finalamount;
        if (!(finalamount < jama)) {
            BigDecimal big_f = new BigDecimal(txtTotalamt.getText());
            BigDecimal big_j = new BigDecimal(txtDiscountamt.getText());

            BigDecimal temp = big_f.subtract(big_j);
            BigDecimal temp1 = temp;
            System.out.println("temp : " + temp);
            temp = temp.setScale(0, RoundingMode.UP);

            temp1 = temp1.setScale(2, RoundingMode.UP);
            BigDecimal diff = temp.subtract(temp1);
            diff = diff.setScale(2, RoundingMode.HALF_UP);
            lblRoundup.setText(diff.toString());

            txtPayableamt.setText(temp.toString());
            txtBalance.setText(temp.toString());
        }
    }//GEN-LAST:event_txtDiscountamtKeyReleased

    private void txtAdvanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdvanceKeyReleased

        if (txtAdvance.getText().equals("")) {
            txtAdvance.setText("");
            txtBalance.setText(txtTotalamt.getText());
        } else {
            double finalamount = Double.parseDouble(txtPayableamt.getText());
            double jama = Double.parseDouble(txtAdvance.getText());
            if (!(finalamount < jama)) {
                txtBalance.setText(Double.toString(finalamount - jama));
            } else {
                txtAdvance.setText("");
                txtBalance.setText("");
            }
        }

    }//GEN-LAST:event_txtAdvanceKeyReleased

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

    private void txtItemnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemnameKeyPressed
        int s = evt.getKeyCode();
        if (s == evt.VK_ENTER || s == evt.VK_TAB) {

            txtQty.requestFocusInWindow();

        }
    }//GEN-LAST:event_txtItemnameKeyPressed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        int s = evt.getKeyCode();
        if (s == evt.VK_ENTER || s == evt.VK_TAB) {

            txtPrice.requestFocusInWindow();

        }
    }//GEN-LAST:event_txtQtyKeyPressed

    private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
        int s = evt.getKeyCode();
        if (s == evt.VK_ENTER || s == evt.VK_TAB) {

            btnAdd.requestFocusInWindow();

        }
    }//GEN-LAST:event_txtPriceKeyPressed

    private void txtPartynameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartynameKeyPressed
        int s = evt.getKeyCode();
        if (s == evt.VK_ENTER || s == evt.VK_TAB) {

            txtItemname.requestFocusInWindow();

        }
    }//GEN-LAST:event_txtPartynameKeyPressed

    private void txtPartynameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartynameKeyReleased
        try {
            String txt = txtPartyname.getText();
            ResultSet rs = st.executeQuery("select * from tabparty where Partyname='" + txt + "' ");

            if (rs.next()) {

                partyid = rs.getInt("Sr");
                txtContact.setText(rs.getString("Contact"));
                txtAddress.setText(rs.getString("Address"));
                txtGSTIN.setText(rs.getString("GSTIN"));
                cmbState.setSelectedItem(rs.getString("State"));
            }

            ResultSet rs1 = st.executeQuery("SELECT Balance from taborder_payment_details where Custid='" + partyid + "' ORDER BY Sr DESC ");
            if (rs1.next()) {
              //  lblOldBalance.setText(rs1.getString("Balance"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }//GEN-LAST:event_txtPartynameKeyReleased

    private void txtContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyPressed

        int s = evt.getKeyCode();
        if ((s >= evt.VK_0 && s <= evt.VK_9) || (s >= evt.VK_NUMPAD0 && s <= evt.VK_NUMPAD9) || s == evt.VK_BACK_SPACE || s == evt.VK_TAB
            || s == evt.VK_LEFT || s == evt.VK_RIGHT) {

            txtContact.setEditable(true);

        } else {
            txtContact.setEditable(false);

        }
    }//GEN-LAST:event_txtContactKeyPressed

    private void txtContactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyReleased
        try {
            String txt = txtContact.getText();
            ResultSet rs = st.executeQuery("select * from tabparty where Contact='" + txt + "' ");

            if (rs.next()) {

                partyid = rs.getInt("Sr");
                txtPartyname.setText(rs.getString("Partyname"));
            } else {
                partyid = 0;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }//GEN-LAST:event_txtContactKeyReleased

    private void txtAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressKeyPressed

    private void txtAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressKeyReleased

    private void cmbStateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStateMouseClicked
String code="";
    private void cmbStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStateActionPerformed
        if (cmbState.getSelectedIndex() != 0) {
            String state = cmbState.getSelectedItem().toString();
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
        }
    }//GEN-LAST:event_cmbStateActionPerformed

    private void txtGSTINKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGSTINKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGSTINKeyPressed

    private void txtGSTINKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGSTINKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGSTINKeyReleased

    public void calDiscount() {

        Double one, two, tot, finaltotal;
        if (txtTotalamt.getText().equals("") && txtDiscountprct.getText().equals("")) {
            txtAdvance.setText(txtTotalamt.getText());

        } else if (txtTotalamt.getText().equals("")) {

        } else if (txtDiscountprct.getText().equals("")) {

        } else {
            one = Double.parseDouble(txtTotalamt.getText());
            two = Double.parseDouble(txtDiscountprct.getText());

            tot = one * (two / 100);

            BigDecimal Ans = new BigDecimal(tot);
            Ans = Ans.setScale(2, RoundingMode.CEILING);
            String txt = Ans.toString();
            txtDiscountamt.setText(txt);

            double temp = Double.parseDouble(txtDiscountamt.getText());

            one = one;
            double pa = (one - temp);
            BigDecimal aa = new BigDecimal(pa);
            System.out.println("aa : " + aa);
            BigDecimal temp1 = aa;
            aa = aa.setScale(0, RoundingMode.UP);
            String aatxt = aa.toString();

            temp1 = temp1.setScale(2, RoundingMode.UP);
            BigDecimal diff = aa.subtract(temp1);
            diff = diff.setScale(2, RoundingMode.HALF_UP);
            lblRoundup.setText(diff.toString());

            txtPayableamt.setText(aatxt);
            txtBalance.setText(aatxt);
        }
    }

    BigDecimal taxable_amount;
    BigDecimal cgst;
    BigDecimal sgst;
    BigDecimal total_amount;
    BigDecimal half_gst;
    String gst_prct = "";

    public void calTaxable_Amount() {
        BigDecimal ans = BigDecimal.ZERO;
        taxable_amount = BigDecimal.ZERO;
        cgst = BigDecimal.ZERO;
        sgst = BigDecimal.ZERO;
        total_amount = BigDecimal.ZERO;
        half_gst = BigDecimal.ZERO;
        if (txtPrice.getText().equals("") || txtQty.getText().equals("")) {

        } else if (txtPrice.equals("")) {

        } else if (txtQty.equals("")) {

        } else {
            BigDecimal qty = new BigDecimal(txtQty.getText());
            BigDecimal price = new BigDecimal(txtPrice.getText());

            taxable_amount = price.multiply(qty);

            taxable_amount = taxable_amount.setScale(2, RoundingMode.CEILING);
            String div = "100";
            String half = "2";
            BigDecimal temp = new BigDecimal(gst_prct);
            BigDecimal big_half = new BigDecimal(half);
            BigDecimal big_div = new BigDecimal(div);

            half_gst = temp.divide(big_half, 2, RoundingMode.CEILING);
            BigDecimal gst1 = half_gst.divide(big_div, 2, RoundingMode.CEILING);

            cgst = taxable_amount.multiply(gst1);
            cgst = cgst.setScale(2, RoundingMode.CEILING);
            sgst = taxable_amount.multiply(gst1);
            sgst = sgst.setScale(2, RoundingMode.CEILING);
            BigDecimal totgst = cgst.add(sgst);

            BigDecimal total_gst_taxableamt = taxable_amount.add(totgst);

            total_gst_taxableamt = total_gst_taxableamt.setScale(2, RoundingMode.CEILING);

            txtAmount.setText(total_gst_taxableamt.toString());

        }
    }

    public void calTaxable_Amount_IGST() {
        BigDecimal ans = BigDecimal.ZERO;
        taxable_amount = BigDecimal.ZERO;
        cgst = BigDecimal.ZERO;
        sgst = BigDecimal.ZERO;
        total_amount = BigDecimal.ZERO;
        half_gst = BigDecimal.ZERO;
        if (txtPrice.getText().equals("") || txtQty.getText().equals("")) {

        } else if (txtPrice.equals("")) {

        } else if (txtQty.equals("")) {

        } else {
            BigDecimal qty = new BigDecimal(txtQty.getText());
            BigDecimal price = new BigDecimal(txtPrice.getText());

            taxable_amount = price.multiply(qty);

            taxable_amount = taxable_amount.setScale(2, RoundingMode.CEILING);
            String div = "100";
            String half = "2";
            BigDecimal temp = new BigDecimal(gst_prct);
            BigDecimal big_half = new BigDecimal(half);
            BigDecimal big_div = new BigDecimal(div);

            half_gst = temp.divide(big_half, 2, RoundingMode.CEILING);
            BigDecimal gst1 = half_gst.divide(big_div, 2, RoundingMode.CEILING);

            cgst = taxable_amount.multiply(gst1);
            cgst = cgst.setScale(2, RoundingMode.CEILING);
            sgst = taxable_amount.multiply(gst1);
            sgst = sgst.setScale(2, RoundingMode.CEILING);
            BigDecimal totgst = cgst.add(sgst);
            totgst = totgst.setScale(2, RoundingMode.CEILING);
            igst_value = totgst.toString();

            totgst = totgst.add(totgst);

            BigDecimal total_gst_taxableamt = taxable_amount.add(totgst);

            total_gst_taxableamt = total_gst_taxableamt.setScale(2, RoundingMode.CEILING);

            txtAmount.setText(total_gst_taxableamt.toString());

        }
    }

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
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Admin;
    private javax.swing.JMenu Company;
    private javax.swing.JMenu Reports;
    private javax.swing.JMenu Transaction;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkIGST;
    private javax.swing.JComboBox cmbPaymenttype;
    private javax.swing.JComboBox cmbState;
    private javax.swing.JComboBox cmbUnits;
    private javax.swing.JComboBox cmnTax;
    private com.toedter.calendar.JDateChooser dcStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblRoundup;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMain1;
    private javax.swing.JTable tabGoodentry;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAdvance;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JLabel txtBillNo;
    private javax.swing.JTextField txtCGST;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtDiscountamt;
    private javax.swing.JTextField txtDiscountprct;
    private javax.swing.JTextField txtGSTIN;
    private javax.swing.JTextField txtIGST;
    private javax.swing.JTextField txtItemname;
    private javax.swing.JTextField txtPartyname;
    private javax.swing.JTextField txtPayableamt;
    private javax.swing.JTextField txtPaymentdetails;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSGST;
    private javax.swing.JTextField txtTaxableamt;
    private javax.swing.JTextField txtTotalamt;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}
