package ru.stqa.pft.addressbook.test;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by luk on 2017-05-05.
 */
public class ContactAddAndDeleteGroupTest extends TestBase {
    private SessionFactory sessionFactory;

    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstname("FromPrecondition").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland"));
        }
        if (app.db().group().size() == 0) {
            app.goTo().group();
            app.group().create(new GroupData().withName("group from Preconditions").withHeader("header 1").withFooter("footer 1"));
        }
    }

    @Test
    public void testContactAddAndDeleteGroup() {
        GroupData group = app.db().group().iterator().next();
        ContactData contact = app.db().contacts().iterator().next();
        app.goTo().mainPage();

        // check precondition to dont add group to contact with this group alredy added (in this appcliaction this is accepted)
        if (group.getContacts().contains(contact)) {
            app.contact().deleteGroupFromContact(contact, group);
        }
        app.contact().addToGroup(contact, group);
        assertThat(group.getContacts(), hasItem((contact)));
        assertThat(contact.getGroups(), hasItem((group)));
        //Assert.assertTrue(group.getContacts().contains(contact));

        app.contact().deleteGroupFromContact(contact, group);
        assertThat(contact.getGroups(), org.hamcrest.core.IsNot.not(hasItem((group))));
        // Assert.assertFalse(group.getContacts().contains(contact));
    }





    /*
    @Test(enabled=false)
    public void testContactAddAndDeleteGroup() {
        GroupData group = app.db().group().iterator().next();
        ContactData contact = app.db().contacts().iterator().next();
        app.goTo().mainPage();
        app.db().getGroups(contact);
        app.db().getContacts(group);
        app.contact().addToGroup(contact, group);
        assertThat(app.db().getGroups(contact), hasItem((group)));
        assertThat(app.db().getContacts(group), hasItem((contact)));
        app.contact().deleteGroupFromContact(contact, group);
        assertThat(app.db().getGroups(contact).withOuth(group), org.hamcrest.core.IsNot.not(hasItem((group))));
        assertThat(app.db().getContacts(group).withOut(contact), org.hamcrest.core.IsNot.not(hasItem(contact)));
    }
*/
}
