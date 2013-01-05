/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.BuyKing;
import Business.Utilizador;
import java.awt.Dimension;
import java.awt.Menu;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 *
 * @author Pedro
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        this.reg=null;
        initComponents();
       
        
        try {
            CriaJanelaBody();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyVetoException ex)
        {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPanel = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonLogIn = new javax.swing.JButton();
        jButtonRegista = new javax.swing.JButton();
        jButtonAjudaPanel = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemNovaJanela = new javax.swing.JMenuItem();
        jMenuItemClient = new javax.swing.JMenuItem();
        jMenuItemLogIn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/LogInBig.png"))); // NOI18N
        jButtonLogIn.setFocusTraversalPolicyProvider(true);
        jButtonLogIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonLogIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogInActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonLogIn);
        this.jButtonLogIn.setToolTipText("Login");

        jButtonRegista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PenGreenicon.png"))); // NOI18N
        jButtonRegista.setToolTipText("");
        jButtonRegista.setFocusTraversalPolicyProvider(true);
        jButtonRegista.setFocusable(false);
        jButtonRegista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRegista.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonRegista);
        this.jButtonRegista.setToolTipText("Registar");

        jButtonAjudaPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Help.png"))); // NOI18N
        jButtonAjudaPanel.setFocusTraversalPolicyProvider(true);
        jButtonAjudaPanel.setFocusable(false);
        jButtonAjudaPanel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAjudaPanel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonAjudaPanel);
        this.jButtonAjudaPanel.setToolTipText("Ajuda Cliente");

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItemNovaJanela.setText("Nova Janela");
        jMenuItemNovaJanela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovaJanelaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemNovaJanela);

        jMenuItemClient.setText("Regista");
        jMenuItemClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemClient);

        jMenuItemLogIn.setText("LogIn");
        jMenuItemLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogInActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemLogIn);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDesktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    //Cria janela  principal no desktop
    private void CriaJanelaBody() throws InterruptedException, PropertyVetoException
    {
       
       if(this.janelaBody==null) 
           this.janelaBody=new PrincipalBody(this.sys, this);
       
       this.jDesktopPanel.add(this.janelaBody);
      //maximiza a janela!
       this.janelaBody.setMaximum(true);
       
       this.janelaBody.setVisible(true);
       
      
  try {
          this.janelaBody.setSelected(true);
      }catch (PropertyVetoException ex)
      {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
       
    }

/**
 * Cria a janela de LogIn.
 * @throws InterruptedException 
 */    
    private void CriaJanelaLogIn() throws InterruptedException
    {
       
       if(this.loginJanela==null) 
        this.loginJanela=new LogIn(sys, this);
       
       this.jDesktopPanel.add(this.loginJanela);
       this.loginJanela.setVisible(true);
      
  try {
          this.loginJanela.setSelected(true);
      }catch (PropertyVetoException ex)
      {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
       
    }
    
    
    private void CriaJanelaRegisto() throws InterruptedException {

        this.registoJanela = new Registo(sys);

        this.jDesktopPanel.add(this.registoJanela);
        this.registoJanela.setVisible(true);

        try {
            this.registoJanela.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
      
    
    void setUser(Utilizador get) {
        reg = get;
    }
    

    
    private void jButtonLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogInActionPerformed
                 try {
            CriaJanelaLogIn();
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLogInActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

   }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItemLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogInActionPerformed
        try {
            CriaJanelaLogIn();

        } catch (InterruptedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemLogInActionPerformed

    private void jMenuItemClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientActionPerformed
        try {
            CriaJanelaRegisto();

        } catch (InterruptedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemClientActionPerformed

    private void jMenuItemNovaJanelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaJanelaActionPerformed
        try {
            try {
                CriaJanelaBody();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemNovaJanelaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjudaPanel;
    private javax.swing.JButton jButtonLogIn;
    private javax.swing.JButton jButtonRegista;
    private javax.swing.JDesktopPane jDesktopPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemClient;
    private javax.swing.JMenuItem jMenuItemLogIn;
    private javax.swing.JMenuItem jMenuItemNovaJanela;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    PrincipalBody janelaBody;
    private LogIn loginJanela;
    private Registo registoJanela;
    private BuyKing sys=new BuyKing();
    private Utilizador reg;
    private KingClienteArea kingArea;

    public Utilizador getReg() {
        return reg;
    }
    
    
    
    
}
