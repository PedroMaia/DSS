/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Licitacao;
import Business.Utilizador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author Cesar
 */
public class LicitacoesDAO {
    
    int idLeilao;

    public LicitacoesDAO(int id) {
        idLeilao=id;
    }
    
    private Licitacao readLlicitacao(ResultSet rs) throws SQLException
    {
        String username=rs.getString("ul");
        Utilizador u=(new UserDAO()).get(username);
        float v=rs.getFloat("vl");
        GregorianCalendar data = new GregorianCalendar();
        data.setTime(rs.getDate("dl"));
        return new Licitacao(u, v,data);
    }
    
    

    public float getMaxLicitacao() throws SQLException {
        //throw new UnsupportedOperationException("Not yet implemented");
        Connection c=DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select max(vl) from Licitacao where idl=?");
        s.setInt(1,idLeilao);
        ResultSet rs = s.executeQuery();
        float res=0;
        if(rs.next()) {
            res= rs.getFloat(1);
        }
        c.close();
        return res;
    }
    
    public Utilizador getVencedor() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select ul from licitacao where idl = ? and vl = (select max(vl) from licitacao where idl=?)");
        s.setInt(1, idLeilao);
        s.setInt(2, idLeilao);
        ResultSet rs = s.executeQuery();
        Utilizador res=null;
        if(rs.next())
        {
            res=new UserDAO().get(rs.getString(1));
        }
        c.close();
        return res;
    }
    
    public boolean addLicitacao(Licitacao l) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement(
                "insert into licitacao values(? , ? , ? , ?)");
        s.setInt(1,idLeilao);
        s.setString(2,l.getUser().getUsername());
        s.setDate(3, new Date(l.getData().getTime().getTime()));
        s.setFloat(4, l.getValor());
        int res=s.executeUpdate();
        c.close();
        return (res>0);
    }
}
