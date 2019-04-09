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
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Sale_List extends javax.swing.JFrame {

    String role, username, sr;
    Statement st;
    Connection con;

    public Sale_List() {
        initComponents();
        getDatabase_Connections();
        showDate();
        getShopname();
        showReport();
        getPartyname();
    }

    public Sale_List(String Role, String Username) {
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
        btnSave4 = new javax.swing.JButton();
        btnSave5 = new javax.swing.JButton();
        btnSave6 = new javax.swing.JButton();
        lblAmt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblSGST = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCGST = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
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

        tabGoodentry.setFont(new java.awt.Font("Mangal", 1, 13)); // NOI18N
        tabGoodentry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr", "Sr", "Bill No", "Date", "Party Name", "Payment Type", "Taxable Amount", "CGST", "SGST", "IGST", "Amount + GST", "Discount Amount", "Payable Amount", "Paid", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true, true
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
            tabGoodentry.getColumnModel().getColumn(6).setMinWidth(100);
            tabGoodentry.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabGoodentry.getColumnModel().getColumn(6).setMaxWidth(100);
            tabGoodentry.getColumnModel().getColumn(7).setMinWidth(70);
            tabGoodentry.getColumnModel().getColumn(7).setPreferredWidth(110);
            tabGoodentry.getColumnModel().getColumn(7).setMaxWidth(70);
            tabGoodentry.getColumnModel().getColumn(8).setMinWidth(70);
            tabGoodentry.getColumnModel().getColumn(8).setPreferredWidth(110);
            tabGoodentry.getColumnModel().getColumn(8).setMaxWidth(70);
            tabGoodentry.getColumnModel().getColumn(9).setMinWidth(70);
            tabGoodentry.getColumnModel().getColumn(9).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(9).setMaxWidth(70);
            tabGoodentry.getColumnModel().getColumn(10).setMinWidth(100);
            tabGoodentry.getColumnModel().getColumn(10).setPreferredWidth(100);
            tabGoodentry.getColumnModel().getColumn(10).setMaxWidth(100);
            tabGoodentry.getColumnModel().getColumn(11).setMinWidth(70);
            tabGoodentry.getColumnModel().getColumn(11).setPreferredWidth(70);
            tabGoodentry.getColumnModel().getColumn(11).setMaxWidth(70);
            tabGoodentry.getColumnModel().getColumn(12).setMinWidth(90);
            tabGoodentry.getColumnModel().getColumn(12).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(12).setMaxWidth(90);
            tabGoodentry.getColumnModel().getColumn(13).setMinWidth(90);
            tabGoodentry.getColumnModel().getColumn(13).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(13).setMaxWidth(90);
            tabGoodentry.getColumnModel().getColumn(14).setMinWidth(90);
            tabGoodentry.getColumnModel().getColumn(14).setPreferredWidth(90);
            tabGoodentry.getColumnModel().getColumn(14).setMaxWidth(90);
        }

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
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
        jLabel4.setText("Search By Party's Name | Contact");

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

        btnSave4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave4.setText("Print");
        btnSave4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave4ActionPerformed(evt);
            }
        });

        btnSave5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave5.setText("Statement");
        btnSave5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave5ActionPerformed(evt);
            }
        });

        btnSave6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnSave6.setText("Dtl");
        btnSave6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave6ActionPerformed(evt);
            }
        });

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
                .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                                .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtItemname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPaymenttype, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        lblAmt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel9.setText("Amount ");

        lblSGST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel5.setText("SGST");

        lblCGST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel10.setText("CGST");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCGST, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSGST, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(lblSGST, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCGST, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addContainerGap())
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
    private void tabGoodentryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabGoodentryMouseClicked
        updateid = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 1).toString();

    }//GEN-LAST:event_tabGoodentryMouseClicked

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        String id = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 2).toString();
        if (role.equals("Admin")) {
            try {
                st.executeUpdate("delete from taborder where BillNo = '" + id + "'  ");
                st.executeUpdate("delete from taborder_total where BillNo = '" + id + "'  ");
                st.executeUpdate("delete from taborder_payment_details where BillNo = '" + id + "'  ");

                showReport();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
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
        Sale item = new Sale(role, username, id);
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

    private void btnSave4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave4ActionPerformed
        String shopname = jLabel1.getText();
        String billno = tabGoodentry.getValueAt(tabGoodentry.getSelectedRow(), 2).toString();

        Print_Option_Sale sale = new Print_Option_Sale(shopname, billno);
        sale.setVisible(true);
    }//GEN-LAST:event_btnSave4ActionPerformed
    int custid = 0;
    private void btnSave5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave5ActionPerformed

        Sdate = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        Edate = ((JTextField) dcEnd.getDateEditor().getUiComponent()).getText();
        if (Sdate.equals("") || Edate.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Select proper dates");
        } else {
            Map<String, Object> param = new HashMap<String, Object>();

            String shopname = jLabel1.getText();
            String address = "";
            try {
                ResultSet rs = st.executeQuery("SELECT * FROM tabshopname WHERE Shopname='" + shopname + "'  ");
                if (rs.next()) {
                    address = rs.getString("Address");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            param.put("Shopname", shopname);
            param.put("Address", address);

            param.put("FDate", Sdate);
            param.put("TDate", Edate);

            Collection ls = null;
            generateReport("Xc_Emp_Sales", ls, param);
        }

    }//GEN-LAST:event_btnSave5ActionPerformed

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

    private void btnSave6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave6ActionPerformed

        Sdate = ((JTextField) dcStart.getDateEditor().getUiComponent()).getText();
        Edate = ((JTextField) dcEnd.getDateEditor().getUiComponent()).getText();
        if (Sdate.equals("") || Edate.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Select proper dates");
        } else {
            Map<String, Object> param = new HashMap<String, Object>();

            String shopname = jLabel1.getText();
            String address = "";
            try {
                ResultSet rs = st.executeQuery("SELECT * FROM tabshopname WHERE Shopname='" + shopname + "'  ");
                if (rs.next()) {
                    address = rs.getString("Address");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            param.put("Shopname", shopname);
            param.put("Address", address);

            param.put("FDate", Sdate);
            param.put("TDate", Edate);

            Collection ls = null;
            enterBillDetails(Sdate, Edate);
            generateReport1("Xc_Emp_Sales", ls, param);
        }
    }//GEN-LAST:event_btnSave6ActionPerformed

    public void enterBillDetails(String sdate, String edate) {
        try {

            st.executeUpdate("delete from tabdetailorder   ");

            ArrayList<Integer> al = new ArrayList<>();

            ResultSet rs = st.executeQuery("SELECT BillNo FROM taborder_payment_details WHERE "
                    + "Date1 >= '" + Sdate + "'  && Date1 <= '" + Edate + "'"
                    + " && Custid='" + custid + "'  order by Sr asc ");
            while (rs.next()) {
                al.add(rs.getInt("BillNo"));
            }

            for (Integer a : al) {

                ArrayList<Integer> sr = new ArrayList<>();
                ResultSet rs11 = st.executeQuery("SELECT SR FROM taborder WHERE "
                        + "BillNo= '" + a + "'  ");

                while (rs11.next()) {
                    sr.add(rs11.getInt("Sr"));
                }

                boolean flag = true;
                for (Integer s : sr) {

                    ResultSet rs1 = st.executeQuery("SELECT * FROM taborder WHERE "
                            + "Sr= '" + s + "'  ");

                    String temp = "";
                    if (rs1.next()) {
                        int amt = rs1.getInt("CGST") + rs1.getInt("CGST");
                        temp = ("(" + rs1.getString("ItemName")
                                .concat(") Qty (" + rs1.getString("Qty") + " Bundle")
                                .concat(") Rate (" + rs1.getString("Price"))
                                .concat(") GST (" + Integer.toString(amt))
                                .concat(") Amt (" + rs1.getString("Amount")) + ")");
                    }

                    String date = "", Billamount = "", Oldamt = "", Totalamt = "", Paid = "", Balance = "";

                    if (flag == true) {
                        ResultSet rs2 = st.executeQuery("SELECT * FROM taborder_payment_details WHERE "
                                + "BillNo= '" + a + "'  ");
                        if (rs2.next()) {
                            date = rs2.getString("Date1");
                            Billamount = rs2.getString("Totalamount");
                            Oldamt = Integer.toString(rs2.getInt("TotalPayable") - rs2.getInt("Totalamount"));
                            Totalamt = rs2.getString("TotalPayable");
                            Paid = rs2.getString("Advance");
                            Balance = rs2.getString("Balance");
                            flag = false;
                        } else {
                            date = "";
                            Billamount = "";
                            Oldamt = "";
                            Totalamt = "";
                            Paid = "";
                            Balance = "";

                        }
                    }
                    st.executeUpdate(" INSERT INTO tabdetailorder(Date1,BillNo,Billamount,Oldamt,Totalamt,Paid,Balance,Itemname,Partyname)"
                            + " VALUES('" + date + "','" + a + "','" + Billamount + "','" + Oldamt + "','" + Totalamt + "',"
                            + "'" + Paid + "','" + Balance + "','" + temp + "','" + partyname_temp + "')  ");

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void generateReport(String name, Collection data, Map param) {

        try {
            System.out.println("Custid : " + custid);
            JasperDesign jd = JRXmlLoader.load("Ladger_Stmt.jrxml");
            String sql = "SELECT  p.Partyname,p.Contact,p.Address,p.State,p.Code,p.GSTIN,op.Date1,"
                    + "op.BillNo,op.Totalamount,op.Advance,op.Balance,op.Payment,op.Chqno,op.Bankname,op.Adv_txt"
                    + " from taborder_payment_details op,tabparty p where p.Sr=op.Custid && op.Custid='" + custid + "' ";

            JRDesignQuery nq = new JRDesignQuery();
            nq.setText(sql);
            jd.setQuery(nq);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void generateReport1(String name, Collection data, Map param) {

        try {
            System.out.println("Custid : " + custid);
            JasperDesign jd = JRXmlLoader.load("Ladger_Dtl_Stmt.jrxml");
            String sql = "SELECT * from tabdetailorder ";

            JRDesignQuery nq = new JRDesignQuery();
            nq.setText(sql);
            jd.setQuery(nq);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    BigDecimal tamt = BigDecimal.ZERO;
    BigDecimal pamt = BigDecimal.ZERO;
    BigDecimal bamt = BigDecimal.ZERO;
    BigDecimal tcgst = BigDecimal.ZERO;
    BigDecimal tsgst = BigDecimal.ZERO;

    public void showReport() {

        tamt = BigDecimal.ZERO;
        pamt = BigDecimal.ZERO;
        bamt = BigDecimal.ZERO;
        tcgst = BigDecimal.ZERO;
        tsgst = BigDecimal.ZERO;

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
                    + "s.Totalamt,s.TaxableamtT,s.OldBalance,s.Discountprct,s.TotalPayable,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid order by s.Sr asc ");

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
                String discount = rs.getString("OldBalance");
                String payable = rs.getString("TotalPayable");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");
                tamt = tamt.add(rs.getBigDecimal("TotalPayable"));
                tcgst = tcgst.add(rs.getBigDecimal("TCGST"));
                tsgst = tsgst.add(rs.getBigDecimal("TSGST"));

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }
            tamt = tamt.setScale(2, RoundingMode.CEILING);
            lblAmt.setText(tamt.toString());

            tcgst = tcgst.setScale(2, RoundingMode.CEILING);
            lblCGST.setText(tcgst.toString());

            tsgst = tsgst.setScale(2, RoundingMode.CEILING);
            lblSGST.setText(tsgst.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Datewsie() {
        tamt = BigDecimal.ZERO;
        tcgst = BigDecimal.ZERO;
        tsgst = BigDecimal.ZERO;
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
                    + "s.Totalamt,s.TaxableamtT,s.OldBalance,s.Discountprct,s.TotalPayable,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "'   order by s.Sr asc ");

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
                String discount = rs.getString("OldBalance");
                String payable = rs.getString("TotalPayable");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");

                tamt = tamt.add(rs.getBigDecimal("TotalPayable"));
                tcgst = tcgst.add(rs.getBigDecimal("TCGST"));
                tsgst = tsgst.add(rs.getBigDecimal("TSGST"));

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }
            tamt = tamt.setScale(2, RoundingMode.CEILING);
            lblAmt.setText(tamt.toString());

            tcgst = tcgst.setScale(2, RoundingMode.CEILING);
            lblCGST.setText(tcgst.toString());

            tsgst = tsgst.setScale(2, RoundingMode.CEILING);
            lblSGST.setText(tsgst.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    String partyname_temp = "";

    public void showReport_Partywise() {
        tamt = BigDecimal.ZERO;
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

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.OldBalance,s.Discountprct,s.TotalPayable,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr,s.Partyid from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && (p.Partyname LIKE '%" + partyname1 + "%' || p.Contact LIKE '%" + partyname1 + "%' )"
                    + "    order by s.Sr asc ");

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
                String discount = rs.getString("OldBalance");
                String payable = rs.getString("TotalPayable");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");
                tamt = tamt.add(rs.getBigDecimal("TotalPayable"));
                custid = rs.getInt("Partyid");
                partyname_temp = partyname;

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }
            tamt = tamt.setScale(2, RoundingMode.CEILING);
            lblAmt.setText(tamt.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Paymentwise() {

        tamt = BigDecimal.ZERO;
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.OldBalance,s.Discountprct,s.TotalPayable,s.PT,s.PTDetails,"
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
                String discount = rs.getString("OldBalance");
                String payable = rs.getString("TotalPayable");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");
                tamt = tamt.add(rs.getBigDecimal("TotalPayable"));

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }
            tamt = tamt.setScale(2, RoundingMode.CEILING);
            lblAmt.setText(tamt.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Date_Partywsie() {
        tamt = BigDecimal.ZERO;
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
                    + "s.Totalamt,s.TaxableamtT,s.OldBalance,s.Discountprct,s.TotalPayable,s.PT,s.PTDetails,"
                    + "s.Advance,s.Balance,s.TGST,s.Roundup,s.Sr from taborder_total s ,tabparty p "
                    + " where p.Sr=s.Partyid && s.Date1 >= '" + Sdate + "'  && s.Date1 <= '" + Edate + "' "
                    + " &&  (p.Partyname LIKE '%" + pname + "%' || p.Contact LIKE '%" + pname + "%') order by s.Sr asc ");

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
                String discount = rs.getString("OldBalance");
                String payable = rs.getString("TotalPayable");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");
                tamt = tamt.add(rs.getBigDecimal("TotalPayable"));

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }
            tamt = tamt.setScale(2, RoundingMode.CEILING);
            lblAmt.setText(tamt.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    public void showReport_Date_Paymentwsie() {
        tamt = BigDecimal.ZERO;
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
        tabGoodentry.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        int count = 0;
        try {

            ResultSet rs = st.executeQuery("SELECT p.Partyname,s.Date1,s.BillNo,s.TCGST,s.TSGST,s.TIGST,"
                    + "s.Totalamt,s.TaxableamtT,s.OldBalance,s.Discountprct,s.TotalPayable,s.PT,s.PTDetails,"
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
                String discount = rs.getString("OldBalance");
                String payable = rs.getString("TotalPayable");
                String pt = rs.getString("PT");
                String advance = rs.getString("Advance");
                String balance = rs.getString("Balance");
                tamt = tamt.add(rs.getBigDecimal("TotalPayable"));

                df.addRow(new Object[]{count, sr, billno, date, partyname, pt, taxable, cgst, sgst, igst, totalamt,
                    discount, payable, advance, balance});

                tabGoodentry.setModel(df);

            }
            tamt = tamt.setScale(2, RoundingMode.CEILING);
            lblAmt.setText(tamt.toString());

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
            java.util.logging.Logger.getLogger(Sale_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sale_List().setVisible(true);
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
    private javax.swing.JButton btnSave4;
    private javax.swing.JButton btnSave5;
    private javax.swing.JButton btnSave6;
    private javax.swing.JButton btnSettings1;
    private javax.swing.JComboBox cmbPaymenttype;
    private com.toedter.calendar.JDateChooser dcEnd;
    private com.toedter.calendar.JDateChooser dcStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel lblAmt;
    private javax.swing.JLabel lblCGST;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblSGST;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMain1;
    private javax.swing.JTable tabGoodentry;
    private javax.swing.JLabel txtBillNo;
    private javax.swing.JTextField txtItemname;
    private javax.swing.JLabel txtUser;
    // End of variables declaration//GEN-END:variables
}
