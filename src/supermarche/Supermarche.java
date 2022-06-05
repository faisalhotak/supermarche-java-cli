package supermarche;

import java.io.IOException;
import java.util.Scanner;

// @author faisalhotak

public class Supermarche {
    
    static Scanner lectureClavier = new Scanner(System.in);
    static ListeDesProduits LPR = new ListeDesProduits();
    static ListeDesEmployes LEM = new ListeDesEmployes();
    static ListeDesClients LCL = new ListeDesClients();
    static ListeDesTicketsCaisse LTC = new ListeDesTicketsCaisse();    
    
    // Un fichier 'Supermarche.ser' sera créé à la fermeture du programme
    // Il contiendra les listes nécessaires à notre programme
    // Ces données sont en bytes, incompréhensibles à l'oeil nu par l'être humain
    static String fichierSupermarche = "fichier/Supermarche.ser";
    static Fichier F = new Fichier();

    public static void main(String[] args) throws IOException, ClassNotFoundException  {

        int choix;       
        
        ouvertureDesFichiers();
              
        do {
            System.out.println("----------------------------------");
            System.out.println("--------       MENU       --------");
            System.out.println("----------------------------------\n");
            System.out.println("****** PRODUIT ******");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Modifier un produit");
            System.out.println("3. Retirer un produit\n");
            
            System.out.println("****** PERSONNEL ******");
            System.out.println("4. Ajouter un employé");
            System.out.println("5. Modifier un employé");
            System.out.println("6. Retirer un employé\n");
            
            System.out.println("********** CLIENT **********");
            System.out.println("7. Ajouter un(e) client(e)");
            System.out.println("8. Modifier un(e) client(e)");
            System.out.println("9. Retirer un(e) client(e)\n");

            System.out.println("************* LISTING ************");
            System.out.println("10. Afficher la liste des produits");
            System.out.println("11. Afficher la liste des employés");           
            System.out.println("12. Afficher la liste des clients");
            System.out.println("13. Afficher la liste des tickets\n");
            
            System.out.println("************** RECHERCHE *************");
            System.out.println("14. Rechercher les tickets d'un client\n");
            
            System.out.println("************* VENTE **************");
            System.out.println("15. Procéder à une vente");
            System.out.println("16. Rapport de ventes des produits\n");
            
            System.out.println("******** QUITTER ********");
            System.out.println("17. Quitter le programme");
            System.out.println("\n-----------------------------------\n");
            
            System.out.print("Votre choix : ");
            choix = entierAuClavier();

            switch (choix)
            {
                case 1 : entrerUnProduit();                        
                         break;
                case 2 : modifierUnProduit();
                         break;
                case 3 : enleverUnProduit();
                         break;
                case 4 : entrerUnEmploye();
                         break;               
                case 5 : modifierUnEmploye();                        
                         break;
                case 6 : enleverUnEmploye();
                         break;
                case 7 : entrerUnClient();                        
                         break;
                case 8 : modifierUnClient();
                         break;
                case 9 : enleverUnClient();
                         break;
                         
                case 10 : afficherLesProduits();
                          break;
                case 11 : afficherLesEmployes();
                          break;
                case 12 : afficherLesClients();
                          break; 
                case 13 : afficherLesTicketsCaisse();
                          break;
                case 14 : rechercherUnTicket();
                          break;
                case 15 : procederVente();
                          break;
                case 16 : rapportDesVentes();
                          break;
                case 17 : fermetureDesFichiers();
                          System.exit(0);
                          break;
                default : System.out.println("Cette option n'existe pas !\n");
            }
       }while (choix != 17);
    }
    
    public static void ouvertureDesFichiers() throws IOException, ClassNotFoundException{
        
        if (F.ouvrir(fichierSupermarche, "L")) 
        {
            LPR.lireLesProduits(F);
            afficherErreurs(F.getErreur());
            LEM.lireLesEmployes(F);
            afficherErreurs(F.getErreur());
            LCL.lireLesClients(F);
            afficherErreurs(F.getErreur());
            LTC.lireLesTickets(F);
            afficherErreurs(F.getErreur());
            F.fermer();
        }
    }
    
