package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().mainPage();
        List<ContactData> before = app.contact().list();
        app.goTo().contactPage();
        ContactData contactAdded = new ContactData().withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                .withAddress("Poland").withGroup(null);
        app.contact().createContact(contactAdded);
        app.goTo().mainPage();

        List<ContactData> after = app.contact().list();
        before.add(contactAdded);
        before.stream().sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        after.stream().sorted((o1, o2) -> o1.getLastName().compareTo((o2.getLastName())));
        Assert.assertEquals(before, after);
    }


}
