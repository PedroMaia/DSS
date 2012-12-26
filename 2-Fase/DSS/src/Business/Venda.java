package Business;

import java.util.GregorianCalendar;

public class Venda {
	private int _preco;
	private boolean _pago;
	private GregorianCalendar _dataLimitePagamento;
	private boolean _prodEnviado;
	private Produto _produto;
        private Utilizador vendedor;
        private Utilizador comprador;

	public void registaComprador(Object aUtilizador_u) {
		throw new UnsupportedOperationException();
	}

	public void cancelaVenda() {
		throw new UnsupportedOperationException();
	}
}