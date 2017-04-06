package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by luk on 2017-03-29.
 */
public class ContactEditTest extends TestBase {

    @Test
    public void contactEditTest() {
        app.getNavigationHelper().gotoMainPage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("new name","new lastname","new address",null),false);
        app.getContactHelper().submitEditConact();
    }
}
