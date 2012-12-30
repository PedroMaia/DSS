/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Leilao;
import Business.Utilizador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cesar
 */
public class LeiloesDAO {

    public static int getNewId() throws SQLException {
        Connection c=DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select sidl.nextval from dual");
        ResultSet rs=s.executeQuery();
        rs.next();
        int res = rs.getInt(1);
        c.close();
        return res;
    }
    
    public boolean add(Leilao l) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("insert into leilao values(?,?,?,?,?,?,?,?,?,?)");
        s.setInt(1,l.getId());
        s.setInt(2,l.getP().getId());
        s.setString(3, l.getLeiloador().getUsername());
        s.setDate(4,new Date(l.getDataLimiteLeilao().getTime().getTime()));
        s.setDate(5, new Date(l.getDataPagamento().getTime().getTime()));
        s.setDate(6, new Date(l.getDataEnvioProduto().getTime().getTime()));
        s.setDate(7, new Date(l.getDataFecho().getTime().getTime()));
        s.setDate(8, new Date(l.getDataLeilao().getTime().getTime()));
        s.setFloat(9, l.getBase());
        s.setFloat(10, l.getTecto());
        int res = s.executeUpdate();
        return (res<0);
    }
}
