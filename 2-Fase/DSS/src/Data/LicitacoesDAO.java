/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Leilao;
import Business.Licitacao;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class LicitacoesDAO {
    
    int idLeilao;

    public LicitacoesDAO(int id) {
        idLeilao=id;
    }
    
    private Licitacao readLlicitacao(ResultSet rs)
    {
        
    }
    
    public List<Licitacao> list(Leilao l)
    {
        
    }
}
