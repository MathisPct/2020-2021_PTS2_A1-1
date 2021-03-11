/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Metier;

/**
 *
 * @author math7
 */
public class Person {
    private int id;
    private String lastName;
    private String firstname;
    private String phone;
    private String email;
    private String adresse;
    private Gender gender;
    
    
    /**
     * Constructeur par initialisation de la personne
     * @param id clé primaire représentant la personne
     */
    public Person(int id){
        this.id = id;
    }
    
    /**
     * Constructeur par copie
     * @param p modèle d'une personne 
     */
    public Person(Person p){
        this.id = p.id;
        this.firstname = p.firstname;
        this.lastName = p.lastName;
        this.phone = p.phone;
        this.email = p.email;
        this.adresse = p.adresse;
        this.gender = p.gender;
    }
    
    public void setLastName(String value){
        this.lastName = value;
    }
    
    public void setFirstName(String value){
        this.firstname = value;
    }
    
    public void setPhone(String value){
        this.phone = value;
    }
    
    public void setEmail(String value){
        this.email = value;
    }
    
    public void setAdresse(String value){
        this.adresse = value;
    }
    
    public void setGender(Gender value){
        this.gender = value;
    }
    
    /**
     * Fonction d'affichage d'une personen
     * @return NOM Prénom
     */
    @Override
    public String toString()
    {
        String last = this.lastName.toUpperCase();
        if(this.firstname!=null && this.firstname.length()>0)
        {
            String first = this.firstname.substring(0,1).toUpperCase()+this.firstname.substring(1).toLowerCase();
            last += " "+first;
        }
        return last;
    }
    
}
