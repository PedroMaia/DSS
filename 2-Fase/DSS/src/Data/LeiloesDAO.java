/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Leilao;
import Business.Produto;
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
    
    private Leilao read(ResultSet rs) throws SQLException
    {
        int id=rs.getInt("idl");
        Produto p = (new ProdutosDAO()).get(rs.getInt("idp"));
        Utilizador leiloador = (new UserDAO()).get(rs.getString("ul"));
        GregorianCalendar dataLeilao, dataFecho, dataLimiteLeilao, dataPagamento, dataEnvioProduto;
        dataLeilao = new GregorianCalendar();
        dataLeilao.setTime(rs.getDate("di"));
        dataFecho = new GregorianCalendar();
        dataFecho.setTime(rs.getDate("df"));
        dataLimiteLeilao=new GregorianCalendar();
        dataLimiteLeilao.setTime(rs.getDate("dll"));
        dataPagamento=new GregorianCalendar();
        dataPagamento.setTime(rs.getDate("dp"));
        dataEnvioProduto=new GregorianCalendar();
        dataEnvioProduto.setTime(rs.getDate("dep"));
        float base = rs.getFloat("pb");
        float tecto = rs.getFloat("pml");
        Leilao res = new Leilao(id, leiloador, p, dataLeilao, dataFecho, dataLimiteLeilao, dataPagamento, dataEnvioProduto, base, tecto);
        return res;
    }
    
    public Leilao get(int id) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from leilao where idl=?");
        s.setInt(1, id);
        ResultSet rs = s.executeQuery();
        Leilao res=null;
        if(rs.next())
        {
            
        }
        c.close();
        return res;
    }
}
