package sample;

public class Vehicle {
    private int id;
    private String name;
    private String brand;
    private String model;
    private int nmbDoors;
    private int nmbSeats;
    private String engine;
    private int available;
    private int price;
    private Person person;

    public Vehicle() {
        name = "";
        brand = "";
        model = "";
        nmbDoors = 0;
        nmbSeats = 0;
        engine = "";
        available = 0;
        price = 0;
        person = new Person();
    }

    public Vehicle(int id, String name, String brand, String model, int nmbDoors, int nmbSeats, String engine, int available, int price) {
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

    public Vehicle(int id, String name, String brand, String model, int nmbDoors, int nmbSeats, String engine, int available, int price, Person person) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.nmbDoors = nmbDoors;
        this.nmbSeats = nmbSeats;
        this.engine = engine;
        this.available = available;
        this.price = price;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
