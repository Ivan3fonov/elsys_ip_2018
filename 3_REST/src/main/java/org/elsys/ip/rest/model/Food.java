package org.elsys.ip.rest.model;

public class Food {

    private int id;
    private String name;
    private int carbs;
    private int proteins;
    private int fats;
    private int unit;
    private int calsPerUnit;
    private int glycemicIndex;
    private int price;
    private int vitaminA;

    public Food(int id, String name, int carbs, int proteins, int fats, int unit, int calsPerUnit, int glycemicIndex, int price, int vitaminA) {
        this.id = id;
        this.name = name;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.unit = unit;
        this.calsPerUnit = calsPerUnit;
        this.glycemicIndex = glycemicIndex;
        this.price = price;
        this.vitaminA = vitaminA;
    }

    public Food() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getCalsPerUnit() {
        return calsPerUnit;
    }

    public void setCalsPerUnit(int calsPerUnit) {
        this.calsPerUnit = calsPerUnit;
    }

    public int getGlycemicIndex() {
        return glycemicIndex;
    }

    public void setGlycemicIndex(int glycemicIndex) {
        this.glycemicIndex = glycemicIndex;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(int vitaminA) {
        this.vitaminA = vitaminA;
    }
}
