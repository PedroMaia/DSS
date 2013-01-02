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

    public Classificacao(Utilizador classificador, GregorianCalendar data, int valor) {
        this.classificador = classificador;
        this.data = data;
        this.valor = valor;
    }
    
    
    
    private Utilizador classificador;
    private GregorianCalendar data;
    private int valor;

    public Utilizador getClassificador() {
        return classificador;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.classificador != null ? this.classificador.hashCode() : 0);
        hash = 19 * hash + (this.data != null ? this.data.hashCode() : 0);
        hash = 19 * hash + this.valor;
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
        final Classificacao other = (Classificacao) obj;
        if (this.classificador != other.classificador && (this.classificador == null || !this.classificador.equals(other.classificador))) {
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        if (this.valor != other.valor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classificacao{" + "classificador=" + classificador + ", data=" + data + ", valor=" + valor + '}';
    }
    
    
}
