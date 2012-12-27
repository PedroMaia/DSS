/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Cesar
 */
public class Licitacao {
    
    private Utilizador u;
    private int v;
    
    public Licitacao(Utilizador user, int valor)
    {
        u=user;
        v=valor;
    }
    
    public Utilizador getUser()
    {
        return u;
    }
    
    public int getValor()
    {
        return v;
    }
}
