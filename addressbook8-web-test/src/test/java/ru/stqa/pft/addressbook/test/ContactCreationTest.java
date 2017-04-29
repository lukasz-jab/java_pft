package ru.stqa.pft.addressbook.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
                    .withHomePhone(split[3]).withPhoto(new File(split[6]))});
            line = reader.readLine();
        }
        return list.iterator();
    }


    @Test(dataProvider = "validContact")
    public void testContactCreation(ContactData contactAdded) {
        File photo = new File("src/test/resources/skanowanie0001.jpg");
        app.goTo().mainPage();
        Contacts before = app.contact().c_all();
        app.goTo().contactPage();
        app.contact().createContact(contactAdded);
        app.goTo().mainPage();
        Contacts after = app.contact().c_all();
        assertThat((before.size() + 1), equalTo(after.size()));
        assertThat(after, equalTo(before.withAdded(contactAdded.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void t() {
        File currentFile = new File("src/test/resources/skanowanie0001.jpg");
        System.out.println(currentFile.getAbsolutePath());
        System.out.println(currentFile.exists());
    }
}
