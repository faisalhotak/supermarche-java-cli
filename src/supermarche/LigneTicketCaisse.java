package supermarche;

import java.io.Serializable;


/**
 *
 * @author faisalhotak
 */

public class LigneTicketCaisse implements Serializable{

    private String nomProduit;
    private int quantite, idProduit;
    private double prixUnitaire, total;
    
    public LigneTicketCaisse(Produit p, int q, int id){
        
        this.quantite = q;
        this.nomProduit = p.getNom();
        this.prixUnitaire =  Math.round((p.getPrix()+(p.getPrix()*(p.getTaux_tva()/100.0)))*100.0) / 100.0 ;
        this.total = (Math.round(this.quantite * this.prixUnitaire * 100.0)) / 100.0;
        this.idProduit = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
    public String toString(){
        return "     " + this.quantite + "       " + this.nomProduit + "          " + this.prixUnitaire + "€        " + this.total + "€";
    }
}
