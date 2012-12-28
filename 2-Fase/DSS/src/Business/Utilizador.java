package Business;

import Data.ClassificacaoDAO;
import Data.FavoritosDAO;
import Data.LeiloesDAO;
import Data.ProdutosDAO;
import Data.TrocasDAO;
import Data.VendasDAO;
import java.awt.image.BufferedImage;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class Utilizador {

    private String _username;
    private String _passmd5;
    private String email;
    private GregorianCalendar dataNascimento;
    private BufferedImage imagem;
    private ClassificacaoDAO _classificacao;
    private ProdutosDAO _meusProds;
    private FavoritosDAO _wishlist;
    private VendasDAO minhasVendas;
    private LeiloesDAO meusLeiloes;
    private TrocasDAO minhasTrocas;
    
    public Utilizador(String name, String pass, String mail, GregorianCalendar dN, BufferedImage i)
    {
        _username=name;
        _passmd5=BuyKing.md5crypt(pass);
        email=mail;
        dataNascimento= dN;
        imagem=i;
        _classificacao=new ClassificacaoDAO();
        _meusProds = new ProdutosDAO();
        _wishlist=new FavoritosDAO();
        minhasVendas = new VendasDAO();
        meusLeiloes=new LeiloesDAO();
        minhasTrocas = new TrocasDAO();
        
    }

    public String getUsername() {
        return _username;
    }

    public String getPassmd5() {
        return _passmd5;
    }

    public String getEmail() {
        return email;
    }

    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    public BufferedImage getImagem() {
        return imagem;
    }
    
    
    
    

    public void compra(Venda aV) {
        throw new UnsupportedOperationException();
        
    }

    public void licitar(Leilao aL, int aV) {
        throw new UnsupportedOperationException();
       
    }

    public void addProduto(Produto aP) {
        throw new UnsupportedOperationException();
        
    }

    public void removeProduto(Produto aP) {
        throw new UnsupportedOperationException();
        
    }

    public Suspeita reporta(Produto aP, String aUsername, String aJust) {
        throw new UnsupportedOperationException();
    }

    public void classifica(Utilizador classificado, int aClassificacao) {
        throw new UnsupportedOperationException();
        
    }

    public Venda vende(Produto aP) {
        throw new UnsupportedOperationException();
    }

    public void leiloar(Produto aP, int aBase, int aTecto) {
        throw new UnsupportedOperationException();
    }

    public int getClassificacao() {
        throw new UnsupportedOperationException();
        
    }
}