    public static void fermetureDesFichiers() throws IOException{
        
        F.ouvrir(fichierSupermarche,"W");
        LPR.enregistrerLesProduits(F);
        afficherErreurs(F.getErreur());
        LEM.enregistrerLesEmployes(F);
        afficherErreurs(F.getErreur());
        LCL.enregistrerLesClients(F);
        afficherErreurs(F.getErreur());
        LTC.enregistrerLesTickets(F);
        afficherErreurs(F.getErreur());
        F.fermer();       
    }
    
    public static void entrerUnProduit(){
        
        String nom, desc_produit, code_barre;
        double prix, taux_tva;
        int quantite_produit;
        
        System.out.println("Veuillez entrer le nom du produit : ");
        nom = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la description du produit : ");
        desc_produit = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le code-barres du produit : ");
        code_barre = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le prix de vente du produit : ");
        prix = doubleAuClavier();
        System.out.println("Veuillez entrer le taux de tva du produit : ");
        taux_tva = doubleAuClavier();
        System.out.println("Veuillez entrer la quantité en stock du produit : ");
        quantite_produit = entierAuClavier();
        
        Produit nouveauProduit = new Produit(nom, desc_produit, code_barre, prix, taux_tva, quantite_produit);
        LPR.ajouterUnProduit(nouveauProduit);  
                       
        System.out.println("\n------------------------------");
        System.out.println(" Produit ajouté avec succès ! ");
        System.out.println("------------------------------\n");
    }
    
    public static void modifierUnProduit(){
        int id_produit, quantite_produit;
        String nom, desc_produit, code_barre;
        double prix, taux_tva;
        
        afficherLesProduits();
        
        if (!LPR.estVide()){
            int taille;
            taille = LPR.listeProduits.size();
            System.out.println("Quel produit souhaitez-vous modifier ?");
            System.out.print("Veuillez entrer l'id du produit : ");
            id_produit = entierAuClavier();
            
            while(id_produit > taille || id_produit < 1){
                System.out.println("Produit introuvable !\n");
                System.out.print("Recommencez : ");
                id_produit = entierAuClavier();
            }
            
            System.out.println("Veuillez entrer le nom du produit : ");
            nom = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la description du produit : ");
            desc_produit = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le code-barres du produit : ");
            code_barre = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le prix de vente du produit : ");
            prix = doubleAuClavier();
            System.out.println("Veuillez entrer le taux de tva du produit : ");
            taux_tva = doubleAuClavier();
            System.out.println("Veuillez entrer la quantité en stock du produit : ");
            quantite_produit = entierAuClavier();
            
            Produit nouveau = new Produit(nom, desc_produit, code_barre, prix, taux_tva, quantite_produit);
            LPR.remplacerUnProduit((id_produit-1), nouveau);

            System.out.println("\n------------------------------");
            System.out.println(" Produit modifié avec succès ! ");
            System.out.println("------------------------------\n");
        }       
    }
    
    public static void enleverUnProduit(){
        int id_produit;
        
        afficherLesProduits();
        
        if (!LPR.estVide()){
            int taille;
            taille = LPR.listeProduits.size();
            System.out.println("Quel produit souhaitez-vous retirer ?");
            System.out.print("Veuillez entrer l'id du produit : ");
            id_produit = entierAuClavier();

            while(id_produit > taille || id_produit < 1){
                System.out.println("Produit introuvable !\n");
                System.out.print("Recommencez : ");
                id_produit = entierAuClavier();
            }
            
            LPR.retirerUnProduit(id_produit);

            System.out.println("\n------------------------------");
            System.out.println(" Produit retiré avec succès ! ");
            System.out.println("------------------------------\n");
        }       
    } 
    
