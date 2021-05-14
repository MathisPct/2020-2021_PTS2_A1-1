package Application.Vue.customBox.MyStyles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public abstract class MyStyle {
    private String font;
    private int widthBar;
    private int fontSize;
    private int btnFontSize;
    
    private String colorBase;
    private String colorBaseBar;
    private String colorSelected;
    private String colorSelectedGradient;


    private String colorSelectedLight;
    private String colorTitle;
    private String colorTitleSelected;
    private String colorLabel;
    private String colorSelectedLabel;
    
    private double itemSpacing;
    private int itemPadding;
    private int boxSpacing;
    private int titlePaddingH;
    private int titlePaddingV;
    

    
    public MyStyle(String fontName) {
         
        this.font = fontName;
        this.fontSize = 23;
        this.btnFontSize = 18;
        this.widthBar = 5;
        this.boxSpacing = 15;
        this.itemSpacing = 6;
        this.itemPadding = 5;
        this.titlePaddingH = 3;
        this.titlePaddingV = 5;
    }
    
    public abstract void initStyleColor();

    public String getColorBase() {
        return colorBase;
    }

    public String getColorBaseBar() {
        return colorBaseBar;
    }

    public String getColorSelected() {
        return colorSelected;
    }

    public String getColorSelectedGradient() {
        return colorSelectedGradient;
    } 

    public String getColorSelectedLight() {
        return colorSelectedLight;
    }
    
    public int getWidthBar() {
        return this.widthBar;
    }
    
    public double getItemSpacing(){
        return this.itemSpacing;
    }
    
    public int getItemPadding(){
        return this.itemPadding;
    }
    
    public String getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getColorTitle() {
        return colorTitle;
    }

    public String getColorTitleSelected() {
        return colorTitleSelected;
    }

    public String getColorLabel() {
        return colorLabel;
    }

    public String getColorSelectedLabel() {
        return colorSelectedLabel;
    }  

    public void setColorBase(String colorBase) {
        this.colorBase = colorBase;
    }

    public void setColorBaseBar(String colorBaseBar) {
        this.colorBaseBar = colorBaseBar;
    }

    public void setColorSelected(String colorSelected) {
        this.colorSelected = colorSelected;
    }

    public void setColorSelectedGradient(String colorSelectedGradient) {
        this.colorSelectedGradient = colorSelectedGradient;
    }

    public void setColorSelectedLight(String colorSelectedLight) {
        this.colorSelectedLight = colorSelectedLight;
    }

    public void setColorTitle(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    public void setColorTitleSelected(String colorTitleSelected) {
        this.colorTitleSelected = colorTitleSelected;
    }

    public void setColorLabel(String colorLabel) {
        this.colorLabel = colorLabel;
    }

    public void setColorSelectedLabel(String colorSelectedLabel) {
        this.colorSelectedLabel = colorSelectedLabel;
    }
    
    

    public int getBoxSpacing() {
        return boxSpacing;
    }

    public int getTitlePaddingH() {
        return titlePaddingH;
    }

    public int getTitlePaddingV() {
        return titlePaddingV;
    }

    public int getBtnFontSize() {
        return btnFontSize;
    }
    
    
    
}
