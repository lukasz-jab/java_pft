package ru.stqa.pft.addressbook.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by luk on 2017-04-24.
 */
public class ContactPhoneTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTest.class);
    //slf4j

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    @BeforeMethod
    public void ensurePredictions() {
        app.goTo().mainPage();
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland"));
        }
    }

    @Test(enabled = false)
    public void test() {
        logger.info("Start ContactPhoneTest");
        app.goTo().mainPage();
        ContactData randomContact = app.db().contacts().iterator().next();
        app.contact().selectContactWithPhonesById(randomContact.getId());
        ContactData editPageRandomContact = app.contact().getPhonesFromContactPage(randomContact.getId());
        assertThat(mergePhones(randomContact), equalTo(mergePhones(editPageRandomContact)));
        assertThat(randomContact.getAddress(), equalTo(editPageRandomContact.getAddress()));
        assertThat(randomContact.getEmail(), equalTo(editPageRandomContact.getEmail()));
        logger.info("Stop ContactPhoneTest");
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeContactData(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getAddress(),contact.getHomePhone()
                , contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail()
        )
                .stream().filter(s -> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining());
    }

    @Test(enabled = true)
    public void testDetailContactPage() {
        app.goTo().mainPage();
        ContactData randomContact = app.db().contacts().iterator().next();
        app.goTo().mainPage();
        String contactfromDetailPage = app.contact().getDetailPageData(randomContact.getId());
        assertThat(mergeContactData(randomContact), equalTo(cleaned(contactfromDetailPage)));
    }
}

