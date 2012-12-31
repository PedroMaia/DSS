package Business;

import Data.SuspeitasDAO;
import java.awt.image.BufferedImage;

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
        
        
        
        
        
}