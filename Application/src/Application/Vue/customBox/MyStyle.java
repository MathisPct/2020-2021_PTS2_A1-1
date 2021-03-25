package Application.Vue.customBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class MyStyle {
    private String font;
    private int widthBar;
    private int fontSize;
    private int btnFontSize;
    
    private String colorBase;
    private String colorBaseBar;
    private String colorSelected;


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
    

    
    public MyStyle(String colorPalette, String fontName) {
        //Orange Style
        switch (colorPalette) {
            case "ORANGE":
                this.colorBase = "#FFFFFF";
                this.colorBaseBar = "#BCBECE";
                this.colorSelected = "#FF8E34";
                this.colorSelectedLight = "#FFE4CA";
                this.colorTitle = "#475975";
                this.colorTitleSelected = "##212939";
                this.colorLabel = "#212939";
                this.colorSelectedLabel = "#212939";
            break;
        }
                
        this.font = fontName;
        this.fontSize = 20;
        this.btnFontSize = 18;
        this.widthBar = 5;
        this.boxSpacing = 15;
        this.itemSpacing = 6;
        this.itemPadding = 5;
        this.titlePaddingH = 3;
        this.titlePaddingV = 5;
    }

    public String getColorBase() {
        return colorBase;
    }

    public String getColorBaseBar() {
        return colorBaseBar;
    }

    public String getColorSelected() {
        return colorSelected;
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
