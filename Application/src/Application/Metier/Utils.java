package Application.Metier;

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
		throw new UnsupportedOperationException();
	}

	/**
	 * Indique si le mot de passe est d'une force suffisante
	 * @return true si le mot de passe est assez complexe
	 */
	public static boolean IsPasswordSafe(String aPass) {
		throw new UnsupportedOperationException();
	}
}