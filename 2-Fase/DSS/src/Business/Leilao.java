package Business;

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
        private List<Licitacao> licitacoes;

	public void registaLicitacao(Utilizador aU, int aV) throws LeilaoFechadoException, BaixaLicitacaoException {
		//throw new UnsupportedOperationException();
            if(fechado()) throw new LeilaoFechadoException();
            if (aV<getUltimaLicitacao()) throw new BaixaLicitacaoException();
            Licitacao l = new Licitacao(aU,aV);
            licitacoes.add(l);
	}

	public boolean fechado() {
		GregorianCalendar hoje = new GregorianCalendar();
                return hoje.after(this._dataFecho);
	}
        
        public void regrideLeilao() {
            throw new UnsupportedOperationException();
        }
        
        public int getUltimaLicitacao()
        {
            return licitacoes.get(licitacoes.size()).getValor();
        }
}