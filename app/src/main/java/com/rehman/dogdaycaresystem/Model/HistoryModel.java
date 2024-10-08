package com.rehman.dogdaycaresystem.Model;

public class HistoryModel
{
    String DateTime;
    String FoodTrays;
    String HumSensor;
    String FireSensor;
    String Id;
    String TemperSensor;
    String img;

    public HistoryModel(String dateTime, String foodTrays, String humSensor, String fireSensor, String id, String temperSensor, String img) {
        DateTime = dateTime;
        FoodTrays = foodTrays;
        HumSensor = humSensor;
        FireSensor = fireSensor;
        Id = id;
        TemperSensor = temperSensor;
        this.img = img;
    }

    public HistoryModel() {
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getFoodTrays() {
        return FoodTrays;
    }

    public void setFoodTrays(String foodTrays) {
        FoodTrays = foodTrays;
    }

    public String getHumSensor() {
        return HumSensor;
    }

    public void setHumSensor(String humSensor) {
        HumSensor = humSensor;
    }

    public String getFireSensor() {
        return FireSensor;
    }

    public void setFireSensor(String fireSensor) {
        FireSensor = fireSensor;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTemperSensor() {
        return TemperSensor;
    }

    public void setTemperSensor(String temperSensor) {
        TemperSensor = temperSensor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
