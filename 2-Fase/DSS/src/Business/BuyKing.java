package Business;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyKing {

    private Map<String, Utilizador> _utilizadores;
    private List<Venda> _vendas = new ArrayList<Venda>();
    private List<Leilao> _leiloes = new ArrayList<Leilao>();
    private List<Suspeita> _suspeitas_;
    private List<Troca> _trocas_;
    private List<Produto> _produtos;

    public List<Produto> pesquisaProduto(String aPchave, String aCat) {
        throw new UnsupportedOperationException();
    }

    public boolean login(String aUsername, String aPassword) {
        //throw new UnsupportedOperationException();
        Utilizador u = _utilizadores.get(aUsername);
        String md5 = BuyKing.md5crypt(aPassword);
        return ((u != null) && (u.getPassmd5().equals(md5)));
    }

    public void registar() {
        throw new UnsupportedOperationException();
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
        while(md5.length()<32)
        {
            md5="0"+md5;
        }
        return md5;
    }
}