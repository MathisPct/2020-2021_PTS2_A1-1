
        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

/**
 * Fonctions utiles pour la DAO
 * @author David
 */
public class UtilsDao {
        
    public static String singleQuoteFixer(String chaine) {
        char c;
        String newChaine = "";
        char singleQuote = ((char) 39);      
        for (int i=0; i<chaine.length(); i++) {
            c = chaine.charAt(i);
            if(c == singleQuote) {
                newChaine += singleQuote;
                newChaine += singleQuote;
            } else {
                newChaine += chaine.charAt(i);
            }
        }
        return newChaine;
    }


    public static String onlyNumbers(String chaine) {
        String newChaine = "";
        char c;
        for (int i=0; i<chaine.length(); i++) {
            c = chaine.charAt(i);
            if(Character.isDigit(chaine.charAt(i))){
                newChaine += c;
            }
        }      
        return newChaine;    
    }
}