    public static void afficherLesProduits(){
        
        int nbProduits = LPR.listeProduits.size();
        
        if (nbProduits > 0){
            for (Produit tmp : LPR.listeProduits) {
            System.out.println("\n---------------------------------------------");
            System.out.println("------------|    ID : "+ (LPR.listeProduits.indexOf(tmp)+1) +"    |-----------------");
            System.out.println("---------------------------------------------\n");
            System.out.println("Nom : " + tmp.getNom());
            System.out.println("Description : " + tmp.getDesc_produit());
            System.out.println("Code-barres : " + tmp.getCode_barre());
            System.out.println("Prix : " + tmp.getPrix() + "€");
            System.out.println("Taux de T.V.A. : " + tmp.getTaux_tva() + "%");
            System.out.println("Quantité en stock : " + tmp.getQuantite_produit());
            }
            System.out.println("\n");
        }
        else{
            System.out.println("\n---------------------------------------------");
            System.out.println(" Il n'y a pas de produits dans cette liste ! ");
            System.out.println("---------------------------------------------\n");
        }
        
    }
    
    public static void afficherLesEmployes(){
        
        int nbEmployes = LEM.listeEmployes.size();
        
        if (nbEmployes > 0) for (Employe tmp : LEM.listeEmployes) {
            System.out.println("\n---------------------------------------------");
            System.out.println("---------------|      "+ (LEM.listeEmployes.indexOf(tmp)+1) +"      |---------------");
            System.out.println("---------------------------------------------\n");

            System.out.println("Nom : " + tmp.getNom());
            System.out.println("Prénom : " + tmp.getPrenom());
            System.out.print(tmp.getAdresse());
            System.out.println("Numéro de téléphone : " + tmp.getNum_telephone());
            System.out.println("Poste : " + tmp.getPoste());       
            System.out.println("Salaire : " + tmp.getSalaire() + "€");
            System.out.println("Date d'embauche : " + tmp.getDate_embauche() + "\n");
            System.out.println("\n");
        }
        else {
            System.out.println("\n---------------------------------------------");
            System.out.println("Il n'y a pas d'employés dans cette liste !");
            System.out.println("---------------------------------------------\n");
            
        }
    }
    
    public static void afficherLesClients(){
        
        int nbClients = LCL.listeClients.size();
        
        if (nbClients > 0) for (Client tmp : LCL.listeClients) {
            System.out.println("\n---------------------------------------------");
            System.out.println("---------------|      "+ (LCL.listeClients.indexOf(tmp)+1) +"      |---------------");
            System.out.println("---------------------------------------------\n");

            System.out.println("Nom : " + tmp.getNom());
            System.out.println("Prénom : " + tmp.getPrenom());
            System.out.print(tmp.getAdresse());
            System.out.println("Numéro de téléphone : " + tmp.getNum_telephone());
            System.out.println("Fidèle depuis : " + tmp.getDate_inscription());
            System.out.println("\n");
        }
        else {
            System.out.println("\n---------------------------------------------");
            System.out.println("Il n'y a pas d'employés dans cette liste !");
            System.out.println("---------------------------------------------\n");
            
        }
    }
    
    public static void afficherLesTicketsCaisse(){
        
        int nbTicketCaisse = LTC.listeTicketCaisse.size();
        
        if (nbTicketCaisse > 0){
            int index;
            for (TicketCaisse tmp : LTC.listeTicketCaisse) {
                index = LTC.listeTicketCaisse.indexOf(tmp);
                System.out.println("\n-----------------------------------------------------------------");
                System.out.println("-------------------------|      "+ (index+1) +"      |-------------------------");
                System.out.println("-----------------------------------------------------------------\n");
                afficherLesLignesTicketCaisse(tmp);
            }
        }
        else{
            System.out.println("\n----------------------------------------------------");
            System.out.println(" Il n'y a aucun ticket de caisse dans cette liste ! ");
            System.out.println("----------------------------------------------------\n");
        }
    }
    
