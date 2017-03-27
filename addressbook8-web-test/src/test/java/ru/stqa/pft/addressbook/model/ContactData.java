package ru.stqa.pft.addressbook.model;

/**
 * Created by luk on 2017-03-27.
 */
public class ContactData {

    private final String firstname;
    private final String lastname;
    private final String address;

    public ContactData(String firstname, String lastname, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }
}


