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
public class Licitacao {
    
    private Utilizador u;
    private float v;
    private GregorianCalendar data;
    
    public Licitacao(Utilizador user, float valor,GregorianCalendar data)
    {
        u=user;
        v=valor;
        this.data = data;
    }
    
    public Utilizador getUser()
    {
        return u;
    }
    
    public float getValor()
    {
        return v;
    }
    
    public GregorianCalendar getData(){
        return data;
    }
    
    public Licitacao(Licitacao l){
        u=l.getUser();
        v=l.getValor();
        data=l.getData();
    }
    
    public Licitacao clone(){
        return new Licitacao(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.u != null ? this.u.hashCode() : 0);
        hash = 47 * hash + Float.floatToIntBits(this.v);
        hash = 47 * hash + (this.data != null ? this.data.hashCode() : 0);
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
        final Licitacao other = (Licitacao) obj;
        if (this.u != other.u && (this.u == null || !this.u.equals(other.u))) {
            return false;
        }
        if (Float.floatToIntBits(this.v) != Float.floatToIntBits(other.v)) {
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Licitacao{" + "u=" + u + ", v=" + v + ", data=" + data + '}';
    }
    
    
}
