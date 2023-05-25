package web.model;

public class Car {
    String model;
    String vin;
    Integer year;

    public Car(String model, String vin, Integer year) {
        this.model = model;
        this.vin = vin;
        this.year = year;
    }


    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public Integer getYear() {
        return year;
    }


}