    public static void afficherLesLignesTicketCaisse(TicketCaisse tc){
        
        int nbLigneTicketCaisse = tc.listeLigneTicketCaisse.size();
        
        if (nbLigneTicketCaisse > 0){
            System.out.println("\n-----------------------------------------------------------------");
            System.out.println("-----------|    Date : " + tc.getDateDeCreation() + "      |----------------");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("  Quantité |    Nom du produit    |     Prix    |    Total  ");
            System.out.println("-----------------------------------------------------------------");
            
            for (LigneTicketCaisse ltc : tc.listeLigneTicketCaisse){
                System.out.println(ltc.toString());
                System.out.println("-----------------------------------------------------------------");
            }
            System.out.println("                     TOTAL                      |     " + tc.getMontantTotal()+ "€");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("                     STATUT     |" + tc.isStatutPaye() + "|");
            System.out.println("-----------------------------------------------------------------");
            if (tc.getIdClient() == 0){
                System.out.println("                   ID CLIENT   |De passage|");
            }
            else{
                System.out.println("                   ID CLIENT    | " + tc.getIdClient() + " |");
            }
            System.out.println("-----------------------------------------------------------------");
            if (tc.getMode() == 1){
                System.out.println("                   MODE        |En espèces|");
            }
            else if (tc.getMode() == 2){
                System.out.println("                   MODE        |Par carte bancaire|");
            }
            System.out.println("-----------------------------------------------------------------\n");
        }
        else{
            System.out.println("\n----------------------------------------------------");
            System.out.println(" Il n'y a aucun produit encaissé sur ce ticket ! ");
            System.out.println("----------------------------------------------------\n");
        }
    }

    public static void entrerUnEmploye(){
        
        String nom, prenom, poste, rue, code_postal, localite, num_telephone, date_embauche;
        double salaire;
        
        System.out.println("Veuillez entrer le nom de l'employé : ");
        nom = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le prénom de l'employé : ");
        prenom = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le poste de l'employé : ");
        poste = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la rue et le numéro de rue de l'employé : ");
        rue = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le code postal de l'employé : ");
        code_postal = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la localité de l'employé : ");
        localite = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le numéro de téléphone de l'employé : ");
        num_telephone = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la date d'embauche de l'employé : ");
        date_embauche = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le salaire de l'employé : ");
        salaire = doubleAuClavier();
        
        Employe nouvelEmploye = new Employe(nom, prenom, poste, rue, code_postal, localite, num_telephone, salaire, date_embauche);
        LEM.ajouterUnEmploye(nouvelEmploye);  
        
        System.out.println("\n------------------------------------");
        System.out.println(" Employé(e) ajouté(e) avec succès ! ");
        System.out.println("------------------------------------\n");
    }
    
    public static void modifierUnEmploye(){
        
        int choix;
        String nom, prenom, poste, rue, code_postal, localite, num_telephone, date_embauche;
        double salaire;
        
        afficherLesEmployes();
        
        if (!LEM.estVide()){
            int taille;
            taille = LEM.listeEmployes.size();
            System.out.println("Quel(le) employé(e) souhaitez-vous modifier ?");
            System.out.print("Veuillez entrer l'id de l'employé(e) : ");
            choix = entierAuClavier();
            
            while(choix > taille || choix < 1){
                System.out.println("Employé(e) introuvable !\n");
                System.out.print("Recommencez : ");
                choix = entierAuClavier();
            }
            
            System.out.println("Veuillez entrer le nom de l'employé : ");
            nom = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le prénom de l'employé : ");
            prenom = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le poste de l'employé : ");
            poste = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la rue et le numéro de rue de l'employé : ");
            rue = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le code postal de l'employé : ");
            code_postal = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la localité de l'employé : ");
            localite = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le numéro de téléphone de l'employé : ");
            num_telephone = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la date d'embauche de l'employé : ");
            date_embauche = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le salaire de l'employé : ");
            salaire = doubleAuClavier();
            
            Employe nouveau = new Employe(nom, prenom, poste, rue, code_postal, localite, num_telephone, salaire, date_embauche);
            LEM.remplacerUnEmploye((choix-1), nouveau);

            System.out.println("\n-------------------------------");
            System.out.println(" Employé modifié avec succès ! ");
            System.out.println("-------------------------------\n");
        }       
    }
    
