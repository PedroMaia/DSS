package Business;

import Data.ClassificacaoDAO;
import Data.FavoritosDAO;
import Data.ProdutosDAO;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

public class Utilizador {

    private String _username;
    private String _passmd5;
    private String email;
    private String localidade;
    private GregorianCalendar dataNascimento;
    private GregorianCalendar dataRegisto;
    private BufferedImage imagem;
    private ClassificacaoDAO _classificacao;
    private FavoritosDAO _wishlist;
    private ProdutosDAO meusProds;

    public Utilizador(String name, String pass, String mail, String l, GregorianCalendar dN, GregorianCalendar dR, BufferedImage i) {
        _username = name;
        _passmd5 = pass;
        email = mail;
        localidade = l;
        dataNascimento = dN;
        dataRegisto = dR;
        imagem = i;
        _classificacao = new ClassificacaoDAO(name);
        _wishlist = new FavoritosDAO(name);
        meusProds = new ProdutosDAO();

    }

    public String getUsername() {
        return _username;
    }

    public String getPassmd5() {
        return _passmd5;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getEmail() {
        return email;
    }

    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    public GregorianCalendar getDataRegisto() {
        return dataRegisto;
    }

    public BufferedImage getImagem() {
        return imagem;
    }


    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this._username != null ? this._username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilizador other = (Utilizador) obj;
        if ((this._username == null) ? (other._username != null) : !this._username.equals(other._username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilizador{" + "_username=" + _username + ", _passmd5=" + _passmd5 + ", email=" + email + ", localidade=" + localidade + ", dataNascimento=" + dataNascimento + ", dataRegisto=" + dataRegisto + ", imagem=" + imagem + '}';
    }

    public boolean addClassificacao(Classificacao c) throws SQLException {
        return _classificacao.add(c);
    }

    public boolean addWishList(Produto p) throws SQLException {
        return _wishlist.add(p);
    }

    public List<Produto> getWishList() throws SQLException {
        return _wishlist.list();
    }

    public List<Produto> getProdutos() throws SQLException {
        return meusProds.getFromUser(this);
    }
    
    public int getClassificacao() throws SQLException
    {
        return _classificacao.getClassificacaoMedia();
    }
}