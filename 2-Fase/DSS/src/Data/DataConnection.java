/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cesar
 */
public class DataConnection {

    public static Connection getDataConnection() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String url = "jdbc:oracle:thin:@serverPC:1521:orcl";
        Connection c = DriverManager.getConnection(url, "luis", "leilei");
        return c;
    }
}
