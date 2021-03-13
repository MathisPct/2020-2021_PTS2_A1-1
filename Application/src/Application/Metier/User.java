package Application.Metier;

/**
 * Représente un utilisateur du système. Peut être un technicien ou un chef de projet
 */
public class User {
	/**
	 * Identifiant unique de l'utilisateur
	 */
	private int ID;
	/**
	 * login (nom de connexion) de l'utilisateur
	 */
	private String login;
	/**
	 * valeur de hachage du mot de passe
	 */
	private String passwordHash;
	/**
	 * Nom de l'utilisateur
	 */
	private String lastName;
	/**
	 * prénom de l'utilisateur
	 */
	private String firstName;
	/**
	 * Indique si l'utilisateur est connecté
	 */
	private boolean isConnected = false;
	/**
	 * Indique si l'utilisateur est chef de projet ou non
	 */
	private boolean isChief = false;

	public User(int id) {
		this.ID = id;
	}

	/**
	 * Copie les données d'un utilisateur
	 * @param user l'utilisateur à recopier
	 */
	public void Copy(User user) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Efface les champs de l'utilisateur et passe à faux l'état "connecté"
	 */
	public void Disconnect() {
		throw new UnsupportedOperationException();
	}

	public void setID(int aID) {
		this.ID = aID;
	}

	public int getID() {
		return this.ID;
	}

	public void setLogin(String aLogin) {
		this.login = aLogin;
	}

	public String getLogin() {
		return this.login;
	}

	public void setPasswordHash(String aPasswordHash) {
		this.passwordHash = aPasswordHash;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setLastName(String aLastName) {
		this.lastName = aLastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setFirstName(String aFirstName) {
		this.firstName = aFirstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setIsConnected(boolean aIsConnected) {
		this.isConnected = aIsConnected;
	}

	public boolean isIsConnected() {
		return this.isConnected;
	}

	public void setIsChief(boolean aIsChief) {
		this.isChief = aIsChief;
	}

	public boolean isIsChief() {
		return this.isChief;
	}

	/**
	 * Fonction qui retourne l'user de la forme NOM Prenom
	 * @return chaine de caractère NOM Prénom
	 * @autor Mathis Poncet
	 */
	public String toString(){
		String res = "";
		res += this.lastName.toUpperCase();
		res += " " + this.firstName;
		return res;
	}
}