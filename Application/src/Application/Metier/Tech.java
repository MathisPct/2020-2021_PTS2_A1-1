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
	 * /**
	 * * lien vers un utilisateur de type technicien
	 * * /
	 *
	 * @param id
	 */
	public Tech(int id) {
		super(id);
	}

	public void AddSkill(Skill s){
		throw new UnsupportedOperationException();
	}

	/**
	 * Demande la liste des compétences d'un techniciens, avec leur niveau
	 * @return un ensemble de compétences
	 */
	public ArrayList<Skill> GetSkills() {
		throw new UnsupportedOperationException();
	}
}