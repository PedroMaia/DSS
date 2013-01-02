/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Classificacao;
import Business.Leilao;
import Business.Licitacao;
import Business.Produto;
import Business.Utilizador;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import static java.lang.System.out;

/**
 *
 * @author Paxaxa
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //Variaveis Calendario
        GregorianCalendar nasc = new GregorianCalendar(1992,11,28);
        GregorianCalendar reg = new GregorianCalendar(2012,12,31);
        GregorianCalendar dClass = new GregorianCalendar();
        GregorianCalendar DFecho = new GregorianCalendar(2013,01,31);
        GregorianCalendar DPag = new GregorianCalendar(2013,02,04);
        GregorianCalendar DEnvProd = new GregorianCalendar(2013,02,05);
        //variaveis imagens
        BufferedImage imagem = new BufferedImage(10,10,1);
        BufferedImage imagemProd = new BufferedImage(10,10,1);
        //booleanos
        Boolean bol = false,bol2=false,bol3=false,bol4=false,bol5=false,bol6=false;
        Boolean bol7=false,bol8=false,bol9=false,bol10=false,bol11=false;
        //inteiros
        int classificMed,classific;
        //variaveis utilizador
        Utilizador u1 = new Utilizador("luis","58","mail","lombo",nasc,reg,imagem);
        Utilizador u2 = new Utilizador("pedroMaia","20","mail2","rib",nasc,reg,imagem);
        Utilizador u3 = new Utilizador("cesar","82","mail3","marta",nasc,reg,imagem);
        Utilizador u4 = new Utilizador("pedro96","96","mail4","vizela",nasc,reg,imagem);
        Utilizador u5 = new Utilizador("teodoro","estupido","mail5","seila",nasc,reg,imagem);
        List<Utilizador> listUser = new ArrayList<Utilizador>();
        UserDAO userd= new UserDAO();
        //variaveis classificacao
        Classificacao cla = new Classificacao(u1,dClass,5);
        Classificacao cla2;
        ClassificacaoDAO c = new ClassificacaoDAO("pedroMaia");
        List<Classificacao> listClassific = new ArrayList<Classificacao>();
        //variaveis produto
        List<Produto> listProds = new ArrayList<Produto>();
        Produto p;
        Produto p2 = new Produto(2,"roda",imagemProd,"fixe","carros");
        ProdutosDAO pDao = new ProdutosDAO();
        //variaveis favoritos
        FavoritosDAO favDao = new FavoritosDAO("favs");
        List<Produto> listFavs = new ArrayList<Produto>();
        //variaveis licitacao
        LicitacoesDAO licitDAO= new LicitacoesDAO(1);
        float max;
        Licitacao l = new Licitacao(u3,50,reg);
        //variaveis leilao
        LeiloesDAO leil = new LeiloesDAO();
        float base=20;
        float teto=90;
        Leilao le = new Leilao(1,u4,p2,reg,DFecho,DFecho,DPag,DEnvProd,base,teto);
        Leilao le2;
        //metodos User
        //bol4=userd.add(u3);
        //out.println(bol4);
        bol4=userd.add(u1);
        out.println(bol4);
        bol4=userd.add(u2);
        out.println(bol4);
        bol4=userd.add(u4);
        out.println(bol4);
        bol4=userd.add(u5);
        out.println(bol4);
        u3=userd.get("cesar");
        out.println(u3);
        listUser=userd.list();
        bol5=userd.update(u3);
        out.println(bol5);
        
        //metodos Produto
        bol = pDao.add(p2, u1);
        out.println(bol);
        p=pDao.get(1);
        listProds=pDao.getFromUser(u1);
        
        
        //metodos classificacao
        bol2=c.add(cla);
        out.println(bol2);
        cla2=c.get(u3);
        out.println(cla2);
        bol3=c.delete(u2);
        out.println(bol3);
        listClassific = c.list();
        classificMed = c.getClassificacaoMedia();
        out.println(classificMed);
        classific=c.getNrClassificacao();
        out.println(classific);
        
        
        
        
        //metodos favoritos
        bol6=favDao.add(p);
        out.println(bol6);
        bol11=leil.add(le);
        out.println(bol11); 
        bol10=licitDAO.addLicitacao(l);
        out.println(bol10);
        listFavs=favDao.list();
        le2=leil.get(1);
        out.println(le2);
        bol7=favDao.contains(p);
        out.println(bol7);
        bol8=favDao.delete(p);
        out.println(bol8);
        bol9=favDao.contains(p);
        out.println(bol9);
        //metodos licitacao
        max=licitDAO.getMaxLicitacao();
        out.println(max);
        u4=licitDAO.getVencedor();
        out.println(u4);
       
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
