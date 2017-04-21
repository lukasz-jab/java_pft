package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        if (app.contact().list().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz").withAddress("Poland").withGroup(null));
        }
        app.goTo().mainPage();
        List<ContactData> before = app.contact().list();
        app.contact().selectContact();
        app.contact().deleteSelectedContact();
        app.goTo().mainPage();

        List<ContactData> after = app.contact().list();
        before.remove(0);
        Assert.assertEquals(before, after);


    }

}
