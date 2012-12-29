package Business;

import Data.LicitacoesDAO;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class Leilao {
	
        private GregorianCalendar dataLeilao;
	private GregorianCalendar _dataFecho;
	private GregorianCalendar _dataLimiteLeilao;
	private GregorianCalendar dataPagamento;
        private GregorianCalendar dataEnvioProduto;
	private float _base;
	private float _tecto;
	
	private Produto p;
        private LicitacoesDAO licitacoes;
        int id;

    public Leilao(int id, GregorianCalendar dataLeilao, GregorianCalendar _dataFecho, int _base, int _tecto, Produto p) throws SQLException {
        this.id=id;
        this.dataLeilao = dataLeilao;
        this._dataFecho = _dataFecho;
        this._base = _base;
        this._tecto = _tecto;
        this.p = p;
        licitacoes=new LicitacoesDAO(this.id);
    }
        
        

	public void registaLicitacao(Utilizador aU, int aV) throws LeilaoFechadoException, BaixaLicitacaoException {
            
            
	}

	public boolean fechado() {
		GregorianCalendar hoje = new GregorianCalendar();
                return hoje.after(this._dataFecho);
	}
        
        public void regrideLeilao() {
            
        }
        
        public float getUltimaLicitacao() throws SQLException
        {
           return licitacoes.getMaxLicitacao();
        }
}