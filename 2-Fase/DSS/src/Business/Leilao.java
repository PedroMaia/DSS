package Business;

import Data.LeiloesDAO;
import Data.LicitacoesDAO;
import java.util.GregorianCalendar;
import java.util.List;

public class Leilao {
	
	private GregorianCalendar _dataFecho;
	private GregorianCalendar _dataLimitePagamento;
	private boolean _pago;
	private int _base;
	private int _tecto;
	private boolean _prodEnviado;
	private Produto p;
        private LicitacoesDAO licitacoes;
        int id;
        
        public Leilao(Produto pr, int base, int tecto)
        {
            p=pr;
            _base=base;
            _tecto=tecto;
            _prodEnviado=false;
            _pago=false;
            _dataLimitePagamento=null;
            GregorianCalendar now = new GregorianCalendar();
            now.add(GregorianCalendar.DAY_OF_MONTH,2);
            _dataFecho=now;
            id=LeiloesDAO.getNewId();
        }

	public void registaLicitacao(Utilizador aU, int aV) throws LeilaoFechadoException, BaixaLicitacaoException {
            
            
	}

	public boolean fechado() {
		GregorianCalendar hoje = new GregorianCalendar();
                return hoje.after(this._dataFecho);
	}
        
        public void regrideLeilao() {
            
        }
        
        public int getUltimaLicitacao()
        {
           
        }
}