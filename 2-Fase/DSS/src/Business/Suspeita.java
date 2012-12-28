package Business;

public class Suspeita {
	private Utilizador _queixoso;
	private Produto _p;
	private String _just;

public Suspeita(Utilizador u,Produto p,String j){
    _queixoso=u.clone();
    _p=p.clone();
    _just=j;
}

public Suspeita(Suspeita s){
    _queixoso=s.getQueix();
    _p=s.getProd();
    _just=s.getJust();
}

public Utilizador getQueix(){
    return _queixoso.clone();
}

public Produto getProd(){
    return _p.clone();
}

public String getJust(){
    return _just;
}

public void setQueixoso(Utilizador u){
    _queixoso=u.clone();
}

public void setProd(Produto p){
    _p=p.clone();
}

public void setJust(String j){
    _just=j;
}

public Suspeita clone(){
    return new Suspeita(this);
}





}