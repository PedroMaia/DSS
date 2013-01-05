package Business;

import java.util.GregorianCalendar;

public class Venda {
    
    public static final int ABERTA=0;
    public static final int PENDENTE=1;
    public static final int CONCLUIDA=2;
    public static final int EXPIRADA=3;
    public static final String[] ESTADOS={"Aberta","Pendente","Concluida","Expirada"};

    private int id;
    private float _preco;
    private GregorianCalendar dataVenda;
    private GregorianCalendar _dataLimiteVenda;
    private GregorianCalendar dataEnvioProduto;
    private GregorianCalendar dataPagamento;
    private Produto _produto;
    private Utilizador vendedor;
    private Utilizador comprador;

    public Venda(int id, float _preco, GregorianCalendar dataVenda, GregorianCalendar _dataLimiteVenda, GregorianCalendar dataEnvioProduto, GregorianCalendar dataPagamento, Produto _produto, Utilizador vendedor, Utilizador comprador) {
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

    public float getPreco() {
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

    public void setDataLimiteVenda(GregorianCalendar _dataLimiteVenda) {
        this._dataLimiteVenda = _dataLimiteVenda;
    }

    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }

    

    public void cancelaVenda() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Venda other = (Venda) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", _preco=" + _preco + ", dataVenda=" + dataVenda + ", _dataLimiteVenda=" + _dataLimiteVenda + ", dataEnvioProduto=" + dataEnvioProduto + ", dataPagamento=" + dataPagamento + ", _produto=" + _produto + ", vendedor=" + vendedor + ", comprador=" + comprador + '}';
    }
    
    public int getEstado()
    {
        if(comprador==null) return Venda.ABERTA;
        else{
            if(dataEnvioProduto!=null&&dataPagamento!=null)
                return Venda.CONCLUIDA;
            else{
                if(_dataLimiteVenda.after(new GregorianCalendar()))
                    return Venda.PENDENTE;
                else
                    return Venda.EXPIRADA;
            }
        }
    }
}