package Business;

import java.util.GregorianCalendar;

public class Troca {
	private Utilizador _prop;
	private Utilizador _convidado;
	private Produto desejado;
	private Produto oferta;
	private GregorianCalendar dataProposta;
        private GregorianCalendar dataConfirmacao;
        private GregorianCalendar dataConclusao;
        private GregorianCalendar dataLimite;
        private int id;

    public Troca(Utilizador _prop, Utilizador _convidado, Produto desejado, Produto oferta, GregorianCalendar dataProposta, GregorianCalendar dataConfirmacao, GregorianCalendar dataConclusao, GregorianCalendar dataLimite, int id) {
        this._prop = _prop;
        this._convidado = _convidado;
        this.desejado = desejado;
        this.oferta = oferta;
        this.dataProposta = dataProposta;
        this.dataConfirmacao = dataConfirmacao;
        this.dataConclusao = dataConclusao;
        this.dataLimite = dataLimite;
        this.id = id;
    }

    public Utilizador getProp() {
        return _prop;
    }

    public Utilizador getConvidado() {
        return _convidado;
    }

    public Produto getDesejado() {
        return desejado;
    }

    public Produto getOferta() {
        return oferta;
    }

    public GregorianCalendar getDataProposta() {
        return dataProposta;
    }

    public GregorianCalendar getDataConfirmacao() {
        return dataConfirmacao;
    }

    public GregorianCalendar getDataConclusao() {
        return dataConclusao;
    }

    public GregorianCalendar getDataLimite() {
        return dataLimite;
    }

    public int getId() {
        return id;
    }

    
        
        
}