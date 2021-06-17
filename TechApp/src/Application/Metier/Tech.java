package Application.Metier;

import java.util.ArrayList;
import Application.Metier.Skill;

/**
 * le technicien est un utilisateur
 */
public class Tech extends User{
	/**
	 * /**
	 *  * un technicien va lister un certain nombre de compétences
	 *  * /
	 */
	private ArrayList<Skill> skills = new ArrayList<>();
        
        /**
         * cout horaire du technicien à l'heure
         */
        private float coutHoraire;
        
        /**
         * grade du technicien (junior, partner, senior)
         */
        private String grade;

	/**
	 * /**
	 * * lien vers un utilisateur de type technicien
	 * * /
	 *
	 * @param id
	 */
	public Tech(int id) {
		super(id);
	}
        
        
        /**
	 * Ajoute une compétence à la liste de compétence d'un techniciens 
         * @param s, une compétence contenant un nom et un niveau
         * @author Lucas
	 */
	public void AddSkill(Skill s){
            skills.add(s);
	}

	/**
	 * Demande la liste des compétences d'un techniciens, avec leur niveau
	 * @return un ensemble de compétences
         * @author Lucas
	 */
	public ArrayList<Skill> GetSkills() {
            return skills;
        }
        
        public float getCoutHoraire() {
            return coutHoraire;
        }

        public void setCoutHoraire(float coutHoraire) {
            this.coutHoraire = coutHoraire;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }  
}