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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this._queixoso != null ? this._queixoso.hashCode() : 0);
        hash = 97 * hash + (this._just != null ? this._just.hashCode() : 0);
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
        final Suspeita other = (Suspeita) obj;
        if (this._queixoso != other._queixoso && (this._queixoso == null || !this._queixoso.equals(other._queixoso))) {
            return false;
        }
        if ((this._just == null) ? (other._just != null) : !this._just.equals(other._just)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Suspeita{" + "_queixoso=" + _queixoso + ", _just=" + _just + '}';
    }

        


}