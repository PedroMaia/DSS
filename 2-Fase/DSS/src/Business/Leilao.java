package Business;

import Data.LicitacoesDAO;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class Leilao {

    public static final int ABERTO = 0;
    public static final int PENDENTE = 1;
    public static final int FECHADO = 2;
    public static final int CONCLUIDO = 3;
    public static final int EXPIRADO = 4;    
    private static int MAIOR_LICITACAO=5;
    private static int LICITACAO_BATIDA=6;
    
    public static final String[] ESTADOS={"Aberto","Pendente","Fechado","Concluido","Expirado", "Maior Licitação", "Licitação Batida"};
    
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
        this.licitacoes = new LicitacoesDAO(id);
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
        if (fechado()) {
            throw new LeilaoFechadoException();
        }
        if (l.getValor() < getUltimaLicitacao()) {
            throw new BaixaLicitacaoException();
        }
        return licitacoes.addLicitacao(l);
    }

    public boolean fechado() throws SQLException {
        GregorianCalendar hoje = new GregorianCalendar();
        return (hoje.after(this._dataFecho) || (licitacoes.getMaxLicitacao() >= _tecto));
    }

    public void regrideLeilao() {
    }

    public float getUltimaLicitacao() throws SQLException {
        float lic=licitacoes.getMaxLicitacao();
        if(lic>_base)
            return lic;
        else 
            return _base;
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

    public int getEstado() throws SQLException {
        if (dataEnvioProduto != null && dataPagamento != null) {
            return Leilao.CONCLUIDO;
        } else {
            if (!fechado()) {
                return Leilao.ABERTO;
            } else {
                if (licitacoes.getVencedor() == null) {
                    return Leilao.FECHADO;
                } else {
                    if (_dataLimiteLeilao.after(new GregorianCalendar())) {
                        return Leilao.PENDENTE;
                    } else {
                        return Leilao.EXPIRADO;
                    }
                }
            }
        }
    }
    
    public Utilizador getVencedor() throws SQLException
    {
        return licitacoes.getVencedor();
    }
    
    public float getMaxDeUser(Utilizador u) throws SQLException
    {
        return licitacoes.getMaiorDeUtilizador(u).getValor();
    }
    
    public int getEstadoLicitacao(Utilizador u) throws SQLException
    {
        if(fechado())
        {
            if(getVencedor().equals(u))
            {
                return getEstado();
            }
            else
                return Leilao.FECHADO;
        }
        else{
            if(getVencedor().equals(u))
                return Leilao.MAIOR_LICITACAO;
            else
                return Leilao.LICITACAO_BATIDA;
        }
    }
}