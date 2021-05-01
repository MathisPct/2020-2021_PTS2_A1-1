/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Metier;

import java.util.Date;

/**
 *
 * @author Eileen
 */
public class Material {
    private String name;
    private MaterialType type;
    private int quantity;
    private Date OrderDate;
    private Date DeliveryDate;
    private float price;

    public Material(String name, MaterialType type, int quantity, Date OrderDate, Date DeliveryDate, float price) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.OrderDate = OrderDate;
        this.DeliveryDate = DeliveryDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }
    
    
    
    
            
}
