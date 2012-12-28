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
        int id;

	public void registaLicitacao(Utilizador aU, int aV) throws LeilaoFechadoException, BaixaLicitacaoException {
            Licitacao l = new Licitacao(aU,aV);
            licitacoes.add(l.clone());
            
	}

	public boolean fechado() {
		GregorianCalendar hoje = new GregorianCalendar();
                return hoje.after(this._dataFecho);
	}
        
        public void regrideLeilao() {
            licitacoes.remove(licitacoes.size()-1);
        }
        
        public int getUltimaLicitacao()
        {
           Licitacao l = licitacoes.get(licitacoes.size()-1);
           int i = l.getValor();
           return i;
        }
}