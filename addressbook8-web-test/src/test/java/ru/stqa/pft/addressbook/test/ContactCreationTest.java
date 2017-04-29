package ru.stqa.pft.addressbook.test;

import com.thoughtworks.xstream.XStream;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by luk on 2017-03-27.
 */
public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
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
}

