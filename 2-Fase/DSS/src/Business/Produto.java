package Business;

import Data.SuspeitasDAO;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.List;

public class Produto {

    private int _id;
    private String _nome;
    private BufferedImage _imagem;
    private String descricao;
    private String categoria;
    private SuspeitasDAO suspeitas;

    public Produto(int _id, String _nome, BufferedImage _imagem, String descricao, String categoria) {
        this._id = _id;
        this._nome = _nome;
        this._imagem = _imagem;
        this.descricao = descricao;
        this.categoria = categoria;
        suspeitas = new SuspeitasDAO(_id);
    }

    public int getId() {
        return _id;
    }

    public String getNome() {
        return _nome;
    }

    public BufferedImage getImagem() {
        return _imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this._id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this._id != other._id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "_id=" + _id + ", _nome=" + _nome + ", _imagem=" + _imagem + ", descricao=" + descricao + ", categoria=" + categoria + ", suspeitas=" + suspeitas + '}';
    }

    public boolean addSuspeita(Suspeita s) throws SQLException {
        return suspeitas.add(s);
    }
    
    public boolean semelhante(Produto p)
    {
        return this._nome.matches(p.getNome())||p.getNome().matches(this._nome);
    }
    
    public boolean existeSemelhante(List<Produto> ps)
    {
        for(Produto p:ps)
        {
            if(semelhante(p))
                return true;
        }
        return false;
    }
    
}