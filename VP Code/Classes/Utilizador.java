package Classes;

import java.util.Vector;
import Classes.Produto;

public class Utilizador {
	private String _username;
	private String _passmd5;
	private int _classificacao;
	public Vector<Produto> _meusProds = new Vector<Produto>();
	public Vector<Produto> _wishlist = new Vector<Produto>();

	public void compra(Venda aV) {
		throw new UnsupportedOperationException();
	}

	public void licitar(Leilao aL, int aV) {
		throw new UnsupportedOperationException();
	}

	public void addProduto(Produto aP) {
		throw new UnsupportedOperationException();
	}

	public void removeProduto(Produto aP) {
		throw new UnsupportedOperationException();
	}

	public boolean reporta(Produto aP, String aUsername, String aJust) {
		throw new UnsupportedOperationException();
	}

	public void classifica(String aUsername, int aClassificacao) {
		throw new UnsupportedOperationException();
	}

	public Venda vende(Produto aP) {
		throw new UnsupportedOperationException();
	}

	public void leiloar(Produto aP, int aBase, int aTecto) {
		throw new UnsupportedOperationException();
	}
}