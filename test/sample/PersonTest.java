package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getFirstName() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        assertEquals("firstName", person.getFirstName());
    }

    @Test
    void setLastName() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        person.setLastName("lastName2");
        assertEquals("lastName2", person.getLastName());
    }

    @Test
    void getUsername() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        assertEquals("username", person.getUsername());
    }

    @Test
    void setAdress() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        person.setAdress("adress2");
        assertEquals("adress2", person.getAdress());
    }

    @Test
    void getEmail() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        assertEquals("email@etf.unsa.ba", person.getEmail());
    }

    @Test
    void setPassword() {
        Person person = new Person("firstName", "lastName", "username", "adress", "email@etf.unsa.ba", "password");
        person.setPassword("password2");
        assertEquals("password2", person.getPassword());
    }
}