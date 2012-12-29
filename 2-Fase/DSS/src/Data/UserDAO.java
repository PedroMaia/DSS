/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Utilizador;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Cesar
 */
public class UserDAO {
    
    /**
     * Lê os dados do utilizador a partir do username
     * @param username username do utilizador a ler da base de dados
     * @return o utilizador que tem o username acima referido. retorna null caso nao exista
     * @throws SQLException 
     */
    public Utilizador get(String username) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select * from utilizador where usr = ?");
        s.setString(1, username);
        ResultSet rs=s.executeQuery();
        if (rs.next())
        {
            return readUser(rs);
        }
        else return null;
    }
    
    /**
     * Lê os dados de um Utilizador a partir de um resultSet, mas é necessario efectuar a operação next antes.
     * @param rs ResultSet a ler.
     * @return Dados do utilizador lidos.
     * @throws SQLException 
     */
    private Utilizador readUser(ResultSet rs) throws SQLException
    {
            String name = rs.getString("usr");
            String pass= rs.getString("pw");
            String mail=rs.getString("e");
            String loc=rs.getString("lcp");
            GregorianCalendar dN = new GregorianCalendar();
            dN.setTime(rs.getDate("ddr"));
            GregorianCalendar dR = new GregorianCalendar();
            dR.setTime(rs.getDate("ddr"));
            BufferedImage i = null;
            try {
                i= ImageIO.read(new ByteArrayInputStream(rs.getBytes("fu")));
            } catch (IOException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new Utilizador(name, pass, mail, loc, dN, dR, i);
    }
    
    /**
     * Lista todos os Utilizadores da Base de dados.
     * @return Lista de Utilizadores.
     * @throws SQLException 
     */
    public List<Utilizador> list() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        List<Utilizador> res = new ArrayList<Utilizador>();
        PreparedStatement s = c.prepareStatement("select * from utilizadores");
        ResultSet rs = s.executeQuery();
        while(rs.next())
        {
            res.add(readUser(rs));
        }
        return res;
    }
    
    public boolean add(Utilizador u) throws SQLException
    {
        Connection c=DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("insert into utilizador values ( ? , ? , ? , ? , ? , ? , ? )");
        s.setString(1, u.getUsername());
        s.setString(2, u.getPassmd5());
        s.setString(3, u.getEmail());
        s.setString(4, u.getLocalidade());
        s.setDate(5, new Date(u.getDataNascimento().getTime().getDate()));
        s.setDate(6, new Date(u.getDataRegisto().getTime().getTime()));
        if(u.getImagem()==null) s.setNull(7, Types.BLOB);
        else 
        {
            byte[] b;
            ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
            try {
                ImageIO.write(u.getImagem(), "jpg", bytesImg);
                bytesImg.flush();
            } catch (IOException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            b=bytesImg.toByteArray();
            try {
                bytesImg.close();
            } catch (IOException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            s.setBytes(7,b);
        }
        int r=s.executeUpdate();
        return (r>0);
    }
}
