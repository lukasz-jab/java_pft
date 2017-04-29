package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luk on 2017-04-28.
 */
public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(contact.getFirstName() + "," + contact.getLastName() + "," + contact.getAddress()
                    + "," + contact.getHomePhone() + "," + contact.getMobilePhone() + "," + contact.getWorkPhone()
                    + "," + contact.getPhoto() + String.format("\n"));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname("Name" + String.valueOf((int) (Math.random() * 10)))
                    .withLastname("Lastname" + String.valueOf((int) (Math.random() * 10)))
                    .withAddress("Address " + ((int) (Math.random() * 10))).withHomePhone(String.valueOf((int) (Math.random() * 1000000)))
                    .withMobilePhone((String.valueOf((int) (Math.random() * 1000000)))).withWorkPhone((String.valueOf((int) (Math.random() * 1000000))))
                    .withPhoto(new File("src/test/resources/s.jpg")));

        }
        return contacts;
    }
}