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

    private UserDAO _utilizadores;
    private VendasDAO _vendas;
    private LeiloesDAO _leiloes;
    private SuspeitasDAO _suspeitas_;
    private TrocasDAO _trocas_;
    private ProdutosDAO _produtos;

    public List<Venda> pesquisaVendasSimples(String aPchave, String aCat) throws SQLException {
        return pesquisaVendasAvançada(aPchave, aCat, 0, Float.MAX_VALUE);
    }

    public List<Venda> pesquisaVendasAvançada(String aPchave, String aCat, float minP, float maxP) throws SQLException {
        //throw new UnsupportedOperationException();
        List<Venda> l = _vendas.getVendasAbertas();
        List<Venda> res = new ArrayList<Venda>();
        for (Venda v : l) {
            if ((v.getPreco() > minP) && (v.getPreco() < maxP)
                    && (aPchave.matches(v.getProduto().getNome()) || aPchave.matches(v.getProduto().getDescricao()))
                    && (aCat.equals(v.getProduto().getCategoria()))) {
                res.add(v);
            }
        }
        return res;
    }

    public boolean login(String aUsername, String aPassword) throws SQLException {
        //throw new UnsupportedOperationException();
        Utilizador u = _utilizadores.get(aUsername);
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
        boolean res = _vendas.update(v);
        return res;
    }

    public boolean vender(Utilizador u, Produto p, float preco) throws SQLException {
        boolean res = false;
        List<Produto> pr = _produtos.getFromUser(u);
        if (pr.contains(p) && !_vendas.aVenda(p) && !_leiloes.emLeilao(p)) {
            Venda v = new Venda(VendasDAO.getNewId(), preco, new GregorianCalendar(), null, null, null, p, u, null);
            res = _vendas.add(v);
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
                _leiloes.update(l);
            }
        }
        return res;
    }

    public boolean leiloar(Utilizador u, Produto p, float base, float tecto) throws SQLException {
        boolean res = false;
        List<Produto> pr = _produtos.getFromUser(u);
        if (pr.contains(p) && !_vendas.aVenda(p) && !_leiloes.emLeilao(p)) {
            GregorianCalendar hoje = new GregorianCalendar();
            GregorianCalendar fecho = (GregorianCalendar) hoje.clone();
            fecho.add(GregorianCalendar.DAY_OF_MONTH, 14);
            GregorianCalendar limite = (GregorianCalendar) fecho.clone();
            limite.add(GregorianCalendar.DAY_OF_MONTH, 7);
            Leilao l = new Leilao(LeiloesDAO.getNewId(), u, p, hoje, fecho, limite, null, null, base, tecto);
            res = _leiloes.add(l);
        }
        return res;
    }

    public boolean registar(String nome, String pass, String mail, String localidade, GregorianCalendar dataNascimento, BufferedImage i) throws SQLException {
        GregorianCalendar dataRegisto = new GregorianCalendar();
        Utilizador u = new Utilizador(nome, BuyKing.md5crypt(pass), mail, localidade, dataRegisto, dataRegisto, i);
        return _utilizadores.add(u);
    }

    public List<Leilao> pesquisaAvançadaLeilao(String nome, String cat, GregorianCalendar dataLimiteFecho, float pmin, float pmax) throws SQLException {
        List<Leilao> list = _leiloes.getLeiloesActivos();
        List<Leilao> res = new ArrayList<Leilao>();
        for (Leilao l : list) {
            if ((nome.matches(l.getP().getNome()) || nome.matches(l.getP().getDescricao()))
                    && pmin <= l.getUltimaLicitacao() && pmax >= l.getTecto()
                    && dataLimiteFecho.after(l.getDataFecho())) {
                res.add(l);
            }
        }
        return res;
    }

    public List<Leilao> pesquisaSimplesLeilao(String nome, String cat) throws SQLException {
        GregorianCalendar lastDate = new GregorianCalendar();
        lastDate.setTimeInMillis(Long.MAX_VALUE);
        return pesquisaAvançadaLeilao(nome, cat, lastDate, 0, Float.MAX_VALUE);
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
}