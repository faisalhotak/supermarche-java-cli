package supermarche;

import java.io.Serializable;

/**
 *
 * @author faisalhotak
 */

public class Employe extends Personne implements Serializable {
    
    private String poste, date_embauche;
    private double salaire;

    public Employe(String n, String p, String po, String r, String cp, String l, String nt, double s, String de) {
        super(n, p, r, cp, l, nt);
                
        this.poste = po;       
        this.salaire = s;
        this.date_embauche = de;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(String date_embauche) {
        this.date_embauche = date_embauche;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}
