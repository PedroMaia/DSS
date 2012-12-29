/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.GregorianCalendar;

/**
 *
 * @author Cesar
 */
public class Classificacao {

    private Utilizador classificador;
    private GregorianCalendar data;
    private int valor;
	
    public Classificacao(Utilizador classificador, GregorianCalendar data, int valor) {
        this.classificador = classificador;
        this.data = data;
        this.valor = valor;
    }
    
    
    


    public Utilizador getClassificador() {
        return classificador;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public int getValor() {
        return valor;
    }
    
    
}
