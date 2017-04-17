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
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactAddPage();
            app.getContactHelper().createContact(new ContactData("Grzegorz", "Brzęczyszczykiewicz", "Poland", null));
        }
        app.getNavigationHelper().gotoMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoMainPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(0);
        Assert.assertEquals(before, after);


    }

}
