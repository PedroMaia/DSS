package Business;

public class Suspeita {
	private Utilizador _queixoso;
	
	private String _just;

    public Suspeita(Utilizador _queixoso, String _just) {
        this._queixoso = _queixoso;
        
        this._just = _just;
    }

    public Utilizador getQueixoso() {
        return _queixoso;
    }

    public String getJust() {
        return _just;
    }

        


}