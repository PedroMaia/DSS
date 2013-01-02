/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Classificacao;
import Business.Leilao;
import Business.Licitacao;
import Business.Produto;
import Business.Suspeita;
import Business.Troca;
import Business.Utilizador;
import Business.Venda;
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
        GregorianCalendar DPropst = new GregorianCalendar(2013,02,05);
        GregorianCalendar DConf = new GregorianCalendar(2013,02,06);
        GregorianCalendar DConclus = new GregorianCalendar(2013,02,8);
        GregorianCalendar DLimit = new GregorianCalendar(2013,02,12);
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
        Produto p = new Produto(1,"pneu",imagemProd,"mtofixe","carros");
        Produto p2 = new Produto(2,"roda",imagemProd,"fixe","carros");
        ProdutosDAO pDao = new ProdutosDAO();
        //variaveis favoritos
        FavoritosDAO favDao = new FavoritosDAO("pedroMaia");
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
        //variaveis suspeita
        SuspeitasDAO susp = new SuspeitasDAO(2);
        Suspeita su = new Suspeita(u4,"Gatuno");
        List<Suspeita> listSusp = new ArrayList<Suspeita>();
        //variaveis Troca
        Troca tr = new Troca(u1,u2,p2,p,DPropst,DConf,DConclus,DLimit,1);
        Troca tr2;
        TrocasDAO trocs = new TrocasDAO();
        List<Troca> listTrocConv = new ArrayList<Troca>();
        List<Troca> listTrocProps = new ArrayList<Troca>();
        //variaveis Venda
        VendasDAO vends = new VendasDAO();
        List<Venda> listVendsAb = new ArrayList<Venda>();
        List<Venda> listVendsC = new ArrayList<Venda>();
        List<Venda> listVendsV = new ArrayList<Venda>();
        Venda v1 = new Venda(1,30,DEnvProd,DConf,DConclus,DLimit,p2,u1,u5);
        Venda v2;
        
        
        
        
        
        
        /*
        //metodos User
        bol4=userd.add(u3);
        out.println(bol4);
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
        
        bol = pDao.add(p, u1);
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
        
        
        
        
          //metodos licitacao
        max=licitDAO.getMaxLicitacao();
        out.println(max);
        u4=licitDAO.getVencedor();
        out.println(u4);
       //metodos favoritos
        bol6=favDao.add(p2);
        out.println(bol6);
        bol11=leil.add(le);
        out.println(bol11); 
        bol10=licitDAO.addLicitacao(l);
        out.println(bol10);
        listFavs=favDao.list();
        le2=leil.get(1);
        out.println(le2);
        bol7=favDao.contains(p2);
        out.println(bol7);
        bol8=favDao.delete(p2);
        out.println(bol8);
        bol9=favDao.contains(p2);
        out.println(bol9);
      */
        //metodos suspeita
        //bol7=susp.add(su);
        //out.println(bol7);
        //listSusp=susp.list();
        //metodos Troca
        bol=trocs.add(tr);
        out.println(bol);
        listTrocConv = trocs.listConvidado(u1);
        listTrocProps = trocs.listPropostas(u2);
        tr2=trocs.get(1);
        out.println(tr);
        bol=trocs.update(tr2);
        out.println(bol);
        //metodos venda
        bol=vends.add(v1);
        out.println(bol);
        listVendsAb = vends.getVendasAbertas();
        out.print(listVendsAb);
        listVendsC=vends.getVendasComprador(u5);
        listVendsV=vends.getVendasVendedor(u1);
        v2=vends.get(1);
        bol=vends.update(v2);
        out.print(bol);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