    public static void enleverUnEmploye(){
        
        int choix;
        
        afficherLesEmployes();
        
        if (!LEM.estVide()){
            int taille;
            taille = LEM.listeEmployes.size();
            System.out.println("Quel(le) employé(e) souhaitez-vous retirer ?");
            System.out.print("Veuillez entrer l'id de l'employé(e) : ");
            choix = entierAuClavier();
            
            while(choix > taille || choix < 1){
                System.out.println("Employé(e) introuvable !\n");
                System.out.print("Recommencez : ");
                choix = entierAuClavier();
            }

            LEM.retirerUnEmploye(choix);

            System.out.println("\n------------------------------------");
            System.out.println(" Employé(e) retiré(e) avec succès ! ");
            System.out.println("------------------------------------\n");
        }       
    }
    
    public static void entrerUnClient(){
        
        String nom, prenom, rue, code_postal, localite, num_telephone, date_inscription;
        
        System.out.println("Veuillez entrer le nom du client : ");
        nom = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le prénom du client : ");
        prenom = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la rue et le numéro de rue du client : ");
        rue = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le code postal du client : ");
        code_postal = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la localité du client : ");
        localite = lectureClavier.nextLine();
        System.out.println("Veuillez entrer le numéro de téléphone du client : ");
        num_telephone = lectureClavier.nextLine();
        System.out.println("Veuillez entrer la date à laquelle le client s'est inscrit : ");
        date_inscription = lectureClavier.nextLine();

        Client nouveauClient = new Client(nom, prenom, rue, code_postal, localite, num_telephone, date_inscription);
        LCL.ajouterUnClient(nouveauClient);  
        
        System.out.println("\n-----------------------------------");
        System.out.println(" Client(e) ajouté(e) avec succès ! ");
        System.out.println("-----------------------------------\n");
    }
    
    public static void modifierUnClient(){
        
        int choix;
        String nom, prenom, rue, code_postal, localite, num_telephone, date_inscription;
        
        afficherLesClients();
        
        if (!LCL.estVide()){
            int taille;
            taille = LCL.listeClients.size();
            System.out.println("Quel(le) client(e) souhaitez-vous modifier ?");
            System.out.print("Veuillez entrer l'id du client : ");
            choix = entierAuClavier();
            
            while(choix > taille || choix < 1){
                System.out.println("Client(e) introuvable !\n");
                System.out.print("Recommencez : ");
                choix = entierAuClavier();
            }
            
            System.out.println("Veuillez entrer le nom du client : ");
            nom = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le prénom du client : ");
            prenom = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la rue et le numéro de rue du client : ");
            rue = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le code postal du client : ");
            code_postal = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la localité du client : ");
            localite = lectureClavier.nextLine();
            System.out.println("Veuillez entrer le numéro de téléphone du client : ");
            num_telephone = lectureClavier.nextLine();
            System.out.println("Veuillez entrer la date à laquelle le client s'est inscrit : ");
            date_inscription = lectureClavier.nextLine();
            
            Client nouveau = new Client(nom, prenom, rue, code_postal, localite, num_telephone, date_inscription);
            LCL.remplacerUnClient((choix-1), nouveau);

            System.out.println("\n-------------------------------");
            System.out.println(" Client(e) modifié(e) avec succès ! ");
            System.out.println("-------------------------------\n");
        }       
    }
    
    public static void enleverUnClient(){
        
        int choix;
        
        afficherLesClients();
        
        if (!LCL.estVide()){
            int taille;
            taille = LCL.listeClients.size();
            System.out.println("Quel(le) client(e) souhaitez-vous retirer ?");
            System.out.print("Veuillez entrer l'id du client : ");
            choix = entierAuClavier();

            while(choix > taille || choix < 1){
                System.out.println("Client(e) introuvable !\n");
                System.out.print("Recommencez : ");
                choix = entierAuClavier();
            }
            
            LCL.retirerUnClient(choix);

            System.out.println("\n------------------------------------");
            System.out.println(" Client(e) retiré(e) avec succès ! ");
            System.out.println("------------------------------------\n");
        }       
    }
    
