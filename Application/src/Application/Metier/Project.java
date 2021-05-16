package Application.Metier;

import java.util.ArrayList;



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
    private String client;
    private ArrayList<Activity> activities;
    private ArrayList<Material> materials;
    
    public Project(){
        activities = new ArrayList();
        materials = new ArrayList();
    }

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

    public String getStatusString() {
        String res = "";
        switch(status) {
            case ENDED : res = "Terminé"; 
                break;
            case WAITING : res = "En attente"; 
                break;
            case WORKING : res = "En cours"; 
                break;
            case CANCELED : res = "Annulé"; 
                break;
            default : res = "undefined" ; 
                break;
        }
        return res;
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
            this.estimatedDurationMinutes = Math.round(aEstimatedDurationHours*60);
    }

    public float getEstimatedDurationHours() {
            return this.estimatedDurationMinutes/60;
    }

    public void setEstimatedDurationDays(float aEstimatedDurationDays) {
            this.estimatedDurationMinutes = Math.round(aEstimatedDurationDays*24*60);
    }

    public float getEstimatedDurationDays() {
            return (this.estimatedDurationMinutes/60)/24;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void addActivity(Activity a){
        activities.add(a);
    }

    public void addMaterial(Material m){
        materials.add(m);
    }
    
    public int getId() {
        return id;
    }
    

    public String activitiesToString() {
        String res = "";
        for(int i=0; i< activities.size(); i++){
            res += activities.get(i).getSummary();
            res += " " + activities.get(i).getDetails();
            res += " " + activities.get(i).getDuration();
            res += " " + activities.get(i).getEndDate();
            res += " " + activities.get(i).getStartDate();
            res += " " + activities.get(i).getStatusAsString();
            res += " " + activities.get(i).getType();            
            res += " | ";
        }
        return res;
    }

    public String materialsToString() {
        String res = "";
        for(int i=0; i< materials.size(); i++){
            res += materials.get(i).getName();
            res += " " + materials.get(i).getQuantity();
            res += " " + materials.get(i).getPrice();
            res += " " + materials.get(i).getDeliveryDate();
            res += " | ";
        }
        return res;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public int getNbActivities(ActivityStatus as){
       int res =0;
       for (Activity a : activities){
           if(a.getStatus() == as){
               res++;
           }
       }
       return res;
    }
    
}