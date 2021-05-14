/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Vue.customBox.MyStyles;

/**
 *
 * @author David
 */
public class MyStyleOrange extends MyStyle{
    
    public MyStyleOrange(String fontName) {
        super(fontName);
        initStyleColor();
    }

    @Override
    public void initStyleColor() {
        super.setColorBase("#FFFFFF");
        super.setColorBaseBar("#BCBECE");
        super.setColorSelected("#FF8E34");
        super.setColorSelectedGradient("linear-gradient( to bottom, #FF8E34, #FFAD70)");
        super.setColorSelectedLight("linear-gradient( to bottom, #FFE4CA, #FFD0AA)");       
        super.setColorTitle("#475975");
        super.setColorTitleSelected("#212939");
        super.setColorLabel("#212939");
        super.setColorSelected("#212939");      
        super.setColorLabel("#212939");
        super.setColorSelected("#FF8E34");
    }
    
    
    
}
