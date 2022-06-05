package supermarche;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author faisalhotak
 */

public class ListeDesEmployes {
        
    ArrayList <Employe> listeEmployes;
    
    public ListeDesEmployes(){
        listeEmployes = new ArrayList<>();
    }
    
    public void ajouterUnEmploye(Employe E){       
        listeEmployes.add(E);
    }
    
    public void remplacerUnEmploye(int idAncienEmploye, Employe nouveau){      
        listeEmployes.set(idAncienEmploye, nouveau);
    }
    
    public void retirerUnEmploye(int idEmploye){
        listeEmployes.remove(idEmploye-1);
    }
    
    public boolean estVide(){
        return listeEmployes.size() <= 0;
    } 

    public void enregistrerLesEmployes(Fichier f) throws IOException{
        f.ecrire(this.listeEmployes);            
    }
    
    public void lireLesEmployes(Fichier f) throws IOException, ClassNotFoundException{
        this.listeEmployes = f.lire("liste des employés");      
    }
}