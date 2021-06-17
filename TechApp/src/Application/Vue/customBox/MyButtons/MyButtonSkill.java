/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyButtons;

import Application.Vue.customBox.MyButtons.MyButton;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Metier.Skill;
import Application.Metier.Tech;

/**
 *
 * @author David
 */
public class MyButtonSkill extends MyButton {
    private Tech tech;
    private Skill skill;
    
    public MyButtonSkill(String name, Tech tech, Skill skill, MyStyle style) {
        super(name, style);
        this.tech = tech;
        this.skill = skill;
    }
    
    @Override
    public void setButton() {
        this.setOnAction((event) -> {
            String txtBtn = "Action sur " + this.tech.getFirstName() + " " + this.tech.getLastName() + ": " + this.skill.getName() + " "+ this.skill.getLevel();
            System.out.println(txtBtn);
        });
    }
    
}
