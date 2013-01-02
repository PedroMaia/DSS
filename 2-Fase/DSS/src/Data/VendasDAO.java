/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Produto;
import Business.Utilizador;
import Business.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
        if(rs.getDate("dlv")!=null)
        {
            dataLimiteVenda=new GregorianCalendar();
            dataLimiteVenda.setTime(rs.getDate("dlv"));
        }
        else dataLimiteVenda=null;
        if(rs.getDate("dep")!=null)
        {
            dataEnvioProduto=new GregorianCalendar();
            dataEnvioProduto.setTime(rs.getDate("dep"));
        }
        else dataEnvioProduto=null;
        if(rs.getDate("dp")!=null)
        {
            dataPagamento=new GregorianCalendar();
            dataPagamento.setTime(rs.getDate("dp"));
        }
        else dataPagamento=null;
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
        PreparedStatement s = c.prepareStatement("select * from venda where cp is null");
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
    
    public boolean add(Venda v) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("insert into venda values(?,?,?,?,?,?,?,?,?)");
        s.setInt(1,v.getId());
        s.setInt(2,v.getProduto().getId());
        s.setFloat(3,v.getPreco());
        if(v.getComprador()!=null)
            s.setString(4,v.getComprador().getUsername());
        else
            s.setNull(4, Types.VARCHAR);
        if(v.getDataLimiteVenda()!=null)
            s.setDate(5,new Date(v.getDataLimiteVenda().getTime().getTime()));
        else
            s.setNull(5,Types.DATE);
        if(v.getDataPagamento()!=null)
            s.setDate(6, new Date(v.getDataPagamento().getTime().getTime()));
        else
            s.setNull(6,Types.DATE);
        if(v.getDataEnvioProduto()!=null)
            s.setDate(7,new Date(v.getDataEnvioProduto().getTime().getTime()));
        else
            s.setNull(7, Types.DATE);
        s.setDate(8,new Date(v.getDataVenda().getTime().getTime()));
        s.setString(9, v.getVendedor().getUsername());
        int res = s.executeUpdate();
        c.close();
        return res<1;
    }
    
    public boolean update(Venda v) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("update venda set cp=?, dlv=? where idv=?");
        if(v.getComprador()!=null)
            s.setString(1, v.getComprador().getUsername());
        else
            s.setNull(1, Types.VARCHAR);
        if(v.getDataLimiteVenda()!=null)
            s.setDate(2, new Date(v.getDataLimiteVenda().getTime().getTime()));
        else
            s.setNull(2, Types.DATE);
        s.setInt(3,v.getId());
        int res = s.executeUpdate();
        c.close();
        return res<1;
    }
}