    // Recherche spécifiquement un ticket par rapport à un client donné
    public static void rechercherUnTicket(){
        
        if (!LTC.estVide() && !LCL.estVide()){
            int id_client, taille;
            boolean vide = true;
            taille = LCL.listeClients.size();
            afficherLesClients();
            System.out.println("Pour quel(le) client(e) souhaitez-vous rechercher (0 pour ceux de passage) ?");
            System.out.print("Veuillez entrer l'id du client : ");
            id_client = entierAuClavier();
            
            while(id_client > taille || id_client < 1){
                System.out.println("Client(e) introuvable !\n");
                System.out.print("Recommencez : ");
                id_client = entierAuClavier();
            }
            
            for (TicketCaisse tmp : LTC.listeTicketCaisse){
                if (tmp.getIdClient() == id_client){
                    afficherLesLignesTicketCaisse(tmp);
                    vide = false;
                }
            }
            
            if (vide){
                System.out.println("\n------------------------------------------------------");
                System.out.println(" Il n'y a pas de tickets enregistrés pour ce client ! ");
                System.out.println("------------------------------------------------------\n");
            }
        }
        else if (!LTC.estVide() && LCL.estVide()){
            System.out.println("\n---------------------------------------");
            System.out.println(" Il n'y a pas de clients enregistrés ! ");
            System.out.println("---------------------------------------\n");
        }
        else if (!LCL.estVide() && LTC.estVide()){
            System.out.println("\n---------------------------------------");
            System.out.println(" Il n'y a pas de tickets enregistrés ! ");
            System.out.println("---------------------------------------\n");
        }
        else{
            System.out.println("\n------------------------------------------------------");
            System.out.println(" Il n'y a pas de clients ni de tickets enregistrés ! ");
            System.out.println("------------------------------------------------------\n");
        }
    }
    
    // Second menu où l'on procède aux encaissements de produits et paiement de ceux-ci
    public static void procederVente(){
        int choix;
        TicketCaisse ticket = new TicketCaisse();
        
        do {
            System.out.println("\n********* VENTE *********");
            System.out.println("1. Encaisser des produits");
            System.out.println("2. Retirer des produits");
            System.out.println("\n****** CONSULTER ******");
            System.out.println("3. Consulter le ticket"); 
            System.out.println("\n****** FINALISER ******");
            System.out.println("4. Paiement du client");
            System.out.println("\n****** QUITTER ******");
            System.out.println("5. Annuler le ticket actuel (retour au programme principal)\n");
            System.out.print("Votre choix : ");
            
            choix = entierAuClavier();

            switch (choix)
            {
                case 1 : vendreLigneProduit(ticket);                        
                         break;
                case 2 : retirerLigneProduit(ticket);
                         break;
                case 3 : afficherLesLignesTicketCaisse(ticket);
                         break;
                case 4 : paiementClient(ticket);
                         break;
                case 5 : System.out.println("\n--------------------------------");
                         System.out.println("   Le ticket a été supprimé !   ");
                         System.out.println("--------------------------------");
                         System.out.println("\n--------------------------------");
                         System.out.println("Sortie du programme de vente !");
                         System.out.println("--------------------------------\n");
                         break;
                default : System.out.println("\nCette option n'existe pas !");
            }
       } while (choix != 4 && choix !=5);
    }
    
