package supermarche;

import java.io.Serializable;

/**
 *
 * @author faisalhotak
 */

public class Personne implements Serializable{

    protected String nom, prenom, num_telephone;
    protected Adresse adresse;
    
    public Personne (String n, String p, String ru, String cp, String l, String nt) {       
        this.nom = n;
        this.prenom = p;       
        this.num_telephone = nt;
        this.adresse = new Adresse(ru, l, cp);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(String num_telephone) {
        this.num_telephone = num_telephone;
    }
}