package educacion.trax.quicktrade2;

/**
 * Created by Pepeg on 11/01/2019.
 */

public class Favorito {
    private String Nombre;

    public Favorito() {
    }

    public Favorito(String nom) {
        this.Nombre = nom;
    }

    public String getNom() {
        return Nombre;
    }

    public void setNom(String key) {
        this.Nombre = key;
    }
}
