package Application.Metier;


public class Project {
	private String name;
	/**
	 * duree reelle du projet (si termine, sinon vide) exprimee en minutes
	 */
	private int finalDuration;
	private ProjectStatus status;
	private int id;
	/**
	 * Temps du projet en minutes
	 */
	private int estimatedDurationMinutes;
	/**
	 * Temps estime du projet en hours
	 */
	private float estimatedDurationHours;
	/**
	 * Temps estime du projet en jours (1 jour = 7h de travail)
	 */
	private float estimatedDurationDays;

        
	/**
	 * Si le projet est en attente, le demarre (status en cours)
	 * Si le projet est annulée ou terminé, lève une exception
	 */
	public void Start() throws LogicError {
		throw new UnsupportedOperationException();
	}

	/**
	 * Annule (si c'est possible) le projet en cours. Le projet ne doit être ni fini, ni annulé. Lève une exception en cas d'erreur.
	 */
	public void Cancel() throws LogicError {
		throw new UnsupportedOperationException();
	}

	public void setName(String aName) {
		this.name = aName;
	}

	public String getName() {
		return this.name;
	}

	public void setFinalDuration(int aFinalDuration) {
		this.finalDuration = aFinalDuration;
	}

	public int getFinalDuration() {
		return this.finalDuration;
	}

	public void setStatus(ProjectStatus aStatus) {
		this.status = aStatus;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

	public void setID(int aID) {
		this.id = aID;
	}

	public int getID() {
		return this.id;
	}

	public void setEstimatedDurationMinutes(int aEstimatedDurationMinutes) {
		this.estimatedDurationMinutes = aEstimatedDurationMinutes;
	}

	public int getEstimatedDurationMinutes() {
		return this.estimatedDurationMinutes;
	}

	public void setEstimatedDurationHours(float aEstimatedDurationHours) {
		this.estimatedDurationHours = aEstimatedDurationHours;
	}

	public float getEstimatedDurationHours() {
		return this.estimatedDurationHours;
	}

	public void setEstimatedDurationDays(float aEstimatedDurationDays) {
		this.estimatedDurationDays = aEstimatedDurationDays;
	}

	public float getEstimatedDurationDays() {
		return this.estimatedDurationDays;
	}
}