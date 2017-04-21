package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by luk on 2017-03-29.
 */
public class ContactEditTest extends TestBase {

    @Test
    public void contactEditTest() {
        app.goTo().mainPage();
        if (app.contact().list().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland").withGroup(null));
            app.goTo().mainPage();
        }
        List<ContactData> before = app.contact().list();
        ContactData contactEdited = new ContactData().withFirstname("new name").withLastname("new lastname")
                .withAddress("new address").withGroup(null);
        app.contact().modify(contactEdited);
        app.goTo().mainPage();

        List<ContactData> after = app.contact().list();
        System.out.println("before size: " + before.size());
        before.remove(0);
        before.add(contactEdited);
        System.out.println("before size: after change: " + before.size());
        System.out.println("after size: " + after.size());
        before.stream().sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        after.stream().sorted((o1, o2) -> o1.getLastName().compareTo((o2.getLastName())));
        Assert.assertEquals(before, after);
        // Contact dissaeper after click "UPDATE" - test fall !!!
    }

}
