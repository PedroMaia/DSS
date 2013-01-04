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
 *Classe de acesso as clasificaçoes na base de dados.
 * @author Cesar
 */
public class ClassificacaoDAO {
    
    private String classificado;
    
    public ClassificacaoDAO(String username)
    {
        this.classificado=username;
    }
    
    /**
     * Devolve a lista de classificaçoes do utilizador a que se refere.
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * Le uma linha do ResultSet e devolve a classificaçao.
     * @param rs ResultSet a ler.
     * @return classicicaçao na linha actual do ResultSet.
     * @throws SQLException 
     */
    private Classificacao readClassificacao(ResultSet rs) throws SQLException
    {
        Utilizador u = new UserDAO().get(rs.getString("classificador"));
        GregorianCalendar data=new GregorianCalendar();
        data.setTime(rs.getDate("dc"));
        float valor = rs.getFloat("va");
        return new Classificacao(u, data, (int)valor);
    }
    
    /**
     * Metodo que devolve a classificaçao media do utilizador.
     * @return classificacao media.
     * @throws SQLException 
     */
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
    
    /**
     * Devolve o numero de classificadores que classificou este utilizador.
     * @return numero de classificadores.
     * @throws SQLException 
     */
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
    
    /**
     * Adiciona uma classificaçao.
     * Caso ja exista faz a actualizaçao da classificacao.
     * @param cl
     * @return se a classificaçao foi adicionada com sucesso.
     * @throws SQLException 
     */
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
        return (res>0);
    }
    
    /**
     * Devolve a classificaçao dada por certo utilizador.
     * @param u classificador.
     * @return Classificaçao atribuida. Caso nao exista devolve null.
     * @throws SQLException 
     */
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
    
    /**
     * Elimina a classificaçao dada por um utilizador.
     * @param u classificador a eliminar.
     * @return se foi removida com sucesso.
     * @throws SQLException 
     */
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
