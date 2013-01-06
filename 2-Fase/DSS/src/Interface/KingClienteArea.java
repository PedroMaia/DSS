package Interface;

import Business.BuyKing;
import Business.Leilao;
import Business.Produto;
import Business.Utilizador;
import Business.Venda;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro
 */
public final class KingClienteArea extends javax.swing.JInternalFrame {

    
    private Utilizador u;
    private BuyKing sys;
    /**
     * Creates new form KingClienteArea
     */
    public KingClienteArea(Utilizador u, BuyKing sys) throws SQLException{
        initComponents();
        this.u=u;
        this.sys=sys;
        updateListaProdutos();
        updateCompras();
        updateVendas();
        updateLeiloes();
        updateLicitacoes();
    }
    
    public void updateListaProdutos() throws SQLException
    {
        List<Produto> l= u.getProdutos();
        String[] colNames={"Nr","Nome", "Categoria"};
        DefaultTableModel m = new DefaultTableModel(colNames, 0)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };
        jTable3.setModel(m);
        for(Produto p:l){
            m.addRow(new Object[]{p.getId(),p.getNome(), p.getCategoria()});
        }
        m.fireTableDataChanged();
    }
    
    public void updateCompras() throws SQLException
    {
        List<Venda> list = sys.getVendasFromComprador(u);
        DefaultTableModel m = new DefaultTableModel(new String[]{"Nr","Nome","Preço","Estado"}, 0)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };
        jTable2.setModel(m);
        for(Venda v:list){
            m.addRow(new Object[]{v.getId(),v.getProduto().getNome(), v.getPreco(), Venda.ESTADOS[v.getEstado()]});
        }
    }
    
    public void updateVendas() throws SQLException 
    {
        List<Venda> list = sys.getVendasFromVendedor(u);
        DefaultTableModel m = new DefaultTableModel(new String[]{"Nr","Nome","Preço","Estado"}, 0)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };
        jTable4.setModel(m);
        for(Venda v:list){
            m.addRow(new Object[]{v.getId(),v.getProduto().getNome(), v.getPreco(), Venda.ESTADOS[v.getEstado()]});
        }
    }
    
    public void updateLeiloes() throws SQLException{
        List<Leilao> list = sys.getLeilaoFromLeiloador(u);
        DefaultTableModel m = new DefaultTableModel(new String[]{"Nr","Nome","Preço","Estado"}, 0)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };
        jTableMeusLeiloes.setModel(m);
        for(Leilao l:list){
            m.addRow(new Object[]{l.getId(),l.getP().getNome(), l.getUltimaLicitacao(), Leilao.ESTADOS[l.getEstado()]});
        }
    }
    
    public void updateLicitacoes() throws SQLException
    {
        List<Leilao> list = sys.getLeilaoFromLicitador(u);
        DefaultTableModel m = new DefaultTableModel(new String[]{"Nr","Nome","Preço","Estado"}, 0)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };
        jTableMinhasLicitacoes.setModel(m);
        for(Leilao l:list){
            m.addRow(new Object[]{l.getId(),l.getP().getNome(), l.getUltimaLicitacao(), Leilao.ESTADOS[l.getEstadoLicitacao(u)]});
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

        jTabbedPaneBody = new javax.swing.JTabbedPane();
        jPanelMeusProdutos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanelCompras = new javax.swing.JPanel();
        jPanelComprasEspera = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanelVendas = new javax.swing.JPanel();
        jPanelVendasEsperaPagamento = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanelLeiloes = new javax.swing.JPanel();
        jPanelMeusLeilões = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableMeusLeiloes = new javax.swing.JTable();
        jPanelMinhasReq = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableMinhasLicitacoes = new javax.swing.JTable();
        jPanelDefUser = new javax.swing.JPanel();
        jPanelMudarPassword = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordFieldNovaPass1 = new javax.swing.JPasswordField();
        jPasswordFieldNovaPass2 = new javax.swing.JPasswordField();
        jButtonGuardarNovaPass = new javax.swing.JButton();
        JpanelAlterarIdpessoal = new javax.swing.JPanel();
        jLabelMorada = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelConcelho = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButtonGuardarNovaPass1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jTabbedPaneBody.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanelMeusProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(" Lista de Produtos:"));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanelMeusProdutosLayout = new javax.swing.GroupLayout(jPanelMeusProdutos);
        jPanelMeusProdutos.setLayout(jPanelMeusProdutosLayout);
        jPanelMeusProdutosLayout.setHorizontalGroup(
            jPanelMeusProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMeusProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMeusProdutosLayout.setVerticalGroup(
            jPanelMeusProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMeusProdutosLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPaneBody.addTab("<html> <strong> Meus Produtos </strong> </html>", new javax.swing.ImageIcon(getClass().getResource("/Imagens/PackProdutos.png")), jPanelMeusProdutos); // NOI18N

        jPanelCompras.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanelComprasEspera.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanelComprasEsperaLayout = new javax.swing.GroupLayout(jPanelComprasEspera);
        jPanelComprasEspera.setLayout(jPanelComprasEsperaLayout);
        jPanelComprasEsperaLayout.setHorizontalGroup(
            jPanelComprasEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprasEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
        );
        jPanelComprasEsperaLayout.setVerticalGroup(
            jPanelComprasEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprasEsperaLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanelComprasLayout = new javax.swing.GroupLayout(jPanelCompras);
        jPanelCompras.setLayout(jPanelComprasLayout);
        jPanelComprasLayout.setHorizontalGroup(
            jPanelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelComprasEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelComprasLayout.setVerticalGroup(
            jPanelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelComprasLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanelComprasEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneBody.addTab("<html> <strong> Compras</strong> <html> ", new javax.swing.ImageIcon(getClass().getResource("/Imagens/Comprar.png")), jPanelCompras, ""); // NOI18N

        jPanelVendas.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanelVendasEsperaPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanelVendasEsperaPagamentoLayout = new javax.swing.GroupLayout(jPanelVendasEsperaPagamento);
        jPanelVendasEsperaPagamento.setLayout(jPanelVendasEsperaPagamentoLayout);
        jPanelVendasEsperaPagamentoLayout.setHorizontalGroup(
            jPanelVendasEsperaPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVendasEsperaPagamentoLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelVendasEsperaPagamentoLayout.setVerticalGroup(
            jPanelVendasEsperaPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVendasEsperaPagamentoLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanelVendasLayout = new javax.swing.GroupLayout(jPanelVendas);
        jPanelVendas.setLayout(jPanelVendasLayout);
        jPanelVendasLayout.setHorizontalGroup(
            jPanelVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelVendasEsperaPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelVendasLayout.setVerticalGroup(
            jPanelVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelVendasEsperaPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneBody.addTab("<html>\n<strong>\nVendas\n</html>\n</strong>", new javax.swing.ImageIcon(getClass().getResource("/Imagens/Vender.png")), jPanelVendas); // NOI18N

        jPanelMeusLeilões.setBorder(javax.swing.BorderFactory.createTitledBorder("Meus Leilões"));

        jTableMeusLeiloes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTableMeusLeiloes);

        javax.swing.GroupLayout jPanelMeusLeilõesLayout = new javax.swing.GroupLayout(jPanelMeusLeilões);
        jPanelMeusLeilões.setLayout(jPanelMeusLeilõesLayout);
        jPanelMeusLeilõesLayout.setHorizontalGroup(
            jPanelMeusLeilõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMeusLeilõesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMeusLeilõesLayout.setVerticalGroup(
            jPanelMeusLeilõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMeusLeilõesLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelMinhasReq.setBorder(javax.swing.BorderFactory.createTitledBorder("Minhas licitações:"));

        jTableMinhasLicitacoes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jTableMinhasLicitacoes);

        javax.swing.GroupLayout jPanelMinhasReqLayout = new javax.swing.GroupLayout(jPanelMinhasReq);
        jPanelMinhasReq.setLayout(jPanelMinhasReqLayout);
        jPanelMinhasReqLayout.setHorizontalGroup(
            jPanelMinhasReqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMinhasReqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addGap(17, 17, 17))
        );
        jPanelMinhasReqLayout.setVerticalGroup(
            jPanelMinhasReqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMinhasReqLayout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanelLeiloesLayout = new javax.swing.GroupLayout(jPanelLeiloes);
        jPanelLeiloes.setLayout(jPanelLeiloesLayout);
        jPanelLeiloesLayout.setHorizontalGroup(
            jPanelLeiloesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeiloesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelLeiloesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMeusLeilões, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMinhasReq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelLeiloesLayout.setVerticalGroup(
            jPanelLeiloesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeiloesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanelMeusLeilões, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(jPanelMinhasReq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );

        jTabbedPaneBody.addTab("<html>\n<strong>\nLeilões\n</strong>\n</html>", new javax.swing.ImageIcon(getClass().getResource("/Imagens/Leilão.png")), jPanelLeiloes); // NOI18N

        jPanelMudarPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Mudar password"));

        jLabel1.setText("Nova password:");

        jLabel2.setText("Confirma nova password:");

        jButtonGuardarNovaPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/save.png"))); // NOI18N
        jButtonGuardarNovaPass.setText("Guardar");
        jButtonGuardarNovaPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarNovaPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMudarPasswordLayout = new javax.swing.GroupLayout(jPanelMudarPassword);
        jPanelMudarPassword.setLayout(jPanelMudarPasswordLayout);
        jPanelMudarPasswordLayout.setHorizontalGroup(
            jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMudarPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMudarPasswordLayout.createSequentialGroup()
                        .addGroup(jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldNovaPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordFieldNovaPass2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMudarPasswordLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonGuardarNovaPass, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelMudarPasswordLayout.setVerticalGroup(
            jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMudarPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPasswordFieldNovaPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMudarPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordFieldNovaPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardarNovaPass)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        JpanelAlterarIdpessoal.setBorder(javax.swing.BorderFactory.createTitledBorder("Def User"));

        jLabelMorada.setText("Morada* :");

        jLabel6.setText("Localidade* :");

        jLabelConcelho.setText("Concelho* :");

        jLabel8.setText("Distrito* :");

        jLabelEmail.setText("Email* :");

        jLabel11.setText("Nº Tel* :");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jButtonGuardarNovaPass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/save.png"))); // NOI18N
        jButtonGuardarNovaPass1.setText("Guardar");
        jButtonGuardarNovaPass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarNovaPass1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelAlterarIdpessoalLayout = new javax.swing.GroupLayout(JpanelAlterarIdpessoal);
        JpanelAlterarIdpessoal.setLayout(JpanelAlterarIdpessoalLayout);
        JpanelAlterarIdpessoalLayout.setHorizontalGroup(
            JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelAlterarIdpessoalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonGuardarNovaPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                        .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14))
                                    .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                        .addComponent(jLabelMorada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)))
                                .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(jTextField3)))
                            .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                .addComponent(jLabelConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                        .addGap(231, 231, 231)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(87, 87, 87)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanelAlterarIdpessoalLayout.createSequentialGroup()
                                        .addGap(225, 225, 225)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(288, Short.MAX_VALUE))))
        );
        JpanelAlterarIdpessoalLayout.setVerticalGroup(
            JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelAlterarIdpessoalLayout.createSequentialGroup()
                        .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMorada)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpanelAlterarIdpessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEmail)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jButtonGuardarNovaPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelDefUserLayout = new javax.swing.GroupLayout(jPanelDefUser);
        jPanelDefUser.setLayout(jPanelDefUserLayout);
        jPanelDefUserLayout.setHorizontalGroup(
            jPanelDefUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDefUserLayout.createSequentialGroup()
                .addGroup(jPanelDefUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDefUserLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jPanelMudarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDefUserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JpanelAlterarIdpessoal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelDefUserLayout.setVerticalGroup(
            jPanelDefUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDefUserLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanelMudarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(JpanelAlterarIdpessoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPaneBody.addTab("<html><strong>Definições de Utilizador</strong></html>", new javax.swing.ImageIcon(getClass().getResource("/Imagens/DefUser.png")), jPanelDefUser); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jTabbedPaneBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneBody, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarNovaPass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarNovaPass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarNovaPass1ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButtonGuardarNovaPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarNovaPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarNovaPassActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelAlterarIdpessoal;
    private javax.swing.JButton jButtonGuardarNovaPass;
    private javax.swing.JButton jButtonGuardarNovaPass1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelConcelho;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelMorada;
    private javax.swing.JPanel jPanelCompras;
    private javax.swing.JPanel jPanelComprasEspera;
    private javax.swing.JPanel jPanelDefUser;
    private javax.swing.JPanel jPanelLeiloes;
    private javax.swing.JPanel jPanelMeusLeilões;
    private javax.swing.JPanel jPanelMeusProdutos;
    private javax.swing.JPanel jPanelMinhasReq;
    private javax.swing.JPanel jPanelMudarPassword;
    private javax.swing.JPanel jPanelVendas;
    private javax.swing.JPanel jPanelVendasEsperaPagamento;
    private javax.swing.JPasswordField jPasswordFieldNovaPass1;
    private javax.swing.JPasswordField jPasswordFieldNovaPass2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPaneBody;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTableMeusLeiloes;
    private javax.swing.JTable jTableMinhasLicitacoes;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
