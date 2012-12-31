/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Classificacao;
import Business.Produto;
import Business.Utilizador;
import java.awt.image.BufferedImage;
import java.util.GregorianCalendar;

/**
 *
 * @author Paxaxa
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GregorianCalendar nasc = new GregorianCalendar(1992,11,28);
        GregorianCalendar reg = new GregorianCalendar(2012,12,31);
        GregorianCalendar dClass = new GregorianCalendar();
        BufferedImage imagem = new BufferedImage(10,10,1);
        BufferedImage imagemProd = new BufferedImage(10,10,1);
        Utilizador u = new Utilizador("luis","boss","mail","lombo",nasc,reg,imagem);
        Classificacao cla = new Classificacao(u,dClass,5);
        ClassificacaoDAO c = new ClassificacaoDAO("ze");
        Produto p = new Produto(1,"roda",imagemProd,"fixe","carros");
        ProdutoDAO p2= new ProdutoDAO
        
    }
}
