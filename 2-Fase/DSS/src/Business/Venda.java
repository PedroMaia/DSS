package Business;

import Data.VendasDAO;
import java.util.GregorianCalendar;

public class Venda {
        int id;
	private float _preco;
        private GregorianCalendar dataVenda;
	private GregorianCalendar _dataLimiteVenda;
	private GregorianCalendar dataEnvioProduto;
        private GregorianCalendar dataPagamento;
	private Produto _produto;
        private Utilizador vendedor;
        private Utilizador comprador;

    

        
        
    public Venda(int id, int _preco, GregorianCalendar dataVenda, GregorianCalendar _dataLimiteVenda, GregorianCalendar dataEnvioProduto, GregorianCalendar dataPagamento, Produto _produto, Utilizador vendedor, Utilizador comprador) {
        this.id = id;
        this._preco = _preco;
        this.dataVenda = dataVenda;
        this._dataLimiteVenda = _dataLimiteVenda;
        this.dataEnvioProduto = dataEnvioProduto;
        this.dataPagamento = dataPagamento;
        this._produto = _produto;
        this.vendedor = vendedor;
        this.comprador = comprador;
    }

    public int getId() {
        return id;
    }

    public int getPreco() {
        return _preco;
    }

    public GregorianCalendar getDataVenda() {
        return dataVenda;
    }

    public GregorianCalendar getDataLimiteVenda() {
        return _dataLimiteVenda;
    }

    public GregorianCalendar getDataEnvioProduto() {
        return dataEnvioProduto;
    }

    public GregorianCalendar getDataPagamento() {
        return dataPagamento;
    }

    public Produto getProduto() {
        return _produto;
    }

    public Utilizador getVendedor() {
        return vendedor;
    }

    public Utilizador getComprador() {
        return comprador;
    }

    
        
        

	public void registaComprador(Object aUtilizador_u) {
		throw new UnsupportedOperationException();
	}

	public void cancelaVenda() {
		throw new UnsupportedOperationException();
	}
}