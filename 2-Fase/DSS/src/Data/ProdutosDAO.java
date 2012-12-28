/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cesar
 */
public class ProdutosDAO {
    
    
    public static int getNewId() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select sidp.nextval from dual");
        ResultSet rs = s.executeQuery();
        rs.next();
        rs.close();
        c.close();
        return rs.getInt(1);
    }
}
