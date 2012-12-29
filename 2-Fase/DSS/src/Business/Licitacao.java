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
    
    public Licitacao(Utilizador user, float valor)
    {
        u=user;
        v=valor;
        data = new GregorianCalendar();
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
}
