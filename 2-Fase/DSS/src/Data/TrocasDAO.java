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
        dataConfirmacao=new GregorianCalendar();
        dataConfirmacao.setTime(rs.getDate("dct"));
        dataConclusao=new GregorianCalendar();
        dataConclusao.setTime(rs.getDate("dconlt"));
        dataLimite=new GregorianCalendar();
        dataLimite.setTime(rs.getDate("dlt"));
        return new Troca(prop, conv, desejado, oferta, dataProposta, dataConfirmacao, dataConclusao, dataLimite, id);
    }
    
    public Troca get(int id) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select * from troca where idt=?");
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
        PreparedStatement s=c.prepareStatement("select * from troca where usr2=?");
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
        PreparedStatement s=c.prepareStatement("select * from troca where usr1=?");
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
        PreparedStatement s=c.prepareStatement("insert into troca values(?,?,?,?,?,?,?,?,?)");
        s.setInt(1, t.getId());
        s.setString(2,t.getProp().getUsername());
        s.setInt(3, t.getOferta().getId());
        s.setString(4, t.getConvidado().getUsername());
        s.setInt(5, t.getDesejado().getId());
        s.setDate(6,new Date(t.getDataLimite().getTime().getTime()));
        s.setDate(7, new Date(t.getDataConfirmacao().getTime().getTime()));
        s.setDate(8, new Date(t.getDataConclusao().getTime().getTime()));
        s.setDate(9, new Date(t.getDataProposta().getTime().getTime()));
        int res=s.executeUpdate();
        c.close();
        return res<1;
    }
}
