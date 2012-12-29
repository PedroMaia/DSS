/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Cesar
 */
public class LeiloesDAO {

    public static int getNewId() throws SQLException {
        Connection c=DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select ")
    }
    
}
