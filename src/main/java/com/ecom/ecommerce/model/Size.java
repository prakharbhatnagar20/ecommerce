package com.ecom.ecommerce.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Size {
    
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private int quantity;

    public Size(){
        
    }

}
