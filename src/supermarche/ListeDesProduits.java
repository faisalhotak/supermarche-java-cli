package supermarche;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author faisalhotak
 */

public class ListeDesProduits {
        
    ArrayList <Produit> listeProduits;
    
    public ListeDesProduits(){
        listeProduits = new ArrayList<>();
    }
    
    public void ajouterUnProduit(Produit P){
        listeProduits.add(P);
    }
    
    public void remplacerUnProduit(int idAncienProduit, Produit nouveau){      
        listeProduits.set(idAncienProduit, nouveau);
    }
    
    public void retirerUnProduit(int idProduit){
        listeProduits.remove(idProduit-1);

    }
    
    public boolean estVide(){
        return listeProduits.size() <= 0;
    }
    
    public void enregistrerLesProduits(Fichier f) throws IOException{
        f.ecrire(this.listeProduits);       
    }
    
    public void lireLesProduits(Fichier f) throws IOException, ClassNotFoundException{
        this.listeProduits = f.lire("liste des produits");
    }
    
    public LigneTicketCaisse vendreProduit(int id, int quantite){
        listeProduits.get(id-1).diminuerQuantite_produit(quantite);
        LigneTicketCaisse ltc = new LigneTicketCaisse(listeProduits.get(id-1), quantite, id);
        return ltc;
    }
    
    public void remettreProduitEnStock(int idProduit, int quantite){
        listeProduits.get(idProduit-1).augmenterQuantite_produit(quantite);
    }    
}