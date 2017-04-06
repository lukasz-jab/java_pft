package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoContactAddPage();
        app.getContactHelper().createContact(new ContactData("Grzegorz", "Brzęczyszczykiewicz", "Poland","group name 2"));
    }


}
