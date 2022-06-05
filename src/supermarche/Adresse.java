package supermarche;

import java.io.Serializable;

/**
 *
 * @author faisalhotak
 */

public class Adresse implements Serializable{

    protected String rue, code_postal, localite;
    
    public Adresse(String ru, String cp, String lo){
        this.rue = ru;
        this.code_postal = cp;
        this.localite = lo;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }
    
    @Override
    public String toString(){
        return "Rue : " + this.rue + "\nCode postal : " + this.code_postal + "\nLocalité : " + this.localite + "\n";
    }
    
}
