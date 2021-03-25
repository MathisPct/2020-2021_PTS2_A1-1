/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox;



/**
 *
 * @author David
 */
public class MyButtonTech extends MyButton{
    
    public MyButtonTech(String name, MyStyle style) {
        super(name, style);
    }
    
    @Override
    public void setButton() {
        this.setOnAction((event) -> {
            System.out.println("Bouton technicien cliqué");
        });
    }
}
