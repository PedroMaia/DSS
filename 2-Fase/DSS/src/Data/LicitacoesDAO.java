/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Licitacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    

    public int getMaxLicitacao() throws SQLException {
        //throw new UnsupportedOperationException("Not yet implemented");
        Connection c=DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("selece max(vl) from Licitacao where idl=?");
        s.setInt(1,idLeilao);
        ResultSet rs = s.executeQuery();
        if(rs.next()) {
            return rs.getInt(1);
        }
        else {
            return 0;
        }
    }
}
