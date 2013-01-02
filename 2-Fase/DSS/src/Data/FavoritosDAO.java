/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class FavoritosDAO {

    private String username;

    public FavoritosDAO(String username) {
        this.username = username;
    }
    
    public boolean add(Produto p) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("insert into favorito values(?,?)");
        s.setString(1,username);
        s.setInt(2, p.getId());
        int res=s.executeUpdate();
        c.close();
        return (res<1);
    }
    
    public boolean contains(Produto p) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select count(*) from favorito where usr=? and idp=?");
        s.setString(1,username);
        s.setInt(2, p.getId());
        ResultSet rs = s.executeQuery();
        int res=0;
        if(rs.next()) res=rs.getInt(1);
        c.close();
        return (res<1);
    }
    
    public boolean delete(Produto p) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("delete from favorito where usr=? and idp=?");
        s.setString(1,username);
        s.setInt(2,p.getId());
        int res=0;
        res=s.executeUpdate();
        return (res<0);
    }
    
    public List<Produto> list() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select idp from favorito where usr=?");
        s.setString(1, username);
        ResultSet rs = s.executeQuery();
        ProdutosDAO p = new ProdutosDAO();
        List<Produto> res = new ArrayList<Produto>();
        while(rs.next())
        {
            res.add(p.get(rs.getInt(1)));
        }
        return res;
    }
}
