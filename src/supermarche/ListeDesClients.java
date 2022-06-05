package supermarche;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author faisalhotak
 */

public class ListeDesClients {
        
    ArrayList <Client> listeClients;
    
    public ListeDesClients(){
        listeClients = new ArrayList<>();
    }
    
    public void ajouterUnClient(Client C){
        listeClients.add(C);
    }
    
    public void remplacerUnClient(int idAncienClient, Client nouveau){      
        listeClients.set(idAncienClient, nouveau);
    }
    
    public void retirerUnClient(int idClient){
        listeClients.remove(idClient-1);
    }
    
    public boolean estVide(){
        return listeClients.size() <= 0;
    }

    public void enregistrerLesClients(Fichier f) throws IOException{
        f.ecrire(this.listeClients);       
    }
    
    public void lireLesClients(Fichier f) throws IOException, ClassNotFoundException{
        this.listeClients = f.lire("liste des clients");
    }
}