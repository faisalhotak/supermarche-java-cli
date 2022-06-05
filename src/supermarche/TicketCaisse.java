package supermarche;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author faisalhotak
 */

public class TicketCaisse implements Serializable{

    ArrayList <LigneTicketCaisse> listeLigneTicketCaisse;
    private int idClient, mode;
    private String dateDeCreation;
    private double montantTotal;
    private boolean statutPaye;
    
    public TicketCaisse(){
        listeLigneTicketCaisse = new ArrayList<>();
        this.dateDeCreation = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.montantTotal = 0;
        this.statutPaye = false;
        this.idClient = 0;
        this.mode = 0;
    }

    public String getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(String dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public double getMontantTotal() {
        return Math.round(montantTotal*100.0) / 100.0;
    }

    public void setMontantTotal(double montant) {
        this.montantTotal = montant;
    }

    public void augmenterTotal(double montant){
        this.montantTotal += (Math.round(montant*100.0)) / 100.0;
    }
    
    public void diminuerTotal(double montant){
        this.montantTotal -= (Math.round(montant*100.0)) / 100.0;
    }
     
    public String isStatutPaye() {
        if (this.statutPaye == false){
            return "Non payé";
        }
        return "Payé";
    }

    public void setStatutPaye(boolean statutPaye) {
        this.statutPaye = statutPaye;
    }
    
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void ajouterUneLigneTicketCaisse(LigneTicketCaisse ltc){      
        augmenterTotal(ltc.getTotal());
        listeLigneTicketCaisse.add(ltc);        
    }
    
    public void retirerUneLigneTicketCaisse(int idLigneTicketCaisse){
        diminuerTotal(listeLigneTicketCaisse.get(idLigneTicketCaisse-1).getTotal());
        listeLigneTicketCaisse.remove(idLigneTicketCaisse-1);
    }
    
    public void paiementTicketCaisse(int id){
        this.setStatutPaye(true);
        this.mode = id;
    }
    
    public boolean estVide(){
        return listeLigneTicketCaisse.size() <= 0;
    }
    
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
