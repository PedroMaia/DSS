/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Produto;
import Business.Utilizador;
import Business.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class VendasDAO {

    public static int getNewId() throws SQLException {
        Connection c=DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select sidv.nextval from dual");
        ResultSet rs=s.executeQuery();
        rs.next();
        int res = rs.getInt(1);
        c.close();
        return res;
    }
    
    private Venda read(ResultSet rs) throws SQLException
    {
        int id=rs.getInt("idv");
        float preco=rs.getFloat("pr");
        GregorianCalendar dataVenda, dataLimiteVenda, dataEnvioProduto, dataPagamento;
        dataVenda=new GregorianCalendar();
        dataVenda.setTime(rs.getDate("div"));
        dataLimiteVenda=new GregorianCalendar();
        dataLimiteVenda.setTime(rs.getDate("dlv"));
        dataEnvioProduto=new GregorianCalendar();
        dataEnvioProduto.setTime(rs.getDate("dep"));
        dataPagamento=new GregorianCalendar();
        dataPagamento.setTime(rs.getDate("dp"));
        Produto p = (new ProdutosDAO()).get(rs.getInt("idp"));
        UserDAO users=new UserDAO();
        Utilizador vendedor = users.get(rs.getString("vd"));
        Utilizador comprador = users.get("cp");
        return new Venda(id, preco, dataVenda, dataLimiteVenda, dataEnvioProduto, dataPagamento, p, vendedor, comprador);
        
    }
    
    public Venda get(int id) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from venda where idv=?");
        s.setInt(1, id);
        ResultSet rs=s.executeQuery();
        Venda res=null;
        if(rs.next())
        {
            res=read(rs);
        }
        c.close();
        return res;
    }
    
    public List<Venda> getVendasAbertas() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from vendas where cp is null");
        List<Venda> res = new ArrayList<Venda>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
    
    public List<Venda> getVendasComprador(Utilizador u) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from vendas where cp=?");
        s.setString(1,u.getUsername());
        List<Venda> res = new ArrayList<Venda>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
    
    public List<Venda> getVendasVendedor(Utilizador u) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from vendas where vd=?");
        s.setString(1,u.getUsername());
        List<Venda> res = new ArrayList<Venda>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
}
