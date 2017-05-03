package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

/**
 * Created by luk on 2017-03-27.
 */
@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id;

    @Expose
    @Column(name="firstname")
    private String firstname;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @Expose
    @Column(name="address")
    @Type(type="text")
    private String address;

    @Transient
    private String group;

    @Expose
    @Column(name="home")
    @Type(type="text")
    private String homePhone;

    @Expose
    @Column(name="mobile")
    @Type(type="text")
    private String mobilePhone;

    @Expose
    @Column(name="work")
    @Type(type="text")
    private String workPhone;

    @Expose
    @Column(name="email")
    @Type(type="text")
    private String email;

    @Transient
    private String allPhones;

    @Column(name="photo")
    @Type(type="text")
    private String photo;


    public File getPhoto() {
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withtAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public int getId() {
        return id;
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

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}



