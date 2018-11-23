package task01.model;

import task01.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    private String manufacturerName;
    private String model;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate manufactureDate;
    private Color color;
    private double price;
    private int amount;

    public Product(String manufacturerName, String model, LocalDate manufactureDate, Color color, double price, int amount) {
        this.manufacturerName = manufacturerName;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.color = color;
        this.price = price;
        this.amount = amount;
    }

    public Product() {
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product("
                + " manufacturerName:" + manufacturerName
                + " model:" + model
                + " manufactureDate:" + manufactureDate
                + " color:" + color
                + " price:" + price
                + " amount:" + amount
                + ")";
    }
}
