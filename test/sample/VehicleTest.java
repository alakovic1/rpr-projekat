package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void setName() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        vehicle.setName("name2");
        assertEquals("name2", vehicle.getName());
    }

    @Test
    void getBrand() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        assertEquals("brand", vehicle.getBrand());
    }

    @Test
    void setModel() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        vehicle.setModel("model2");
        assertEquals("model2", vehicle.getModel());
    }

    @Test
    void getEngine() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        assertEquals("benzin", vehicle.getEngine());
    }

    @Test
    void setAvailable() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        vehicle.setAvailable("no");
        assertEquals("no", vehicle.getAvailable());
    }

    @Test
    void getPrice() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        assertEquals(50, vehicle.getPrice());
    }

    @Test
    void setPrice() {
        Vehicle vehicle = new Vehicle("name", "brand", "model", 5, 5, "benzin", "yes", 50);
        vehicle.setPrice(100);
        assertEquals(100, vehicle.getPrice());
    }
}