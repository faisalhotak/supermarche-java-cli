package supermarche;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author faisalhotak
 */

public class ListeDesTicketsCaisse {

    ArrayList <TicketCaisse> listeTicketCaisse;

    public ListeDesTicketsCaisse(){
        this.listeTicketCaisse = new ArrayList<>();
    }
          
    public void ajouterTicketCaisse(TicketCaisse TC){
        listeTicketCaisse.add(TC);
    }
    
    public void retirerUnTicketCaisse(int idTicketCaisse){
        listeTicketCaisse.remove(idTicketCaisse);
    }
    
    public boolean estVide(){
        return listeTicketCaisse.size() <= 0;
    }

    public void enregistrerLesTickets(Fichier f) throws IOException{
        f.ecrire(this.listeTicketCaisse);                    
    }
    
    public void lireLesTickets(Fichier f) throws IOException, ClassNotFoundException{
        this.listeTicketCaisse = f.lire("liste des tickets de caisse");
    }   
}
