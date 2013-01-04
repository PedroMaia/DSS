/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.BuyKing;
import Business.Utilizador;
import java.awt.Menu;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
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
        jButtonNovaJanela = new javax.swing.JButton();
        jButtonKingArea = new javax.swing.JButton();
        jButtonLogOut = new javax.swing.JButton();
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

        jButtonNovaJanela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/newWindows.png"))); // NOI18N
        jButtonNovaJanela.setFocusable(false);
        jButtonNovaJanela.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNovaJanela.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNovaJanela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaJanelaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonNovaJanela);

        jButtonKingArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/CrownSmall.png"))); // NOI18N
        jButtonKingArea.setFocusable(false);
        jButtonKingArea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonKingArea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonKingArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKingAreaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonKingArea);

        jButtonLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/SmallLoginin.png"))); // NOI18N
        jButtonLogOut.setFocusable(false);
        jButtonLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonLogOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonLogOut);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDesktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jDesktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Cria janela  principal no desktop
    private void CriaJanelaBody() throws InterruptedException
    {
       
       if(this.janelaBody==null) 
           this.janelaBody=new PrincipalBody(this.sys);
       
       this.jDesktopPanel.add(this.janelaBody);
       this.janelaBody.setVisible(true);
      
  try {
          this.janelaBody.setSelected(true);
      }catch (PropertyVetoException ex)
      {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
       
    }

   
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

        if (this.registoJanela == null) {
            this.registoJanela = new Registo(sys);
        }

        this.jDesktopPanel.add(this.registoJanela);
        this.registoJanela.setVisible(true);

        try {
            this.registoJanela.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
      
    
    void setUser(Utilizador get) {
        reg=get;
    }
        
        
    private void jMenuItemNovaJanelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaJanelaActionPerformed
    try {
            CriaJanelaBody();
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
 // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemNovaJanelaActionPerformed

    private void jMenuItemClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientActionPerformed
   try {
            CriaJanelaRegisto();
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemClientActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItemLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogInActionPerformed
          try {
            CriaJanelaLogIn();
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemLogInActionPerformed

    private void jButtonNovaJanelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaJanelaActionPerformed
     try {
            CriaJanelaBody();
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }//GEN-LAST:event_jButtonNovaJanelaActionPerformed
/**a alterar*/
    private void jButtonKingAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKingAreaActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jButtonKingAreaActionPerformed

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
    private javax.swing.JButton jButtonKingArea;
    private javax.swing.JButton jButtonLogOut;
    private javax.swing.JButton jButtonNovaJanela;
    private javax.swing.JDesktopPane jDesktopPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemClient;
    private javax.swing.JMenuItem jMenuItemLogIn;
    private javax.swing.JMenuItem jMenuItemNovaJanela;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    private PrincipalBody janelaBody;
    private LogIn loginJanela;
    private Registo registoJanela;
    private BuyKing sys=new BuyKing();
    private Utilizador reg;
    private KingClienteArea kingArea;

}
