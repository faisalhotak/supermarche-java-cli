package supermarche;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author faisalhotak
 */

public class Fichier {

    private ObjectOutputStream ofW;
    private ObjectInputStream ofR;
    private char mode;
    private String erreur;

    public boolean ouvrir(String nomDuFichier, String s) {
        
        try{           
            mode = (s.toUpperCase()).charAt(0);
            
            if ((mode == 'R') || (mode == 'L')){
                ofR = new ObjectInputStream(new FileInputStream(nomDuFichier));
            }
            else if ((mode == 'W')  || (mode == 'E')){
                ofW = new ObjectOutputStream(new FileOutputStream(nomDuFichier));
            }
            
            return true;
        }
        catch (IOException e){
            return false;
        }
    }

    public void ecrire (ArrayList a) throws IOException{
        if (a!= null)
        {
            ofW.writeObject(a);
        }
        else
        {
            this.erreur = "Erreur : Pas de donnés présentes !";
        }
    }

    public ArrayList lire(String nomListe) {
        
        try{
            ArrayList a = (ArrayList) ofR.readObject();
            return a;           
        }
        
        catch(IOException e) {this.erreur = "Erreur de lecture au niveau de la " + nomListe + " ! ";}
        
        catch(ClassNotFoundException e){
            this.erreur = "Le fichier n'est pas au bon format !";
        }
        return null;
    }

    public void fermer() throws IOException{
        if ((mode == 'R') || (mode == 'L')) ofR.close();
        else if ((mode == 'W') || (mode == 'E')) ofW.close();
    }
    
    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    
}
