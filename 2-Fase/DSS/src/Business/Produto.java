package Business;

import java.awt.image.BufferedImage;

public class Produto {
	private int _id;
	private String _nome;
	private BufferedImage _imagem;
        
        
        
        public Produto(int id,String nome,BufferedImage imagem){
            _id=id;
            _nome=nome;
            _imagem=imagem;
        }
        
        public Produto(Produto p){
            _id=p.getID();
            _nome=p.getNome();
            _imagem=p.getImagem();
        }
        
        public int getID(){
            return _id;
        }
        
        public String getNome(){
            return _nome;
        }
        
        public BufferedImage getImagem(){
            return _imagem;
        }
        
        public void setNome(String nome){
            _nome=nome;
        }
        
        public Produto clone(){
            return new Produto(this);
        }
        
}