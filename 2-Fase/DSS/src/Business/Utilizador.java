package Business;

import Data.ClassificacaoDAO;
import Data.FavoritosDAO;
import java.awt.image.BufferedImage;
import java.util.GregorianCalendar;

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
    
    
    public Utilizador(String name, String pass, String mail, String l, GregorianCalendar dN, GregorianCalendar dR, BufferedImage i)
    {
        _username=name;
        _passmd5=BuyKing.md5crypt(pass);
        email=mail;
        localidade=l;
        dataNascimento= dN;
        dataRegisto=dR;
        imagem=i;
        _classificacao=new ClassificacaoDAO(name);
        _wishlist=new FavoritosDAO(name);
        
        
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