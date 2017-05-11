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
 * Created by luk on 2017-03-27.
 */
public class ContactDeletionTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTest.class);

    //slf4j
    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland"));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().mainPage();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().mainPage();
        Contacts after = app.db().contacts();

        assertThat(before.withOut(deletedContact), equalTo(after));
        verifyContactListInUI();
    }

}
