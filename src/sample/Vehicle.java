package sample;

public class Vehicle {
    private int id;
    private String name;
    private String brand;
    private String model;
    private int nmbDoors;
    private int nmbSeats;
    private String engine;
    private String available;
    private int price;

    public Vehicle() {
        name = "";
        brand = "";
        model = "";
        nmbDoors = 0;
        nmbSeats = 0;
        engine = "";
        available = "";
        price = 0;
    }

    public Vehicle(int id, String name, String brand, String model, int nmbDoors, int nmbSeats, String engine, String available, int price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.nmbDoors = nmbDoors;
        this.nmbSeats = nmbSeats;
        this.engine = engine;
        this.available = available;
        this.price = price;
    }

    public Vehicle(String name, String brand, String model, int nmbDoors, int nmbSeats, String engine, String available, int price) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.nmbDoors = nmbDoors;
        this.nmbSeats = nmbSeats;
        this.engine = engine;
        this.available = available;
        this.price = price;
    }

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNmbDoors() {
        return nmbDoors;
    }

    public void setNmbDoors(int nmbDoors) {
        this.nmbDoors = nmbDoors;
    }

    public int getNmbSeats() {
        return nmbSeats;
    }

    public void setNmbSeats(int nmbSeats) {
        this.nmbSeats = nmbSeats;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
