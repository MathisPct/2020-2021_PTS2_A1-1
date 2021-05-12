/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyButtons;

import Application.Vue.customBox.MyButtons.MyButton;
import Application.Vue.customBox.MyStyles.MyStyle;
import Application.Metier.Tech;



/**
 *
 * @author David
 */
public class MyButtonTech extends MyButton{
    private Tech tech;
    
    public MyButtonTech(String name, Tech tech, MyStyle style) {
        super(name, style);
        this.tech = tech;
    }
    
    @Override
    public void setButton() {
        this.setOnAction((event) -> {
            String txtTech = "Action sur " + this.tech.getFirstName() + " " + this.tech.getLastName();
            System.out.println(txtTech);
        });
    }
}
