package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        // app.getGroupHelper().returnTo - the application return with any click after few seconds
    }

}
