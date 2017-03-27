package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoContactAddPage();
        app.getGroupHelper().fillContactForm(new ContactData("Grzegorz", "Brzęczyszczykiewicz", "Poland"));
        app.getGroupHelper().submitContactCreation();
        // app.getGroupHelper().returnTo - the application return with any click after few seconds
    }


}
