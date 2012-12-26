package Business;

import java.util.GregorianCalendar;

public class Leilao {
	
	private GregorianCalendar _dataFecho;
	private GregorianCalendar _dataLimitePagamento;
	private boolean _pago;
	private int _base;
	private int _tecto;
	private boolean _prodEnviado;
	public Produto p;

	public void registaLicitacao(Utilizador aU, int aV) {
		throw new UnsupportedOperationException();
	}

	public boolean fechado() {
		GregorianCalendar hoje = new GregorianCalendar();
                return hoje.after(this._dataFecho);
	}
        
        public void regrideLeilao() {
            throw new UnsupportedOperationException();
        }
}