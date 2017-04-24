package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by luk on 2017-04-24.
 */
public class ContactPhoneTest extends TestBase {
    @BeforeMethod
    public void ensurePredictions() {
        app.goTo().mainPage();
        if (!app.contact().isThereAContact()) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withId(0).withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland").withGroup(null));
        }
    }

    @Test
    public void test() {
        app.goTo().mainPage();
        ContactData randomContact = app.contact().c_all().iterator().next();
        app.contact().selectContactWithPhonesById(randomContact.getId());
        ContactData editPageRandomContact = app.contact().getPhonesFromContactPage(randomContact.getId());

        assertThat(randomContact.getHomePhone(), equalTo(cleaned(editPageRandomContact.getHomePhone())));
        assertThat(randomContact.getWorkPhone(), equalTo(cleaned(editPageRandomContact.getWorkPhone())));
        assertThat(randomContact.getMobilePhone(), equalTo(cleaned(editPageRandomContact.getMobilePhone())));
        assertThat(randomContact.getAddress(), equalTo(editPageRandomContact.getAddress()));
        assertThat(randomContact.getEmail(), equalTo(editPageRandomContact.getEmail()));
    }
    public String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}