    // Encaissement d'un produit sur le ticket
    public static void vendreLigneProduit(TicketCaisse tc){
        
        int idProduit, quantite, max;
        LigneTicketCaisse ligneGeneree;
        afficherLesProduits();
        
        if(!LPR.estVide()){
            int taille;
            taille = LPR.listeProduits.size();
            do{
                System.out.println("Quel produit souhaitez-vous vendre ? (0 pour sortir)");
                System.out.print("Votre choix : ");
                idProduit = entierAuClavier();
                
                while(idProduit > taille || idProduit < 0){
                    System.out.println("Produit introuvable !\n");
                    System.out.print("Recommencez : ");
                    idProduit = entierAuClavier();
                }

                if (idProduit != 0){                    
                    max = LPR.listeProduits.get(idProduit-1).getQuantite_produit();

                    System.out.println("Veuillez indiquer la quantité voulue ? (0 pour sortir)");
                    System.out.print("Votre choix : ");
                    quantite = entierAuClavier();
                    
                    while(quantite > max || quantite < 0){
                        if (quantite > max) System.out.println("Quantité non disponible !\n");
                        else if (quantite < 0) System.out.println("La quantité demandée doit être positive !\n");
                        
                        System.out.print("Recommencez : ");
                        quantite = entierAuClavier();
                    }
                    
                    if (quantite != 0){
                        ligneGeneree = LPR.vendreProduit(idProduit, quantite);
                        tc.ajouterUneLigneTicketCaisse(ligneGeneree);
                        System.out.println("\n--------------------------------------------------");
                        System.out.println(" Produit ajouté au ticket de caisse avec succès ! ");
                        System.out.println("--------------------------------------------------");
                        afficherLesProduits();
                    }
                    else{
                        System.out.println("\n");
                    }
                }
            }while(idProduit != 0);
        }
        
    }
    
    // Retire une ligne de produits encaissés sur le ticket
    public static void retirerLigneProduit(TicketCaisse tc){
        
        int idLigneProduit;
        afficherLesLignesTicketCaisse(tc);
        
        if (!tc.estVide()){
            int taille;
            taille = tc.listeLigneTicketCaisse.size();
            do{
                System.out.println("Quelle ligne souhaitez-vous supprimer ? (0 pour sortir)");
                System.out.print("Votre choix : ");
                idLigneProduit = entierAuClavier();
                
                while(idLigneProduit > taille || idLigneProduit < 0){
                    System.out.println("Ligne de ticket introuvable !\n");
                    System.out.print("Recommencez : ");
                    idLigneProduit = entierAuClavier();
                }

                if (idLigneProduit != 0){
                    
                    int idProduit = tc.listeLigneTicketCaisse.get(idLigneProduit-1).getIdProduit();
                    int quantite = tc.listeLigneTicketCaisse.get(idLigneProduit-1).getQuantite();
                    LPR.remettreProduitEnStock(idProduit, quantite);
                    tc.retirerUneLigneTicketCaisse(idLigneProduit);                    
                    System.out.println("\n--------------------------------------------------");
                    System.out.println(" Ligne retirée du ticket de caisse avec succès ! ");
                    System.out.println("--------------------------------------------------\n");
                    
                    if (!tc.estVide()){
                        afficherLesLignesTicketCaisse(tc);
                    }
                    else{
                        System.out.println("---------------------------------------------");
                        System.out.println(" Tous les encaissements ont été retirés ! ");
                        System.out.println("---------------------------------------------\n");
                    }
                    
                }
            }while(idLigneProduit != 0 && !tc.estVide());      
        }   
    }
    
