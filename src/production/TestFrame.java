/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Владелец
 */
public final class TestFrame extends javax.swing.JFrame {

    private Connection dbConnection;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (dbConnection != null) {
            return dbConnection;
        } else {
            try {
                dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/production-", "root", "08071994");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
            return dbConnection;
        }
    }

    public void tableShow() {
        dbConnection = getConnection();

        DefaultTableModel dtm = null;

        try {

            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select task.id №, product_list.name Бутылка, color_list.name Цвет, pack_list.name Упаковка, employee_list.name Упаковщик"
                    + " from task, product_list, color_list, pack_list, employee_list"
                    + " where task.product = product_list.id and task.color = color_list.id and task.pack = pack_list.id and task.employee = employee_list.id");
            dtm = new DefaultTableModel();

            //if (pstmt.execute()) {
            //rs = pstmt.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int col = 1; col <= rsmd.getColumnCount(); col++) {
                dtm.addColumn(rsmd.getColumnLabel(col));
            }

            while (rs.next()) {

                Vector<String> row = new Vector<String>();

                for (int col = 1; col <= rsmd.getColumnCount(); col++) {

                    int type = rsmd.getColumnType(col);

                    switch (type) {

                        case Types.INTEGER:
                            row.add(new Integer(rs.getInt(col)).toString());
                            break;

                        case Types.VARCHAR:
                            row.add(rs.getString(col));
                            break;
                        default:

                            throw new Exception("Неподдерживаемый тип");
                    }

                }

                dtm.addRow(row);
            }
            //  }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        jTable1.setModel(dtm);
    }

    public void comboFilling(String sql, MyComboBox box) {
        dbConnection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = dbConnection.prepareStatement(sql);
            if (pstmt.execute()) {
                rs = pstmt.getResultSet();

                while (rs.next()) {
                    box.addElement(rs.getInt("id"), rs.getString("name"));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new form TestFrame
     */
    public TestFrame() {
                   
        initComponents();
        String sql_command1 = "SELECT id, name from product_list where type = 1";
        comboFilling(sql_command1, myComboBox1);
        String sql_command2 = "SELECT id, name from color_list";
        comboFilling(sql_command2, myComboBox2);
        String sql_command5 = "SELECT id, name from pack_list";
        comboFilling(sql_command5, myComboBox5);
        String sql_command6 = "SELECT id, name from employee_list where post = 1";
        comboFilling(sql_command6, myComboBox6);
        String sql_command7 = "SELECT id, name from employee_list where post = 2";
        comboFilling(sql_command7, myComboBox7);
        String sql_command8 = "SELECT id, name from employee_list where post = 3";
        comboFilling(sql_command8, myComboBox8);
        tableShow();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        myComboBox1 = new production.MyComboBox();
        myComboBox2 = new production.MyComboBox();
        myComboBox3 = new production.MyComboBox();
        myComboBox4 = new production.MyComboBox();
        myComboBox5 = new production.MyComboBox();
        myComboBox6 = new production.MyComboBox();
        myComboBox7 = new production.MyComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        myComboBox8 = new production.MyComboBox();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Список");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Бутылка");

        jLabel2.setText("Цвет");

        jLabel3.setText("Крышка");

        jLabel4.setText("Цвет");

        jLabel5.setText("Упаковка");

        jLabel6.setText("Упаковщик");

        jButton2.setText("Записать");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ((JLabel) myComboBox1.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor1 = (JTextField) myComboBox1.getEditor().getEditorComponent();
        editor1.setHorizontalAlignment(JTextField.CENTER);

        ((JLabel) myComboBox2.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor2 = (JTextField) myComboBox2.getEditor().getEditorComponent();
        editor2.setHorizontalAlignment(JTextField.CENTER);

        ((JLabel) myComboBox3.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor3 = (JTextField) myComboBox3.getEditor().getEditorComponent();
        editor3.setHorizontalAlignment(JTextField.CENTER);

        ((JLabel) myComboBox4.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor4 = (JTextField) myComboBox4.getEditor().getEditorComponent();
        editor4.setHorizontalAlignment(JTextField.CENTER);

        ((JLabel) myComboBox5.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor5 = (JTextField) myComboBox5.getEditor().getEditorComponent();
        editor5.setHorizontalAlignment(JTextField.CENTER);

        ((JLabel) myComboBox6.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor6 = (JTextField) myComboBox6.getEditor().getEditorComponent();
        editor6.setHorizontalAlignment(JTextField.CENTER);

        ((JLabel) myComboBox7.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor7 = (JTextField) myComboBox7.getEditor().getEditorComponent();
        editor7.setHorizontalAlignment(JTextField.CENTER);

        jLabel7.setText("Контролер");

        jLabel8.setText("Мастер смены");

        ((JLabel) myComboBox8.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTextField editor8 = (JTextField) myComboBox8.getEditor().getEditorComponent();
        editor8.setHorizontalAlignment(JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(myComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(myComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(46, 46, 46)
                                .addComponent(jButton1)
                                .addGap(8, 8, 8)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tableShow();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dbConnection = getConnection();
        Statement statement = null;
        int product_id = (int) myComboBox1.getSelectedItemId();
        int color_id = (int) myComboBox2.getSelectedItemId();
        int pack_id = (int) myComboBox5.getSelectedItemId();
        int employee_id = (int) myComboBox6.getSelectedItemId();
        String insertTableSQL = "INSERT INTO task (product,color,pack,employee) values (" + product_id + "," + color_id + "," + pack_id + "," + employee_id + ")";
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        tableShow();

    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new TestFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private production.MyComboBox myComboBox1;
    private production.MyComboBox myComboBox2;
    private production.MyComboBox myComboBox3;
    private production.MyComboBox myComboBox4;
    private production.MyComboBox myComboBox5;
    private production.MyComboBox myComboBox6;
    private production.MyComboBox myComboBox7;
    private production.MyComboBox myComboBox8;
    // End of variables declaration//GEN-END:variables

}
