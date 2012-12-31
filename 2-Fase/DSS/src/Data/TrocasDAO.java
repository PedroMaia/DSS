/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Troca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cesar
 */
public class TrocasDAO {
    public static int getNewId() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select sidt.nextval from dual");
        ResultSet rs = s.executeQuery();
        rs.next();
        int res=rs.getInt(1);
        c.close();
        return res;
    }
    
    
    private Troca read(ResultSet rs)
    {
        
    }
}
