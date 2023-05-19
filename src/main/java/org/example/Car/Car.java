package org.example.Car;

import javafx.scene.image.Image;

public class Car {
    int id;
    String color;
    String model;
    String mark;
    String data_wyp;
    float cost;
    int buyer_id;

    Image image;

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public String getData_wyp() {
        return data_wyp;
    }

    public void setData_wyp(String data_wyp) {
        this.data_wyp = data_wyp;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public float getCost() {
        return cost;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public Image getImage() {
        return image;
    }
    int seller_id;

    public int getId() {
        return id;
    }

    public Car(String kolor, String model, String marka, float cena, String data_wyp, int seller_id, int buyer_id, Image image, int id)
    {
        this.id = id;
        this.color = kolor;
        this.model = model;
        this.mark = marka;
        this.data_wyp = data_wyp;
        this.cost = cena;
        this.buyer_id = buyer_id;
        this.seller_id = seller_id;
        this.image = image;
    }




}
