package supermarche;

import java.io.Serializable;

/**
 *
 * @author faisalhotak
 */

public class Produit implements Serializable{
    
    private String nom, desc_produit, code_barre;
    private double prix, taux_tva;
    private int quantite_produit;

    public Produit(String n, String dp, String cb, double p, double tt, int qp) {
        this.nom = n;
        this.desc_produit = dp;
        this.code_barre = cb;
        this.prix = p;
        this.taux_tva = tt;
        this.quantite_produit = qp;
    }
    
    public String getNom(){
        return nom;
    }
    
    public void setNom(String nom){
        this.nom=nom;
    }
    
    public String getDesc_produit(){
        return desc_produit;
    }
    
    public void setDesc_produit(String desc_produit){
        this.desc_produit=desc_produit;
    }
    
    public String getCode_barre(){
        return code_barre;
    }
    
    public void setCode_barre(String code_barre){
        this.code_barre=code_barre;
    }

    public double getPrix(){
        return prix;
    }

    public void setPrix(double prix){
        this.prix=prix;
    }
    
    public double getTaux_tva(){
        return taux_tva;
    }
    
    public void setTaux_tva(double taux_tva){
        this.taux_tva=taux_tva;
    }
        
    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }
    
    public void diminuerQuantite_produit(int q){
        this.quantite_produit -= q;
    }
    
    public void augmenterQuantite_produit(int q){
        this.quantite_produit += q;
    }
}
