/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Classificacao;
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
public class ClassificacaoDAO {
    
    private String classificado;
    
    public ClassificacaoDAO(String username)
    {
        this.classificado=username;
    }
    
    public List<Classificacao> list() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from classificacao where classificado = ?");
        s.setString(1, classificado);
        List<Classificacao> res = new ArrayList<Classificacao>();
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(readClassificacao(rs));
        }
        c.close();
        return res;
    }
    
    private Classificacao readClassificacao(ResultSet rs) throws SQLException
    {
        Utilizador u = new UserDAO().get(rs.getString("classificador"));
        GregorianCalendar data=new GregorianCalendar();
        data.setTime(rs.getDate("dc"));
        float valor = rs.getFloat("va");
        return new Classificacao(u, data, (int)valor);
    }
    
    public int getClassificacaoMedia() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select avg(va) from classificacao where classificado = ?");
        s.setString(1,classificado);
        ResultSet rs = s.executeQuery();
        int res=0;
        if(rs.next()) res=(int) rs.getFloat(1);
        c.close();
        return res;
    }
    
    public int getNrClassificacao() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select count(*) from classificacao where classificado=?");
        s.setString(1, classificado);
        ResultSet rs = s.executeQuery();
        int res = 0;
        if(rs.next()) res = rs.getInt(1);
        c.close();
        return res;
    }
    
    public boolean add(Classificacao cl) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from classificacao where classificado = ? and classificador = ?");
        s.setString(1, classificado);
        s.setString(2, cl.getClassificador().getUsername());
        int res=0;
        ResultSet rs = s.executeQuery();
        
        if(rs.next())
        {
            rs.close();
            s=c.prepareStatement("update classificacao set va=?, dc=? where classificado=? and classificador=?");
            s.setInt(1, cl.getValor());
            s.setDate(2, new Date(cl.getData().getTime().getTime()));
            s.setString(3, classificado);
            s.setString(4, cl.getClassificador().getUsername());
            res=s.executeUpdate();
        }
        else
        {
            s=c.prepareStatement("insert into classificacao values(?,?,?,?)");
            s.setString(1, classificado);
            s.setString(2, cl.getClassificador().getUsername());
            s.setInt(3, cl.getValor());
            s.setDate(4, new Date(cl.getData().getTime().getTime()));
            res=s.executeUpdate();
        }
        c.close();
        return (res<1);
    }
    
    public Classificacao get(Utilizador u) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from classificacao where classificado=? and classificador=?");
        s.setString(1, classificado);
        s.setString(2, u.getUsername());
        Classificacao cl = null;
        ResultSet rs=s.executeQuery();
        if(rs.next()) cl=readClassificacao(rs);
        c.close();
        return cl;
    }
    
    public boolean delete(Utilizador u) throws SQLException
    {
        int res=0;
        if(get(u)!=null)
        {
            Connection c = DataConnection.getDataConnection();
            PreparedStatement s = c.prepareStatement("delete from classificacao where classificado=? and classificador=?");
            s.setString(1, classificado);
            s.setString(2, u.getUsername());
            res=s.executeUpdate();
            c.close();
        }
        return (res<1);
        
    }
    
}
