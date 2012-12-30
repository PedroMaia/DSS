/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Produto;
import Business.Utilizador;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Cesar
 */
public class ProdutosDAO {
    
    
    public static int getNewId() throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s=c.prepareStatement("select sidp.nextval from dual");
        ResultSet rs = s.executeQuery();
        rs.next();
        rs.close();
        c.close();
        return rs.getInt(1);
    }

    public Produto get(int idp) throws SQLException {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from produto where idp=?");
        s.setInt(1,idp);
        ResultSet rs = s.executeQuery();
        Produto res=null;
        if(rs.next())
        {
            res=read(rs);
        }
        c.close();
        return res;
    }
    
    private Produto read(ResultSet rs) throws SQLException
    {
        int id=rs.getInt("idp");
        String nome = rs.getString("np");
        String des=rs.getString("dsp");
        String cat=rs.getString("ctg");
        BufferedImage i=null;
        try {
            i = ImageIO.read(new ByteArrayInputStream(rs.getBytes("imp")));
        } catch (IOException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Produto(id, nome, i, des, cat);
    }
    
    public List<Produto> getFromUser(Utilizador u) throws SQLException
    {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from produto where dp=?");
        s.setString(1,u.getUsername());
        List<Produto> res=new ArrayList<Produto>();
        ResultSet rs =s.executeQuery();
        while(rs.next())
        {
            res.add(read(rs));
        }
        c.close();
        return res;
    }
}
