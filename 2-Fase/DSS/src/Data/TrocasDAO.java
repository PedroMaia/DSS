/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Produto;
import Business.Troca;
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
    
    
    private Troca read(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("idt");
        UserDAO users=new UserDAO();
        Utilizador prop = users.get(rs.getString("usr1"));
        Utilizador conv=users.get(rs.getString("usr2"));
        ProdutosDAO prods=new ProdutosDAO();
        Produto desejado=prods.get(rs.getInt("idp2"));
        Produto oferta=prods.get(rs.getInt("idp1"));
        GregorianCalendar dataProposta, dataConfirmacao, dataConclusao, dataLimite;
        dataProposta=new GregorianCalendar();
        dataProposta.setTime(rs.getDate("dpt"));
        if(rs.getDate("dct")!=null)
        {
            dataConfirmacao=new GregorianCalendar();
            dataConfirmacao.setTime(rs.getDate("dct"));
        }
        else dataConfirmacao=null;
        if(rs.getDate("dconlt")!=null)
        {
            dataConclusao=new GregorianCalendar();
            dataConclusao.setTime(rs.getDate("dconlt"));
        }
        else dataConclusao=null;
        if(rs.getDate("dlt")!=null)
        {
            dataLimite=new GregorianCalendar();
            dataLimite.setTime(rs.getDate("dlt"));
        }
        else dataLimite=null;
        return new Troca(prop, conv, desejado, oferta, dataProposta, dataConfirmacao, dataConclusao, dataLimite, id);
    }
    
    public Troca get(int id) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select * from ptroca where idt=?");
        s.setInt(1,id);
        ResultSet rs = s.executeQuery();
        Troca res=null;
        if(rs.next())
            res=read(rs);
        c.close();
        return res;
    }
    
    public List<Troca> listConvidado(Utilizador u) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select * from ptroca where usr2=?");
        s.setString(1, u.getUsername());
        List<Troca> res = new ArrayList<Troca>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
    
    public List<Troca> listPropostas(Utilizador u) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select * from ptroca where usr1=?");
        s.setString(1, u.getUsername());
        List<Troca> res = new ArrayList<Troca>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
    
    public boolean add(Troca t) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("insert into ptroca values(?,?,?,?,?,?,?,?,?)");
        s.setInt(1, t.getId());
        s.setString(2,t.getProp().getUsername());
        s.setInt(3, t.getOferta().getId());
        s.setString(4, t.getConvidado().getUsername());
        s.setInt(5, t.getDesejado().getId());
        if(t.getDataLimite()!=null)
            s.setDate(6,new Date(t.getDataLimite().getTime().getTime()));
        else
            s.setNull(6, Types.DATE);
        if(t.getDataConfirmacao()!=null)
            s.setDate(7, new Date(t.getDataConfirmacao().getTime().getTime()));
        else
            s.setNull(7, Types.DATE);
        if(t.getDataConclusao()!=null)
            s.setDate(8, new Date(t.getDataConclusao().getTime().getTime()));
        else
            s.setNull(8, Types.DATE);
        s.setDate(9, new Date(t.getDataProposta().getTime().getTime()));
        int res=s.executeUpdate();
        c.close();
        return res>0;
    }
    
    public boolean update(Troca t) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("update ptroca set dct=?, dlt=? where idt=?");
        if(t.getDataConfirmacao()!=null)
            s.setDate(1, new Date(t.getDataConfirmacao().getTime().getTime()));
        else
            s.setNull(1, Types.DATE);
        if(t.getDataLimite()!=null)
            s.setDate(2, new Date(t.getDataLimite().getTime().getTime()));
        else
            s.setNull(2, Types.DATE);
        s.setInt(3, t.getId());
        int res=s.executeUpdate();
        c.close();
        return res>0;
    }
}
