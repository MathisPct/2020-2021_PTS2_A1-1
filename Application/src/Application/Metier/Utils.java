package Application.Metier;

import Application.Database.BadPasswordError;
import java.util.Scanner;

/**
 * Utilitaires généraux de la couche métier
 */
public class Utils {

	/**
	 * 
	 *  Fournit un hachage du mot de passe. L'algorithme est basique : on fait du HEX : on prend chaque caractère de la chaine d'entrée, et c'est le code hexa qui est stocké.
	 * Exemple : si le password est "AB1", alors le hach est "414231". le retour est bien une chaîne
	 *  
	 * @param aPass un mot de passe à "hacher"
	 * @return la valeur hachée du paramètre
	 */
	public static String HashPassword(String aPass) {
            Scanner sc = new Scanner(aPass);
            String str = sc.next();
            StringBuffer sb = new StringBuffer();
            //Converting string to character array
            char ch[] = str.toCharArray();
            for(int i = 0; i < ch.length; i++) {
               String hexString = Integer.toHexString(ch[i]);
               sb.append(hexString);
            }
            String result = sb.toString();
            return result;
           
        
        }              

	/**
	 * Indique si le mot de passe est d'une force suffisante
	 * Une force suffisante = Longueur comprise entre 6 et l'infini
         *                        Avec au moins une lettre, un chiffre, une majuscule, et aucun caractère alphanumérique XX 
         * @return true si le mot de passe est assez complexe
         * @throws exception si le mot de passe entré en paramètre est vide
	 */
	public static boolean IsPasswordSafe(String aPass) throws BadPasswordError {
            boolean Safe = false;
            boolean longueur = false;
            boolean minuscule = false;
            boolean majuscule = false;
            boolean chiffre = false;
            boolean carSpeciaux = false;
            int temp;
            int codeAscii;           
            int i;
            
            if(aPass.isEmpty()){
                throw new BadPasswordError("Le mot de passe est vide");
            }
            
            Scanner sc = new Scanner(aPass);
            String str = sc.next();
            char ch[] = str.toCharArray();  
            
            int [] ascii = new int [ch.length];
            
            
            
            //obtention des codes ASCII des caractères           
            for(i = 0; i < ch.length; i++) {
                char c = ch[i];
                codeAscii = (int) c;
                ascii [i]= codeAscii;
            }
            
            /**TEST pour afficher les valeurs en ascii
            *   for(i = 0; i < ch.length; i++) {
            *        System.out.println(ascii[i]);
            *   }
            **/
            
            //Validation de la bonne longueur du mot de passe
            if (ch.length >=6) {
                longueur = true;
            }               

            //Validation de la présence d'une minuscule
            temp = 0;
            for ( i=0;i<ascii.length;i++){
                if (ascii[i]>=97 && ascii[i]<=122) { 
                    temp = temp + 1;
                }
            
            }
            if (temp > 0){
                minuscule = true;
            }
            
            //Validation de la présence d'une majuscule
            temp = 0;
            for ( i=0;i<ascii.length;i++){
                if (ascii[i]>=65 && ascii[i]<=90){
                    temp = temp + 1;
                }
            }
            if (temp > 0){
                majuscule = true;
            }
            
            //Validation de la présence d'au moins un chiffre
            temp = 0;
            for (i=0 ; i<ascii.length ;i++){
                if (ascii[i]>=48 && ascii[i]<=57) {
                    temp = temp + 1;
                }
                if (temp > 0){
                    chiffre = true;
                }
            }

            //Validation de l'absence de caractères alphanumériques ou de caractères spéciaux
            temp = 0;
            for ( i=0;i<ascii.length;i++){
                if (ascii[i]>=32 && ascii[i]<=47) { 
                    temp = temp + 1;
                }
                if (ascii[i]>=58 && ascii[i]<=64) {
                    temp = temp + 1;
                }
                if (ascii[i]>=91 && ascii[i]<=96) {
                    temp = temp + 1;;
                }
                if (ascii[i]>=123 && ascii[i]<=255) {
                    temp = temp + 1;
                }
                if (temp > 0){
                    carSpeciaux = true;
                }
            }
            
            
            System.out.println(longueur);
            System.out.println(minuscule);
            System.out.println(majuscule);
            System.out.println(chiffre);
            System.out.println(carSpeciaux);
            
            if ((longueur == true)&&(minuscule == true)&&(majuscule == true)&&(chiffre == true)&&(carSpeciaux == false)){
                  Safe = true;
            }
            return Safe;
	}
}