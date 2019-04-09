/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_billing.v3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ZS
 */
public class Print_Option_Sale extends javax.swing.JFrame {

    Statement st;
    Connection con;
    String shopname, billno;

    public Print_Option_Sale() {
        initComponents();
        getDatabase_Connections();
    }

    public Print_Option_Sale(String Shopname, String BillNo) {
        initComponents();
        getDatabase_Connections();
        shopname = Shopname;
        billno = BillNo;
        lblBillNo.setText(billno);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblBillNo = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnPrint1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Bill No. :");

        lblBillNo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnPrint.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnPrint.setText("Print Bill [ Half Size ]");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnPrint1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnPrint1.setText("Print Bill [ Full Size ]");
        btnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnPrint1)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Map<String, Object> param = new HashMap<String, Object>();

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

        Collection ls = null;
        generateReport("Xc_Emp_Sales", ls, param);

    }//GEN-LAST:event_btnPrintActionPerformed

    public void generateReport(String name, Collection data, Map param) {

        try {

            JasperDesign jd = JRXmlLoader.load("Sale_Bill_Half.jrxml");
            String sql = "SELECT  o.ItemName,o.Date1,o.BillNo,o.Qty,o.Price,o.Amount,o.Taxableamt,o.CGST,"
                    + "o.SGST,o.IGST,o.CGSTP,o.SGSTP,o.IGSTP,ot.TCGST,ot.TSGST,ot.TIGST,ot.Totalamt,ot.TaxableamtT"
                    + ",ot.Discountprct,ot.Discountamt,ot.Payableamt,ot.PT,ot.Advance,ot.Balance,ot.TGST,ot.Roundup,"
                    + "ot.PTDetails,ot.OldBalance,ot.TotalPayable ,u.Unit,t.Taxname,p.Partyname,p.Contact,p.Address,p.State,p.Code,p.GSTIN,ot.Adv_txt"
                    + " from tabtax t,tabunits u,taborder o,taborder_total ot,tabparty p "
                    + " where  u.Sr=o.Unitid && t.Sr=o.Tax && p.Sr=o.Partyid && o.BillNo=ot.BillNo"
                    + " && ot.BillNo='" + lblBillNo.getText() + "' ";

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

    private void btnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint1ActionPerformed
        Map<String, Object> param = new HashMap<String, Object>();

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

        Collection ls = null;
        generateReport_Full("Xc_Emp_Sales", ls, param);

    }//GEN-LAST:event_btnPrint1ActionPerformed

    public void generateReport_Full(String name, Collection data, Map param) {

        try {

            JasperDesign jd = JRXmlLoader.load("Sale_Bill_Full.jrxml");
           String sql = "SELECT  o.ItemName,o.Date1,o.BillNo,o.Qty,o.Price,o.Amount,o.Taxableamt,o.CGST,"
                    + "o.SGST,o.IGST,o.CGSTP,o.SGSTP,o.IGSTP,ot.TCGST,ot.TSGST,ot.TIGST,ot.Totalamt,ot.TaxableamtT"
                    + ",ot.Discountprct,ot.Discountamt,ot.Payableamt,ot.PT,ot.Advance,ot.Balance,ot.TGST,ot.Roundup,"
                    + "ot.PTDetails,ot.OldBalance,ot.TotalPayable ,u.Unit,t.Taxname,p.Partyname,p.Contact,p.Address,p.State,p.Code,p.GSTIN,ot.Adv_txt"
                    + " from tabtax t,tabunits u,taborder o,taborder_total ot,tabparty p "
                    + " where  u.Sr=o.Unitid && t.Sr=o.Tax && p.Sr=o.Partyid && o.BillNo=ot.BillNo"
                    + " && ot.BillNo='" + lblBillNo.getText() + "' ";

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Print_Option_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Print_Option_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Print_Option_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Print_Option_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Print_Option_Sale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBillNo;
    // End of variables declaration//GEN-END:variables
}
