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
        app.getNavigationHelper().gotoMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoContactAddPage();
        ContactData contactAdded = new ContactData("Grzegorz", "Brzęczyszczykiewicz", "Poland", "group name 2");
        app.getContactHelper().createContact(contactAdded);
        app.getNavigationHelper().gotoMainPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        before.add(contactAdded);
        before.stream().sorted((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        after.stream().sorted((o1, o2) -> o1.getLastName().compareTo((o2.getLastName())));
        Assert.assertEquals(before, after);
    }


}
