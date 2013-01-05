package Business;

import Data.LeiloesDAO;
import Data.ProdutosDAO;
import Data.SuspeitasDAO;
import Data.TrocasDAO;
import Data.UserDAO;
import Data.VendasDAO;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyKing {

    private UserDAO utilizadores;
    private VendasDAO vendas;
    private LeiloesDAO leiloes;
    private TrocasDAO trocas;
    private ProdutosDAO produtos;

    public BuyKing()
    {
        utilizadores=new UserDAO();
        vendas=new VendasDAO();
        leiloes=new LeiloesDAO();
        trocas=new TrocasDAO();
        produtos=new ProdutosDAO();
    }
    
    public List<Venda> getSugestoesVendas(Utilizador u) throws SQLException
    {
        List<Venda> disp = vendas.getVendasAbertas();
        List<Produto> fav=u.getWishList();
        List<Venda> res = new ArrayList<Venda>();
        for(Venda v:disp)
        {
            if(v.getProduto().existeSemelhante(fav))
                res.add(v);
                
        }
        return res;
    }
    
    public List<Leilao> getSugestoesLeilao(Utilizador u) throws SQLException
    {
        List<Leilao> disp = leiloes.getLeiloesActivos();
        List<Produto> fav=u.getWishList();
        List<Leilao> res = new ArrayList<Leilao>();
        for(Leilao l:disp)
        {
            if(l.getP().existeSemelhante(fav))
                res.add(l);
                
        }
        return res;
    }
    
    /*
    public List<Troca> getSugestoesTroca(Utilizador u) throws SQLException
    {
        List<Venda> disp = vendas.getVendasAbertas();
        List<Produto> fav=u.getWishList();
        List<Troca> res=new ArrayList<Troca>();
        for(Venda v:disp)
        {
            if(v.getProduto().existeSemelhante(fav))
            {
                for(Produto p:u.getProdutos())
                    if(!(leiloes.emLeilao(p)||vendas.aVenda(p))&&p.existeSemelhante(v.getVendedor().getWishList()))
                    {
                        Troca t = new Troca(u, v.getVendedor(), v.getProduto(), p, new GregorianCalendar(), null, null, null, TrocasDAO.getNewId());
                        res.add(t);
                    }
            }
                
        }
        
    }*/
    
    public List<Venda> pesquisaVendasSimples(String aPchave, String aCat) throws SQLException {
        return pesquisaVendasAvançada(aPchave, aCat, 0, Float.POSITIVE_INFINITY);
    }

    public List<Venda> pesquisaVendasAvançada(String aPchave, String aCat, float minP, float maxP) throws SQLException {
        //throw new UnsupportedOperationException();
        List<Venda> l = vendas.getVendasAbertas();
        List<Venda> res = new ArrayList<Venda>();
        for (Venda v : l) {
            if ((v.getPreco() > minP) && (v.getPreco() < maxP)
                    && ((v.getProduto().getNome().matches("(?i:.*"+aPchave+".*)")) || v.getProduto().getDescricao().matches("(?i:.*"+aPchave+".*)"))) {
                if(aCat.equals("<html><strong>Categoria</strong></html>")||aCat.equals(v.getProduto().getCategoria()))
                    res.add(v);
            }
        }
        return res;
    }

    public boolean login(String aUsername, String aPassword) throws SQLException {
        //throw new UnsupportedOperationException();
        Utilizador u = utilizadores.get(aUsername);
        String md5 = BuyKing.md5crypt(aPassword);
        return ((u != null) && (u.getPassmd5().equals(md5)));
    }

    public static String md5crypt(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BuyKing.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] digest = md.digest(s.getBytes());
        BigInteger big = new BigInteger(1, digest);
        String md5 = big.toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }

    public boolean registaCompra(Utilizador u, Venda v) throws SQLException {
        v.setComprador(u);
        GregorianCalendar d = new GregorianCalendar();
        d.add(GregorianCalendar.DAY_OF_MONTH, 7); //7 dias para conclusao da venda
        v.setDataLimiteVenda(d);
        boolean res = vendas.update(v);
        return res;
    }

    public boolean vender(Utilizador u, Produto p, float preco) throws SQLException {
        boolean res = false;
        List<Produto> pr = produtos.getFromUser(u);
        if (pr.contains(p) && !vendas.aVenda(p) && !leiloes.emLeilao(p)) {
            Venda v = new Venda(VendasDAO.getNewId(), preco, new GregorianCalendar(), null, null, null, p, u, null);
            res = vendas.add(v);
        }
        return res;
    }

    public boolean licitar(Utilizador u, Leilao l, float v) throws LeilaoFechadoException, BaixaLicitacaoException, SQLException {
        boolean res = false;
        if (!l.fechado()) {
            Licitacao lit = new Licitacao(u, v, new GregorianCalendar());
            res = l.registaLicitacao(lit);
            if (res && (v >= l.getTecto())) {
                GregorianCalendar d = new GregorianCalendar();
                d.add(GregorianCalendar.DAY_OF_MONTH, 7);
                l.setDataLimiteLeilao(d);
                leiloes.update(l);
            }
        }
        return res;
    }

    public boolean leiloar(Utilizador u, Produto p, float base, float tecto) throws SQLException {
        boolean res = false;
        List<Produto> pr = produtos.getFromUser(u);
        if (pr.contains(p) && !vendas.aVenda(p) && !leiloes.emLeilao(p)) {
            GregorianCalendar hoje = new GregorianCalendar();
            GregorianCalendar fecho = (GregorianCalendar) hoje.clone();
            fecho.add(GregorianCalendar.DAY_OF_MONTH, 14);
            GregorianCalendar limite = (GregorianCalendar) fecho.clone();
            limite.add(GregorianCalendar.DAY_OF_MONTH, 7);
            Leilao l = new Leilao(LeiloesDAO.getNewId(), u, p, hoje, fecho, limite, null, null, base, tecto);
            res = leiloes.add(l);
        }
        return res;
    }

    public boolean registar(String nome, String pass, String mail, String localidade, GregorianCalendar dataNascimento, BufferedImage i) throws SQLException {
        GregorianCalendar dataRegisto = new GregorianCalendar();
        Utilizador u = new Utilizador(nome, BuyKing.md5crypt(pass), mail, localidade, dataNascimento, dataRegisto, i);
        return utilizadores.add(u);
    }

    public List<Leilao> pesquisaAvançadaLeilao(String aPchave, String cat, float pmin, float pmax) throws SQLException {
        List<Leilao> list = leiloes.getLeiloesActivos();
        List<Leilao> res = new ArrayList<Leilao>();
        for (Leilao l : list) {
            if ((l.getP().getNome().matches("(?i:.*"+aPchave+".*)") || l.getP().getDescricao().matches("(?i:.*"+aPchave+".*)"))
                    && pmin <= l.getUltimaLicitacao() && pmax >= l.getTecto()) {
                if(cat.equals("<html><strong>Categoria</strong></html>")||cat.equals(l.getP().getCategoria()))
                    res.add(l);
            }
        }
        return res;
    }

    public List<Leilao> pesquisaSimplesLeilao(String nome, String cat) throws SQLException {
        return pesquisaAvançadaLeilao(nome, cat, 0, Float.MAX_VALUE);
    }

    public boolean classificar(Utilizador classificador, Utilizador classificado, int valor) throws SQLException {
        Classificacao c = new Classificacao(classificador, new GregorianCalendar(), valor);
        return classificado.addClassificacao(c);
    }

    public boolean reportarSuspeita(Utilizador u, Produto p, String just) throws SQLException {
        Suspeita s = new Suspeita(u, just);
        return p.addSuspeita(s);
    }

    public boolean adicionarWishlist(Utilizador u, Produto p) throws SQLException {
        return u.addWishList(p);
    }
    
    public Produto addProduto(Utilizador u, String nomeP, BufferedImage imgP, String descP, String cat) throws SQLException
    {
        Produto p = new Produto(ProdutosDAO.getNewId(), nomeP, imgP, descP, cat);
        if(produtos.add(p, u))
            return p;
        else return null;
    }

    public Utilizador getUtilizador(String text) throws SQLException {
        return utilizadores.get(text);
    }
    
    public boolean existeUtilizador(String username) throws SQLException
    {
        return utilizadores.existe(username);
    }
    
    public boolean existeUserMail(String mail) throws SQLException
    {
        return utilizadores.existeMail(mail);
    }
    
    public List<Venda> getVendasFromComprador(Utilizador u) throws SQLException
    {
        return vendas.getVendasComprador(u);
    }

    public List<Venda> getVendasFromVendedor(Utilizador u) throws SQLException {
        return vendas.getVendasVendedor(u);
    }

    public List<Leilao> getLeilaoFromLeiloador(Utilizador u) throws SQLException {
        return leiloes.getLeiloesLeiloador(u);
    }
    
    public List<Leilao> getLeilaoFromLicitador(Utilizador u) throws SQLException
    {
        return leiloes.getLeiloesLicitador(u);
    }
}