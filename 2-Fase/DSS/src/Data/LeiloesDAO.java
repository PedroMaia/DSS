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
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
        if(l.getDataLimiteLeilao()!=null)s.setDate(4,new Date(l.getDataLimiteLeilao().getTime().getTime()));
        else s.setNull(4, Types.DATE);
        if(l.getDataPagamento()!=null) s.setDate(5, new Date(l.getDataPagamento().getTime().getTime()));
        else s.setNull(5, Types.DATE);
        if(l.getDataEnvioProduto()!=null)s.setDate(6, new Date(l.getDataEnvioProduto().getTime().getTime()));
        else s.setNull(6, Types.DATE);
        s.setDate(7, new Date(l.getDataFecho().getTime().getTime()));
        s.setDate(8, new Date(l.getDataLeilao().getTime().getTime()));
        s.setFloat(9, l.getBase());
        s.setFloat(10, l.getTecto());
        int res = s.executeUpdate();
        return (res>0);
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
        if(rs.getDate("dll")!=null)
        {
            dataLimiteLeilao=new GregorianCalendar();
            dataLimiteLeilao.setTime(rs.getDate("dll"));
        }
        else dataLimiteLeilao=null;
        if(rs.getDate("dp")!=null)
        {
            dataPagamento=new GregorianCalendar();
            dataPagamento.setTime(rs.getDate("dp"));
        }
        else dataPagamento=null;
        if(rs.getDate("dep")!=null)
        {
            dataEnvioProduto=new GregorianCalendar();
            dataEnvioProduto.setTime(rs.getDate("dep"));
        }
        else dataEnvioProduto=null;
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
            res=read(rs);
        }
        c.close();
        return res;
    }
    
    public boolean update(Leilao l) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("update leilao set dll=? where idl=?");
        if(l.getDataLimiteLeilao()!=null)
            s.setDate(1, new Date(l.getDataLimiteLeilao().getTime().getTime()));
        else
            s.setNull(1, Types.DATE);
        s.setInt(2, l.getId());
        int res = s.executeUpdate();
        return (res>0);
    }
    
    public boolean emLeilao(Produto p) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select count(*) from leilao where idp=? and sysdate<df and (select max(vl) from licitacao where licitacao.idl = leilao.idl) < pml and dp is not null and dep is not null");
        s.setInt(1,p.getId());
        ResultSet rs=s.executeQuery();
        rs.next();
        int res = rs.getInt(1);
        return res>0;
    }
    
    public List<Leilao> getLeiloesActivos() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from leilao where sysdate<df and nvl((select max(vl) from licitacao where licitacao.idl=leilao.idl),0)<pml");
        List<Leilao> res = new ArrayList<Leilao>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
    
    public List<Leilao> getLeiloesLeiloador(Utilizador u) throws SQLException{
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from leilao where ul=?");
        s.setString(1,u.getUsername());
        List<Leilao> res = new ArrayList<Leilao>();
        ResultSet rs = s.executeQuery();
        while(rs.next()){
            res.add(read(rs));
        }
        c.close();
        return res;
        
    }
    
    public List<Leilao> getLeiloesLicitador(Utilizador u) throws SQLException{
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from leilao where idl in (select distinct idl from licitacao where ul=?)");
        s.setString(1, u.getUsername());
        List<Leilao> res = new ArrayList<Leilao>();
        ResultSet rs = s.executeQuery();
        while(rs.next()){
            res.add(read(rs));
        }
        c.close();
        return res;
    }
}
