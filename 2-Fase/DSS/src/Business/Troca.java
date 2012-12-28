package Business;

public class Troca {
	private Utilizador _prop;
	private Utilizador _convidado;
	private Produto desejado;
	private Produto oferta;
	boolean aceite;
        boolean concluido;

public Troca(Utilizador u1,Utilizador u2,Produto des,Produto of,boolean a,boolean conc){
    _prop=u1.clone();
    _convidado=u2.clone();
    desejado=des.clone();
    oferta=of.clone();
    aceite=a;
    concluido=conc;
}

public Troca(Troca t){
    _prop=t.getProp();
    _convidado=t.getConv();
    desejado=t.getDesej();
    oferta=t.getOfert();
    aceite=t.getResult();
    concluido=t.getConc();
}

public Utilizador getProp(){
    return _prop.clone();
}

public Utilizador getConv(){
    return _convidado.clone();
}
        
public Produto getDesej(){
    return desejado.clone();
}        
   
public Produto getOfert(){
    return oferta.clone();
} 

public Boolean getResult(){
    return aceite;
} 

public Boolean getConc(){
    return concluido;
} 

public void setProp(Utilizador u){
    _prop=u.clone();
}

public void setConv(Utilizador u){
    _convidado=u.clone();
}

public void setDesej(Produto p){
    desejado=p.clone();
}

public void setOfert(Produto p){
    oferta=p.clone();
}

public void setResult(Boolean b){
    aceite = b;
}

public void setConc(Boolean b){
    concluido = b;
}

public Troca clone(){
    return new Troca(this);
}

}