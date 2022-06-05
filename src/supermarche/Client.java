package supermarche;

import java.io.Serializable;

/**
 *
 * @author faisalhotak
 */

public class Client extends Personne implements Serializable {

    private String date_inscription;

    public Client(String n, String p, String ru, String cp, String l, String nt, String di) {
        super(n, p, ru, cp, l, nt);

        this.date_inscription = di;
    }

    public String getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(String date_inscription) {
        this.date_inscription = date_inscription;
    }
}
