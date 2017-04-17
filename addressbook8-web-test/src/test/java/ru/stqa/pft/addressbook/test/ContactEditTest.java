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
        app.getNavigationHelper().gotoMainPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactAddPage();
            app.getContactHelper().createContact(new ContactData("Grzegorz", "Brzęczyszczykiewicz", "Poland", null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact();
        ContactData contactEdited = new ContactData("new name", "new lastname", "new address", null);
        app.getContactHelper().fillContactForm((contactEdited), false);
        app.getContactHelper().submitEditConact();
        app.getNavigationHelper().gotoMainPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        System.out.println("before size: "+before.size());
        before.remove(0);
        before.add(contactEdited);
        System.out.println("before size: after change: "+before.size());
        System.out.println("after size: "+after.size());
        before.stream().sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        after.stream().sorted((o1, o2) -> o1.getLastName().compareTo((o2.getLastName())));
        Assert.assertEquals(before, after);
        // Contact dissaeper after click "UPDATE" - test fall !!!
    }
}
