package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().mainPage();
        if (!app.contact().isThereAContact()) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withId(0).withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland").withGroup(null));
        }
        Contacts before = app.contact().c_all();
        app.goTo().contactPage();
        ContactData contactAdded = new ContactData().withId(0).withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                .withAddress("Poland").withGroup(null);
        app.contact().createContact(new ContactData());
        app.goTo().mainPage();
        Contacts after = app.contact().c_all();
        assertThat(before.withAdded(contactAdded.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))
                , equalTo(after));
    }


}
