package ru.stqa.pft.addressbook.test;

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
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "")
                .replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", "").replaceAll("F:", "");
        // to eliminated prefix in phone number in detail page"
    }

    @BeforeMethod
    public void ensurePredictions() {
        app.goTo().mainPage();
        if (!app.contact().isThereAContact()) {
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withId(0).withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                    .withAddress("Poland").withGroup(null));
        }
    }

    @Test(enabled = false)
    public void test() {
        app.goTo().mainPage();
        ContactData randomContact = app.contact().c_all().iterator().next();
        app.contact().selectContactWithPhonesById(randomContact.getId());
        ContactData editPageRandomContact = app.contact().getPhonesFromContactPage(randomContact.getId());

        assertThat(randomContact.getAllPhones(), equalTo(mergePhones(editPageRandomContact)));
        assertThat(randomContact.getAddress(), equalTo(editPageRandomContact.getAddress()));
        assertThat(randomContact.getEmail(), equalTo(editPageRandomContact.getEmail()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    @Test(enabled = true)
    public void testDetailContactPage() {
        app.goTo().mainPage();
        ContactData randomContact = app.contact().c_all().iterator().next();
        String contactFromEditPage = app.contact().getEditPageData(randomContact.getId());
        app.goTo().mainPage();
        String contactfromDetailPage = app.contact().getDetailPageData(randomContact.getId());

        System.out.println(cleaned(contactFromEditPage));
        System.out.println(cleaned(contactfromDetailPage));

        char[] editContact = cleaned(contactFromEditPage).toCharArray();
        char[] datailContact = cleaned(contactfromDetailPage).toCharArray();
        Arrays.sort(editContact);
        Arrays.sort(datailContact);

        assertThat(editContact, equalTo(datailContact));

    }

}