    // Permet au client de payer son ticket
    // Si on ne souhaite pas attribuer de clients, il sera attribué à un client de passage
    public static void paiementClient(TicketCaisse tc){
        
        if (!tc.estVide()){
            int mode;
            int id_client = 0;
            if (!LCL.estVide()){
                int taille;
                taille = LCL.listeClients.size();
                afficherLesClients();
                System.out.println("Quel est l'ID du client qui paie ? (0 si client de passage)");
                System.out.print("Votre choix : ");
                id_client = entierAuClavier();
                
                while(id_client > taille || id_client < 0){
                    System.out.println("Client(e) introuvable !\n");
                    System.out.print("Recommencez : ");
                    id_client = entierAuClavier();
                }  
            }
            else{
                System.out.println("\nAucun client n'a été retrouvé dans la base de données.");
                System.out.println("Attribution automatique comme client de passage !");
            }
            
            System.out.println("\n******* Commment paiera-t-il ? ******\n");
            System.out.println("1. En espèces");
            System.out.println("2. Par carte bancaire\n");
            System.out.print("Votre choix : ");
            mode = entierAuClavier();
            
            while(mode > 2 || mode < 1){
                System.out.println("Mode incorrect !\n");
                System.out.print("Recommencez : ");
                mode = entierAuClavier();
            }
            
            tc.setIdClient(id_client);
            tc.paiementTicketCaisse(mode);
            LTC.ajouterTicketCaisse(tc);
            
            System.out.println("\n---------------------------");
            System.out.println("Paiment du client réussi !");
            System.out.println("---------------------------\n");
        }
        else{
            System.out.println("\n--------------------------------------");
            System.out.println("Paiment échoué : le ticket est vide !");
            System.out.println("--------------------------------------\n");
        }
    }
    
    // Génère une liste comportant chaque produit et leurs ventes totales
    public static void rapportDesVentes(){
        
        int i, j, k, nbProduits, nbTickets, id_produit;
        
        nbProduits = LPR.listeProduits.size(); 
        nbTickets = LTC.listeTicketCaisse.size();
        
        if (nbProduits > 0 && nbTickets > 0){
            double totalVente, totalTout;
            totalTout = 0.0;
            System.out.println("\n--------------------------------------------");
            System.out.println("--------|   RAPPORT DES VENTES   |----------");
            System.out.println("--------------------------------------------");
            System.out.println(" ID |    Nom du produit      |    Ventes   ");
            System.out.println("--------------------------------------------");
            
            for (i = 0; i < nbProduits ; i++){
                totalVente = 0.0;
                id_produit = i;
                for (j = 0; j < LTC.listeTicketCaisse.size() ; j++){
                    for (k = 0; k < LTC.listeTicketCaisse.get(j).listeLigneTicketCaisse.size() ; k++){
                        if (LTC.listeTicketCaisse.get(j).listeLigneTicketCaisse.get(k).getIdProduit()-1 == id_produit){
                            totalVente += LTC.listeTicketCaisse.get(j).listeLigneTicketCaisse.get(k).getTotal();
                             
                        }
                    }
                }
                totalTout += totalVente;
                System.out.println("\n " + (i+1) + "     " + LPR.listeProduits.get(i).getNom() + "        " + totalVente + "€");
                System.out.println("\n--------------------------------------------");
            }
            System.out.println("             TOTAL           |    " + totalTout + "€");
            System.out.println("--------------------------------------------\n");
        }
        else
        {
            System.out.println("\n-------------------------------------");
            System.out.println(" Il n'y a aucune vente de produits ! ");
            System.out.println("-------------------------------------\n");
        }
    }
    
    // Vérifie si l'entrée au clavier qui suit est conforme à la demande (int)
    public static int entierAuClavier(){
        int entree;
        while (!lectureClavier.hasNextInt()){
            System.out.println("Erreur d'entrée, nombre(s) entier(s) requis !\n");
            System.out.print("Recommencez : ");
            lectureClavier.nextLine();
        }
        entree = lectureClavier.nextInt();
        lectureClavier.nextLine();
          
        return entree;
    }
    
    // Vérifie si l'entrée au clavier qui suit est conforme à la demande (double)
    public static double doubleAuClavier(){
        double entree;
        while (!lectureClavier.hasNextDouble()){
            System.out.println("Erreur d'entrée, nombre(s) réel(s) requis !\n");
            System.out.print("Recommencez : ");
            lectureClavier.nextLine();
        }
        entree = lectureClavier.nextDouble();
        lectureClavier.nextLine();
          
        return entree;
    } 
    
    // Dans le cas où il y aurait des erreurs à la lecture ou écriture du fichier
    public static void afficherErreurs(String erreur){
        if (erreur != null) System.out.println(erreur);
    }
}
