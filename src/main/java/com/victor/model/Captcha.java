package com.victor.model;

public class Captcha {

    private String text;
    private String pathImage;
    private boolean solved;

    protected Captcha(){

    }

    public Captcha(String text, String pathImage, boolean solved) {
        this.text = text;
        this.pathImage = pathImage;
        this.solved = solved;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
