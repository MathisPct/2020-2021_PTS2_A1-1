package Application.Metier;

/**
 * La classe Skill représente une compétence
 * Les compétences sont attribuées aux techniciens
 */
public class Skill {
    /**
     * attribut de type chaine de caractère et qui est le nom de la compétence
     */
    private String name; //nom de la compétence
    /**
     * Attribut de type chaine de caractère qui est le niveau de compétence
     */
    private String level; //niveau de la compétence

    /**
     * Fonction qui retourne le nom de la compétence
     * @return valeur chaine de caractère qui est le nom de la compétence
     */
    public String getName() {
        String str = name;
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        return cap;
    }

    /**
     * Procédure qui permet de set le nom d'une compétence
     * @param name valeur chaine de caractère qui est le nom de la compétence
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fonction qui retourne le level de la compétence
     * @return valeur chaine de caractère qui est le level de la compétence
     */
    public String getLevel() {
        String str = level;
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        return cap;
    }

    /**
     * Procédure qui permet de set le level d'une compétence
     * @param level valeur chaine de caractère qui est le level de la compétence
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public String toString(){
        return "Nom: " + this.name + " Level: " + this.level + " ";
    }
}
