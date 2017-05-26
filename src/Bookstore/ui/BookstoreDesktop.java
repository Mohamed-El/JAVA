/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bookstore.ui;

import Bookstore.entity.Boek;
import Bookstore.util.HibernateUtil;
import java.awt.Color;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MELKACHA
 */
public class BookstoreDesktop extends javax.swing.JFrame {

    /**
     * Creates new form BookstoreDesktop
     */
    public BookstoreDesktop() {
        initComponents();
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(57,97,197));
        setVisible(true);
        refreshBookList();
    }

    private static String QUERY_ALL_BOOKS = "from Boek";
    
    private void refreshBookList() {
        executeHQLQuery(QUERY_ALL_BOOKS);
    }
    
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    private void displayResult(List resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("Titel");
        tableHeaders.add("Auteur");
        tableHeaders.add("Jaar");
        tableHeaders.add("Foto URL");

        for(Object o : resultList) {
            Boek boek = (Boek)o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(boek.getTitel());
            oneRow.add(boek.getAuteur());
            oneRow.add(boek.getJaar());
            oneRow.add(boek.getFoto());            
            tableData.add(oneRow);
        }
        resultTable.setModel(new DefaultTableModel(tableData, tableHeaders));
    }
     
    public void addBoek () {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             //start transactie
             trns = session.beginTransaction();
             
             //Maak een nieuwe boek object
             Boek b = new Boek();
             b.setTitel(txtTitel.getText());
             b.setAuteur(txtAuteur.getText());
             b.setIsbn(txtISBN.getText());
             b.setUitgever(txtUitgever.getText());
             b.setJaar(txtJaar.getText());
             b.setFoto(txtURL.getText());
             
             //save dit in de database
             session.save(b);
             
             //Commit
             session.getTransaction().commit();
         } catch (RuntimeException e) {
             if (trns != null) {
                 trns.rollback();
             }
             e.printStackTrace();
         } finally {
             session.flush();
             session.close();
         }
    }
    
    public void deleteBoek () {
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblTitel = new javax.swing.JLabel();
        lblAuteur = new javax.swing.JLabel();
        lblISBN = new javax.swing.JLabel();
        lblUitgever = new javax.swing.JLabel();
        lblJaar = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        txtTitel = new javax.swing.JTextField();
        txtAuteur = new javax.swing.JTextField();
        txtUitgever = new javax.swing.JTextField();
        txtJaar = new javax.swing.JTextField();
        txtISBN = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        txtURL = new javax.swing.JTextField();
        btnVerwijderen = new javax.swing.JButton();
        btnToevoegen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bookstore CVO");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblTitel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTitel.setForeground(new java.awt.Color(255, 255, 255));
        lblTitel.setText("Titel:");

        lblAuteur.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblAuteur.setForeground(new java.awt.Color(255, 255, 255));
        lblAuteur.setText("Auteur:");

        lblISBN.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblISBN.setForeground(new java.awt.Color(255, 255, 255));
        lblISBN.setText("ISBN:");

        lblUitgever.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblUitgever.setForeground(new java.awt.Color(255, 255, 255));
        lblUitgever.setText("Uitgever:");

        lblJaar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblJaar.setForeground(new java.awt.Color(255, 255, 255));
        lblJaar.setText("Jaar:");

        lblFoto.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblFoto.setForeground(new java.awt.Color(255, 255, 255));
        lblFoto.setText("Foto URL:");

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(resultTable);

        btnVerwijderen.setText("Verwijderen");
        btnVerwijderen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerwijderenActionPerformed(evt);
            }
        });

        btnToevoegen.setText("Toevoegen");
        btnToevoegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToevoegenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(349, 349, 349))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitel)
                            .addComponent(lblAuteur)
                            .addComponent(lblUitgever)
                            .addComponent(lblJaar)
                            .addComponent(lblISBN)
                            .addComponent(lblFoto))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAuteur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtTitel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtISBN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtUitgever, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtJaar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txtURL, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnToevoegen, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerwijderen, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitel)
                            .addComponent(txtTitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAuteur)
                            .addComponent(txtAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblISBN)
                            .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUitgever)
                            .addComponent(txtUitgever, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJaar)
                            .addComponent(txtJaar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFoto)
                            .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnToevoegen)
                            .addComponent(btnVerwijderen))
                        .addGap(76, 76, 76))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnToevoegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToevoegenActionPerformed
        addBoek();
        refreshBookList();
    }//GEN-LAST:event_btnToevoegenActionPerformed

    private void btnVerwijderenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerwijderenActionPerformed
        //deleteBoek();
        refreshBookList();
    }//GEN-LAST:event_btnVerwijderenActionPerformed

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
            java.util.logging.Logger.getLogger(BookstoreDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookstoreDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookstoreDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookstoreDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookstoreDesktop().setVisible(true);
            }
        });
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnToevoegen;
    private javax.swing.JButton btnVerwijderen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAuteur;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblJaar;
    private javax.swing.JLabel lblTitel;
    private javax.swing.JLabel lblUitgever;
    private javax.swing.JTable resultTable;
    private javax.swing.JTextField txtAuteur;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtJaar;
    private javax.swing.JTextField txtTitel;
    private javax.swing.JTextField txtURL;
    private javax.swing.JTextField txtUitgever;
    // End of variables declaration//GEN-END:variables
}
