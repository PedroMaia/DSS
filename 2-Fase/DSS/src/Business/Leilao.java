package Business;

import java.util.GregorianCalendar;

public class Leilao {
	private boolean _fechado;
	private GregorianCalendar _dataFecho;
	private GregorianCalendar _dataLimitePagamento;
	private boolean _pago;
	private int _base;
	private int _tecto;
	private boolean _prodEnviado;
	public Produto _unnamed_Produto_;

	public void registaLicitacao(Utilizador aU, int aV) {
		throw new UnsupportedOperationException();
	}

	public boolean getFechado() {
		return this._fechado;
	}
}