package com.example.cooking.model;

public class Categories {
    private String name;
    private int idPicture;

    public Categories(String name, int idPicture) {
        this.name = name;
        this.idPicture = idPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }
}
