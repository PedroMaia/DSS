package Business;

import Data.LicitacoesDAO;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class Leilao {
    
	private int id;
        private Utilizador leiloador;
	private Produto p;
        private GregorianCalendar dataLeilao;
	private GregorianCalendar _dataFecho;
	private GregorianCalendar _dataLimiteLeilao;
	private GregorianCalendar dataPagamento;
        private GregorianCalendar dataEnvioProduto;
	private float _base;
	private float _tecto;
        private LicitacoesDAO licitacoes;

    public Leilao(int id, Utilizador leiloador, Produto p, GregorianCalendar dataLeilao, GregorianCalendar _dataFecho, GregorianCalendar _dataLimiteLeilao, GregorianCalendar dataPagamento, GregorianCalendar dataEnvioProduto, float _base, float _tecto) {
        this.id = id;
        this.leiloador = leiloador;
        this.p = p;
        this.dataLeilao = dataLeilao;
        this._dataFecho = _dataFecho;
        this._dataLimiteLeilao = _dataLimiteLeilao;
        this.dataPagamento = dataPagamento;
        this.dataEnvioProduto = dataEnvioProduto;
        this._base = _base;
        this._tecto = _tecto;
        this.licitacoes=new LicitacoesDAO(id);
    }
        

    

    public Utilizador getLeiloador() {
        return leiloador;
    }

    

    public GregorianCalendar getDataLeilao() {
        return dataLeilao;
    }

    public GregorianCalendar getDataFecho() {
        return _dataFecho;
    }

    public GregorianCalendar getDataLimiteLeilao() {
        return _dataLimiteLeilao;
    }

    public GregorianCalendar getDataPagamento() {
        return dataPagamento;
    }

    public GregorianCalendar getDataEnvioProduto() {
        return dataEnvioProduto;
    }

    public float getBase() {
        return _base;
    }

    public float getTecto() {
        return _tecto;
    }

    public Produto getP() {
        return p;
    }

    public LicitacoesDAO getLicitacoes() {
        return licitacoes;
    }

    public int getId() {
        return id;
    }

    public void setDataLimiteLeilao(GregorianCalendar _dataLimiteLeilao) {
        this._dataLimiteLeilao = _dataLimiteLeilao;
    }
        
        

	public boolean registaLicitacao(Licitacao l) throws LeilaoFechadoException, BaixaLicitacaoException, SQLException {
            if(fechado()) throw new LeilaoFechadoException();
            if(l.getValor()<getUltimaLicitacao()) throw new BaixaLicitacaoException();
            return licitacoes.addLicitacao(l);
	}

	public boolean fechado() throws SQLException {
		GregorianCalendar hoje = new GregorianCalendar();
                return (hoje.after(this._dataFecho)||(licitacoes.getMaxLicitacao()>=_tecto));
	}
        
        
        
        public void regrideLeilao() {
            
        }
        
        public float getUltimaLicitacao() throws SQLException
        {
           return licitacoes.getMaxLicitacao();
        }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Leilao other = (Leilao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Leilao{" + "id=" + id + ", leiloador=" + leiloador + ", p=" + p + ", dataLeilao=" + dataLeilao + ", _dataFecho=" + _dataFecho + ", _dataLimiteLeilao=" + _dataLimiteLeilao + ", dataPagamento=" + dataPagamento + ", dataEnvioProduto=" + dataEnvioProduto + ", _base=" + _base + ", _tecto=" + _tecto + ", licitacoes=" + licitacoes + '}';
    }
        
        
        
}