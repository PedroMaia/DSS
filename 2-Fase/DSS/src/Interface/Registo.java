/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.BuyKing;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro
 */
public class Registo extends javax.swing.JInternalFrame {


    /**
     * Creates new form Registo
     */
    public Registo(BuyKing sys) {
        initComponents();
        this.sys=sys;
        //para juntar! no inico
        this.jTextFieldDay.selectAll();
        this.imgCliente=null;
        estadoInitLabels();
    }
    /*Abre janela de escolher Imagem de produto:Limpar a imagem antrior!*/
    public void abreFileChooser() {
        JFileChooser file = new JFileChooser();
        FileFilter ff=  new ExtensionFileFilter("JPG and JPEG", new String[] { "JPG", "JPEG" });
        file.setFileFilter(ff);
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);

        if (i == 1) {
            //JOptionPane.showMessageDialog(null, "Procurar Localidade Erro:\n Não existe Localidade", "Erro", JOptionPane.ERROR_MESSAGE);

        } else {
            
            
            File arquivo = file.getSelectedFile();
            try {
                    this.imgCliente=ImageIO.read(arquivo);
                    loadImgProd();
                } 
                catch (IOException ex)
                {
                    //Erro carregar Imagem!
                     Logger.getLogger(PrincipalBody.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
    }
    
    public void estadoInitLabels(){
        Boolean bol = false;
        this.jLabelDisponivel.setVisible(bol);
        this.jLabelDisponivel1.setVisible(bol);
        this.jLabelPasswordErro.setVisible(bol);
        this.jLabelEmailErro.setVisible(bol);
    }
    
    public void dispUser() throws SQLException {
        String dUser = this.jTextFieldUserName.getText();
        if (!this.jTextFieldUserName.getText().equals("")) {
            if (sys.existeUtilizador(dUser)) {
                this.jLabelDisponivel1.setVisible(true);
                this.jLabelDisponivel.setVisible(false);
            } else {
                this.jLabelDisponivel.setVisible(true);
                this.jLabelDisponivel1.setVisible(false);
            }
        }
    }
    
    public void dispPass(){
        String comp =new String (this.jPasswordField1.getPassword());
        String comp2 = new String(this.jPasswordConfirme.getPassword());
        
        if(comp.equals(comp2))
            this.jLabelPasswordErro.setVisible(false);
        else 
            this.jLabelPasswordErro.setVisible(true);
    }
    
    public void dispMail() throws SQLException{
        String dMail = this.jTextFieldEmail.getText();
        if(sys.existeUserMail(dMail) && this.jTextFieldEmail.getText().equals(""))
            this.jLabelEmailErro.setVisible(true);
        else
            this.jLabelEmailErro.setVisible(false);
    }
    
    
    
    
   //*Fata coisas!! inserir nova imagem cada vez que ele carrega no bt!     
    public void loadImgProd()
    {
       int lar= this.jScrollPane2.getWidth();
        int alt=this.jScrollPane2.getHeight();     
        
        Image resizedImage = this.imgCliente.getScaledInstance(lar-10,alt-10, Image.SCALE_DEFAULT);
        
        ImageIcon icon = new ImageIcon(resizedImage);
        this.jLabelImagemCliente.setIcon(icon);
    
    }
    
    public void regista(){
        if(new String(jPasswordField1.getPassword()).equals(new String(jPasswordConfirme.getPassword()))&&jCheckBox1.isSelected())
            try
            {   // verifica se a string dá para converter para inteiro.
                int dia=Integer.parseInt(this.jTextFieldDay.getText());
                int mes=this.jMonthChooser1.getMonth();
                int ano=this.jYearChooser1.getYear();
                System.out.println(""+dia+ano+mes);
                GregorianCalendar dataNacimento=new GregorianCalendar(ano,mes,dia);
                
                if(sys.registar(jTextFieldUserName.getText(), new String(jPasswordField1.getPassword()), jTextFieldEmail.getText()
                    , jTextFieldMorada.getText(), dataNacimento , this.imgCliente))
                {
                    JOptionPane.showInternalMessageDialog(this,"Já pode efetuar Login", "Registo feito Com Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch(Exception e){JOptionPane.showInternalMessageDialog(this,e,"Não foi possivel registar",JOptionPane.ERROR_MESSAGE);
                                   }
        else
            JOptionPane.showInternalMessageDialog(this,"Dados Incorretos","Não foi possivel registar",JOptionPane.ERROR_MESSAGE);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jPanelInforUser = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabelImagemCliente = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldMorada = new javax.swing.JTextField();
        jTextFieldUserName = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelDisponivel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelPasswordErro = new javax.swing.JLabel();
        jTextFieldDay = new javax.swing.JTextField();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jBcimagem = new javax.swing.JButton();
        jLabelDisponivel1 = new javax.swing.JLabel();
        jPasswordConfirme = new javax.swing.JPasswordField();
        jLabelEmailErro = new javax.swing.JLabel();

        setClosable(true);

        jCheckBox1.setText("Aceito os Termos");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PenGreenicon.png"))); // NOI18N
        jButton1.setText("Registar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanelInforUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações User"));

        jScrollPane2.setViewportView(jLabelImagemCliente);

        jTextFieldUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserNameActionPerformed(evt);
            }
        });
        jTextFieldUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldUserNameFocusLost(evt);
            }
        });

        jLabel12.setText("Username* :");

        jLabelPassword.setText("Password* :");

        jLabelDisponivel.setText("<html><font color= \"Green\">Disponivel</font></html>");

        jLabel14.setText("Confirmar password* :");

        jLabel3.setText("Morada* :");

        jLabel9.setText("Email* :");

        jLabel1.setText("DataNascimento:");

        jLabelPasswordErro.setText("<html><font color= \"Red\">Password não coincidem.</font></html>");

        jTextFieldDay.setText("Dia");
        jTextFieldDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDayActionPerformed(evt);
            }
        });
        jTextFieldDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDayFocusGained(evt);
            }
        });

        jBcimagem.setText("Carregar Imagem");
        jBcimagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcimagemActionPerformed(evt);
            }
        });

        jLabelDisponivel1.setText("<html><font color= \"Red\">User já existe, insira outro user name.</font></html>");

        jPasswordConfirme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordConfirmeActionPerformed(evt);
            }
        });
        jPasswordConfirme.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordConfirmeFocusLost(evt);
            }
        });

        jLabelEmailErro.setText("<html><font color= \"Red\">Email já se encontra registado!</font></html>");

        javax.swing.GroupLayout jPanelInforUserLayout = new javax.swing.GroupLayout(jPanelInforUser);
        jPanelInforUser.setLayout(jPanelInforUserLayout);
        jPanelInforUserLayout.setHorizontalGroup(
            jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInforUserLayout.createSequentialGroup()
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabelPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addComponent(jTextFieldDay, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(381, 381, 381))
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldMorada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInforUserLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(jPanelInforUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPasswordConfirme, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPasswordErro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(14, 14, 14)
                        .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDisponivel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInforUserLayout.createSequentialGroup()
                                .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabelDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInforUserLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabelEmailErro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBcimagem, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanelInforUserLayout.setVerticalGroup(
            jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInforUserLayout.createSequentialGroup()
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInforUserLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12))
                            .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDisponivel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextFieldDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelEmailErro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInforUserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBcimagem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldMorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabelPasswordErro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordConfirme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(36, Short.MAX_VALUE)
                    .addComponent(jPanelInforUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(457, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(38, 38, 38))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jPanelInforUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        regista();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBcimagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcimagemActionPerformed
        abreFileChooser();
    }//GEN-LAST:event_jBcimagemActionPerformed

    private void jTextFieldDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDayActionPerformed
        //seleciona o texto
        this.jTextFieldDay.setText("");
    }//GEN-LAST:event_jTextFieldDayActionPerformed

    private void jTextFieldUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserNameActionPerformed
        try {
            dispUser();
        } catch (SQLException ex) {
            Logger.getLogger(Registo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldUserNameActionPerformed

    private void jPasswordConfirmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordConfirmeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordConfirmeActionPerformed

    private void jTextFieldUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUserNameFocusLost
        try {
            dispUser();
        } catch (SQLException ex) {
            Logger.getLogger(Registo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldUserNameFocusLost

    private void jPasswordConfirmeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordConfirmeFocusLost
        dispPass();
    }//GEN-LAST:event_jPasswordConfirmeFocusLost

    private void jTextFieldDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDayFocusGained
       this.jTextFieldDay.setText("");   // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDayFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcimagem;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDisponivel;
    private javax.swing.JLabel jLabelDisponivel1;
    private javax.swing.JLabel jLabelEmailErro;
    private javax.swing.JLabel jLabelImagemCliente;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPasswordErro;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanelInforUser;
    private javax.swing.JPasswordField jPasswordConfirme;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldDay;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldMorada;
    private javax.swing.JTextField jTextFieldUserName;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
    private BufferedImage imgCliente;
    private BuyKing sys;
    
}
