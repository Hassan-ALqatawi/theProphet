package com.al_qatawi.theprophet.modle;

public class Content {

    private int ID;
    private String TEXT;

    public Content(int ID, String TEXT) {
        this.ID = ID;
        this.TEXT = TEXT;
    }

    public Content(String TEXT) {
        this.TEXT = TEXT;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }
}
