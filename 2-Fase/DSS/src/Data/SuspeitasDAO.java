/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Suspeita;
import Business.Utilizador;
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
public class SuspeitasDAO {
    
    int idp;

    public SuspeitasDAO(int id) {
        this.idp=id;
    }
    
    public boolean add(Suspeita sus) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("insert into casosuspeito values(?,?,?)");
        s.setString(1, sus.getQueixoso().getUsername());
        s.setInt(2,this.idp);
        s.setString(3,sus.getJust());
        int res=s.executeUpdate();
        c.close();
        return res<0;
    }
    
    private Suspeita read(ResultSet rs) throws SQLException
    {
        Utilizador u = (new UserDAO()).get(rs.getString("uas"));
        String just = rs.getString("js");
        return new Suspeita(u, just);
    }
    
    public List<Suspeita> list() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from casosuspeito where idp=?");
        s.setInt(1, this.idp);
        List<Suspeita> res = new ArrayList<Suspeita>();
        ResultSet rs= s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
}
