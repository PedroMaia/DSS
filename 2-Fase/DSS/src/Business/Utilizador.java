package Business;

import java.util.List;
import java.util.Map;

public class Utilizador {

    private String _username;
    private String _passmd5;

    public String getPassmd5() {
        return _passmd5;
    }
    private Map<Utilizador, Integer> _classificacao;
    private List<Produto> _meusProds;
    private List<Produto> _wishlist;
    private List<Venda> minhasVendas;
    private List<Leilao> meusLeiloes;

    public void compra(Venda aV) {
        //throw new UnsupportedOperationException();
        aV.registaComprador(this);
        minhasVendas.add(aV);
    }

    public void licitar(Leilao aL, int aV) {
        //throw new UnsupportedOperationException();
        aL.registaLicitacao(this, aV);
        meusLeiloes.add(aL);
    }

    public void addProduto(Produto aP) {
        //throw new UnsupportedOperationException();
        _meusProds.add(aP);
    }

    public void removeProduto(Produto aP) {
        //throw new UnsupportedOperationException();
        _meusProds.remove(aP);
    }

    public boolean reporta(Produto aP, String aUsername, String aJust) {
        throw new UnsupportedOperationException();
    }

    public void classifica(Utilizador classificado, int aClassificacao) {
        //throw new UnsupportedOperationException();
        classificado._classificacao.put(this, aClassificacao);
    }

    public Venda vende(Produto aP) {
        throw new UnsupportedOperationException();
    }

    public void leiloar(Produto aP, int aBase, int aTecto) {
        throw new UnsupportedOperationException();
    }

    public int getClassificacao() {
        //throw new UnsupportedOperationException();
        int c = 0;
        for (int i : _classificacao.values()) {
            c += i;
        }
        return c / _classificacao.size();
    }
}