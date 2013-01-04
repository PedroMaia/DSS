/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Produto;
import Business.Utilizador;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

    public static int getNewId() throws SQLException {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select sidp.nextval from dual");
        ResultSet rs = s.executeQuery();
        rs.next();
        int res=rs.getInt(1);
        c.close();
        return res;
    }

    public Produto get(int idp) throws SQLException {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from produto where idp=?");
        s.setInt(1, idp);
        ResultSet rs = s.executeQuery();
        Produto res = null;
        if (rs.next()) {
            res = read(rs);
        }
        c.close();
        return res;
    }

    private Produto read(ResultSet rs) throws SQLException {
        int id = rs.getInt("idp");
        String nome = rs.getString("np");
        String des = rs.getString("dsp");
        String cat = rs.getString("ctg");
        BufferedImage i = null;
        try {
            i = ImageIO.read(new ByteArrayInputStream(rs.getBytes("imp")));
        } catch (IOException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Produto(id, nome, i, des, cat);
    }

    public List<Produto> getFromUser(Utilizador u) throws SQLException {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("select * from produto where dp=?");
        s.setString(1, u.getUsername());
        List<Produto> res = new ArrayList<Produto>();
        ResultSet rs = s.executeQuery();
        while (rs.next()) {
            res.add(read(rs));
        }
        c.close();
        return res;
    }

    public boolean add(Produto p, Utilizador u) throws SQLException {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("insert into produto values(?,?,?,?,?,?)");
        s.setInt(1, p.getId());
        s.setString(2, p.getNome());
        byte[] b = null;
        ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
        try {
            ImageIO.write(p.getImagem(), "jpg", bytesImg);
            bytesImg.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        b = bytesImg.toByteArray();
        s.setBytes(3, b);
        s.setString(4, u.getUsername());
        s.setString(5, p.getDescricao());
        s.setString(6, p.getCategoria());
        int res = s.executeUpdate();
        c.close();
        return (res>0);
    }

    public boolean update(Produto p, Utilizador u) throws SQLException {
        Connection c = DataConnection.getDataConnection();
        PreparedStatement s = c.prepareStatement("update produto set np=?, imp=?, dp=?, dsp=?, ctg=? where idp=?");
        s.setString(1, p.getNome());
        ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
        try {
            ImageIO.write(p.getImagem(), "jpg", bytesImg);
            bytesImg.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.setBytes(2, bytesImg.toByteArray());
        s.setString(3, u.getUsername());
        s.setString(4, p.getDescricao());
        s.setString(5, p.getCategoria());
        s.setInt(6, p.getId());
        int res = s.executeUpdate();
        return (res>0);
    }
}
