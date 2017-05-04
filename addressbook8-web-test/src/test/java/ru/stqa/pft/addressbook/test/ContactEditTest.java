package ru.stqa.pft.addressbook.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by luk on 2017-03-29.
 */
public class ContactEditTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTest.class);

    //slf4j
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().mainPage();
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland").withGroup(null));
        }
    }

    @Test
    public void contactEditTest() {
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData contactEdited = before.iterator().next();
        int id = contactEdited.getId();
        before.remove(contactEdited);
        app.contact().modify(contactEdited);
        app.goTo().mainPage();
        Contacts after = app.db().contacts();
        assertThat(before.withAdded(contactEdited.withId(id)), equalTo(after));
    }
}